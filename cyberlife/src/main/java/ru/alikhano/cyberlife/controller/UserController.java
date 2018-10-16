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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.CustomerDTO;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	CustomerService customerService;
	@Autowired
	UserService userService;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@RequestMapping("/createProfile")
	public String createProfile(Model model) {
		CustomerDTO customerDTO = new CustomerDTO();
		model.addAttribute("customerForm", customerDTO);
		
		return "createProfile";
	}
	
	@RequestMapping(value="/createProfile", method = RequestMethod.POST)
	public String createProfilePost(@ModelAttribute(name="customerForm") @Valid CustomerDTO customerForm, BindingResult result, Model model, HttpServletRequest request) throws CustomLogicException {
		
		if (customerService.getByEmail(customerForm.getEmail()) != null) {
			model.addAttribute("repEmail", "Oops, this email is taken. Please try again");
			return "createProfile";
		}
		
		try {
			customerForm.setUser(userService.getByUsernameDTO((String)request.getSession().getAttribute("username")));
			
			customerService.create(customerForm);
			logger.info("User has registered and created an account");
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute("error","Your have mistyped values for some of the fields. Please verify provided information");
			logger.error(ex.getMessage() + "WRONG values while creating a personal account");
			return "createProfile";
		}
		
	
			
		return "redirect:/login";
	}
	
	
}
