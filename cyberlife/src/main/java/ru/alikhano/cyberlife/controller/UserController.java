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

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);
	private static final String CREATE = "createProfile";
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private UserService userService;

	@GetMapping("/createProfile")
	public String showCreateProfilePage(Model model) {
		CustomerDTO customerDTO = new CustomerDTO();
		model.addAttribute("customerForm", customerDTO);
		
		return CREATE;
	}

	@PostMapping(value="/createProfile")
	public String createProfile(@ModelAttribute(name="customerForm") @Valid CustomerDTO customerForm,
			BindingResult result, Model model, HttpServletRequest request) {
		if (customerService.getByEmail(customerForm.getEmail()) != null) {
			model.addAttribute("repEmail", "Oops, this email is taken. Please try again");
			return CREATE;
		}
		
		try {
			customerForm.setUser(userService.getByUsernameDTO((String)request.getSession().getAttribute("username")));
			customerService.create(customerForm);
			LOGGER.info("User has registered and created an account");
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute("error","Your have mistyped values for some of the fields. " +
					"Please verify provided information");
			LOGGER.error(ex.getMessage() + "WRONG values while creating a personal account");
			return CREATE;
		}

		return "redirect:/login";
	}
}
