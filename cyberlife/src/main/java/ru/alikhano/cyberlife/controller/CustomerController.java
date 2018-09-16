package ru.alikhano.cyberlife.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.alikhano.cyberlife.DTO.AddressDTO;
import ru.alikhano.cyberlife.DTO.CustomerDTO;
import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.service.AddressService;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.UserService;

@Controller
public class CustomerController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@RequestMapping("/myAccount")
	public String viewAccount(Model model, Authentication authentication) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		model.addAttribute("customer", customerDTO);
		
		return "customerAccount";
	}
	
	
	@RequestMapping("/myAccount/updateAccount/{customerId}")
	public String updateAccount(@PathVariable("customerId") int customerId, Model model) {
		CustomerDTO customerDTO = customerService.getById(customerId);
		model.addAttribute("customer", customerDTO);

		return "updateAccount";
	}
	
	@RequestMapping(value = "/myAccount/updateAccount", method = RequestMethod.POST)
	public String updateAccountPost(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult result,
			HttpServletRequest request) {
		customerService.update(customerDTO);

		return "redirect:/myAccount";
	}
	
	@RequestMapping("/myAccount/changeAddress")
	public String changeAddress(Authentication authentication, Model model) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());

		model.addAttribute("address", customerDTO.getAddress());

		return "changeAddress";
	}
	
	@RequestMapping(value = "/myAccount/changeAddress", method = RequestMethod.POST)
	public String changeAddressPost(@Valid @ModelAttribute("address") AddressDTO addressDTO, BindingResult result,
			HttpServletRequest request, Authentication authentication) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		addressService.update(addressDTO);
		
		return "redirect:/myAccount";
	}
	
	@RequestMapping("/myAccount/changePassword")
	public String changePassword(Authentication authentication, Model model) {
	

		return "changePassword";
	}
	
	@RequestMapping(value = "/myAccount/changePassword", method = RequestMethod.POST)
	public String changePasswordPost(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
			HttpServletRequest request, Authentication authentication, Model model) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		if (!userService.verifyPassword(oldPassword, user.getUserId())) {
			model.addAttribute("mismatchMsg", "Oops, entered password does not match the stored value");
			return "changePassword";
		}
		userService.changePassword(newPassword, user);
		return "redirect:/myAccount";
	}

	


}
