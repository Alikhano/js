package ru.alikhano.cyberlife.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.alikhano.cyberlife.DTO.CustomerDTO;
import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.UserService;

@Controller
public class CustomerController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/myAccount")
	public String viewAccount(Model model, Authentication authentication) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		model.addAttribute("customer", customerDTO);
		
		return "customerAccount";
	}
	
	
	@RequestMapping("/updateAccount/{customerId}")
	public String editProduct(@PathVariable("customerId") int customerId, Model model) {
		CustomerDTO customerDTO = customerService.getById(customerId);
		model.addAttribute("customer", customerDTO);

		return "updateAccount";
	}
	
	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
	public String editProductPost(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult result,
			HttpServletRequest request) {
		customerService.update(customerDTO);
		System.out.println(customerDTO);

		return "redirect:/myAccount";
	}

	


}
