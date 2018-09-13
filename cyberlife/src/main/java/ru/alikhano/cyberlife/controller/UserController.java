package ru.alikhano.cyberlife.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.alikhano.cyberlife.DTO.AddressDTO;
import ru.alikhano.cyberlife.DTO.CustomerDTO;
import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.model.Address;
import ru.alikhano.cyberlife.model.Customer;
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
	public String createProfilePost(@ModelAttribute(name="customerForm") CustomerDTO customerForm, BindingResult result, Model model, HttpServletRequest request) {
		
		if(result.hasErrors()){
            return "registerCustomer";
        }
		
		customerForm.setUser(userService.getByUsernameDTO((String)request.getSession().getAttribute("username")));
		
		customerService.create(customerForm);
			
		return "redirect:/login";
	}
	
	
}
