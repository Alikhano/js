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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.RoleDTO;
import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.model.User;
import ru.alikhano.cyberlife.service.CartService;
import ru.alikhano.cyberlife.service.ProductService;
import ru.alikhano.cyberlife.service.RoleService;
import ru.alikhano.cyberlife.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@Autowired
	CartService cartService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ProductService productService;
	
	private static final String REGISTER = "register";  
	
	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
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
				logger.info("New persistent cookie and cart ID");
			}
			
		}   
		return "home";
	}
	
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null) {
			model.addAttribute("error", "Username or password is incorrect.");
		}

		if (logout != null) {
			model.addAttribute("message", "Logged out successfully.");
		}
		

		return "login";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return REGISTER;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult, Model model,
			HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			logger.error("binding result errors");
			return REGISTER;
		}
		
		if (userService.getByUsernameDTO(userForm.getUsername()) != null) {
			model.addAttribute("repUsername", "Oops, this username is taken. Please try again");
			logger.error("User tried to use a duplicate username");
			return REGISTER;
		}
		
		RoleDTO roleDTO = roleService.getRoleDTO();
		Set<RoleDTO> roles = new HashSet<>();
		roles.add(roleDTO);
		userForm.setRoles(roles);
		userForm.setEnabled(true);
		

		request.getSession().setAttribute("username", userForm.getUsername());
		
		userService.register(userForm);
		
		logger.info(userForm.getUsername() + " has registered");

		return "redirect:/createProfile";
	}

}
