package ru.alikhano.cyberlife.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.WebUtils;

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dto.RoleDTO;
import ru.alikhano.cyberlife.dto.UserDTO;
import ru.alikhano.cyberlife.model.User;
import ru.alikhano.cyberlife.service.CartService;
import ru.alikhano.cyberlife.service.RoleService;
import ru.alikhano.cyberlife.service.UserService;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private RoleService roleService;

	private static final String REGISTER = "register";  
	
	private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

	/** 
	 * display homepage, create persistent cookie to store individual cart ids
	 * @param request http request received from client side
	 * @param response http response from server
	 * @return jsp file name
	 */
	@GetMapping(value = "/")
	public String home(HttpServletRequest request, HttpServletResponse response) {
		String cookieName = "cartId";
		final int expiryTime = 10 * 24 * 60 * 60; //10 days
		final String cookiePath = "/";
		Cookie[] cookies = request.getCookies();
		boolean hasCookie = false;
		int cartId = 0;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					hasCookie = true;
					cartId = Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue());
				}
			}
						
			if (!hasCookie || cartService.getById(cartId) == null) {
				
				CartDTO cartDTO = new CartDTO();
				int cookieValue = cartService.createAndGetId(cartDTO);
				Cookie cartCookie = new Cookie(cookieName, String.valueOf(cookieValue));
				cartCookie.setMaxAge(expiryTime);
				cartCookie.setPath(cookiePath);
				response.addCookie(cartCookie);
				LOGGER.info("New persistent cookie and cart ID");
			}
			
		}   
		return "home";
	}
	
	

	/** controller to display login page
	 * @param model
	 * @param error message to display in case of wrong credentials
	 * @param logout message to display after logout
	 * @return jsp file name
	 */
	@GetMapping(value = "/login")
	public String login(Model model, String error, String logout) {
		if (error != null) {
			model.addAttribute("error", "Username or password is incorrect.");
		}

		if (logout != null) {
			model.addAttribute("message", "Logged out successfully.");
		}

		return "login";
	}

	@GetMapping(value = "/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return REGISTER;
	}

	/**
	 * controller to register a new user
	 * @param userForm object, containing login/password info
	 * @param bindingResult
	 * @param model
	 * @param request http request received from client side
	 * @return redirect to a page where user can create a customer profile
	 */
	@PostMapping(value = "/registration")
	public String registration(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult, Model model,
			HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			LOGGER.error("binding result errors");
			return REGISTER;
		}
		
		if (userService.getByUsernameDTO(userForm.getUsername()) != null) {
			model.addAttribute("repUsername", "Oops, this username is taken. Please try again");
			LOGGER.error("User tried to use a duplicate username");
			return REGISTER;
		}
		
		RoleDTO roleDTO = roleService.getRoleDTO();
		Set<RoleDTO> roles = new HashSet<>();
		roles.add(roleDTO);
		userForm.setRoles(roles);
		userForm.setEnabled(true);

		request.getSession().setAttribute("username", userForm.getUsername());
		
		userService.register(userForm);
		
		LOGGER.info(userForm.getUsername() + " has registered");

		return "redirect:/createProfile";
	}

}
