package ru.alikhano.cyberlife.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.UserService;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Controller
public class UserController {
	
	@Autowired
	CustomerService customerService;
	@Autowired
	UserService userService;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	private static final String CREATE = "createProfile";
	
	/** displays a form to create a customer's profile
	 * @param model
	 * @return jsp file name
	 */
	@GetMapping("/createProfile")
	public String createProfile(Model model) {
		CustomerDTO customerDTO = new CustomerDTO();
		model.addAttribute("customerForm", customerDTO);
		
		return CREATE;
	}
	
	/**
	 * controller to create new customer's profile
	 * @param customerForm object containing customer's personal information
	 * @param result
	 * @param model
	 * @param request http request received from client side
	 * @return redirect to login page
	 * @throws CustomLogicException
	 */
	@PostMapping(value="/createProfile")
	public String createProfilePost(@ModelAttribute(name="customerForm") @Valid CustomerDTO customerForm, BindingResult result, Model model, HttpServletRequest request) {
		
		if (customerService.getByEmail(customerForm.getEmail()) != null) {
			model.addAttribute("repEmail", "Oops, this email is taken. Please try again");
			return CREATE;
		}
		
		try {
			customerForm.setUser(userService.getByUsernameDTO((String)request.getSession().getAttribute("username")));
			
			customerService.create(customerForm);
			logger.info("User has registered and created an account");
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute("error","Your have mistyped values for some of the fields. Please verify provided information");
			logger.error(ex.getMessage() + "WRONG values while creating a personal account");
			return CREATE;
		}
		
			
		return "redirect:/login";
	}
}
