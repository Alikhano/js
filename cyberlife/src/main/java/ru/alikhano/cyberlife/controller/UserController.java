package ru.alikhano.cyberlife.controller;

import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping("/createProfile")
	public String createProfile(Model model) {
		CustomerDTO customerDTO = new CustomerDTO();
		model.addAttribute("customerForm", customerDTO);
		
		return "createProfile";
	}
	
	@RequestMapping(value="/createProfile", method = RequestMethod.POST)
	public String createProfilePost(@ModelAttribute(name="customerForm") CustomerDTO customerForm, BindingResult result, Model model, HttpServletRequest request) throws CustomLogicException {
		
		if(result.hasErrors()){
            return "registerCustomer";
        }
		
		if (customerForm.getLastName() == null || (customerForm.getAddress().getCity() == null && customerForm.getAddress().getStreet() == null && customerForm.getAddress().getZipCode() == null)) {
			throw new CustomLogicException("You did not fill in some of information for your account. Please make sure to complete your profile after login!");
		}
		
		
		if (customerService.getByEmail(customerForm.getEmail()) != null) {
			model.addAttribute("repEmail", "Oops, this email is taken. Please try again");
			return "createProfile";
		}
		
		customerForm.setUser(userService.getByUsernameDTO((String)request.getSession().getAttribute("username")));
		
		customerService.create(customerForm);
			
		return "redirect:/login";
	}
	
	
}
