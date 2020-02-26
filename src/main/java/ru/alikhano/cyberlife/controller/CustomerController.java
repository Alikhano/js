package ru.alikhano.cyberlife.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.alikhano.cyberlife.dto.AddressDTO;
import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.dto.UserDTO;
import ru.alikhano.cyberlife.service.AddressService;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.UserService;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Controller
public class CustomerController {
	
	private static final Logger LOGGER   = LogManager.getLogger(CustomerController.class);
	private static final String CUSTOMER = "customer";
	private static final String UPDATE   = "updateAccount";
	private static final String ERROR    = "error";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AddressService addressService;
	
	private static final String REDIRECT = "redirect:/myAccount";  

	@RequestMapping("/myAccount")
	public String viewAccount(Model model, Authentication authentication) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		model.addAttribute(CUSTOMER, customerDTO);
		
		return "customerAccount";
	}
	

	@GetMapping("/myAccount/updateAccount/")
	public String updateAccount(Model model, Authentication authentication) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customer = customerService.getByUserId(user.getUserId());

		if (customer == null) {
			LOGGER.error("Customer profile has not been created before");
			customer = new CustomerDTO();
			customer.setUser(user);
			customerService.create(customer);
			model.addAttribute(CUSTOMER, customer);
			model.addAttribute(ERROR, "You seem to not have a profile. We generated it for you");
			return UPDATE;
		}

		return UPDATE;
	}
	

	@GetMapping("/myAccount/updateAccount/{customerId}")
	public String updateAccount(@PathVariable("customerId") int customerId, Model model, Authentication authentication) {
		CustomerDTO customerDTO = customerService.getById(customerId);
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customer = customerService.getByUserId(user.getUserId());
		
		if (customerDTO == null) {
			LOGGER.error("Customer profile has not been created before");
			customer = new CustomerDTO();
			customer.setUser(user);
			customerService.create(customerDTO);
			model.addAttribute(CUSTOMER, customer);
			model.addAttribute(ERROR,"You seem to not have a profile. We generated it for you");
			return UPDATE;
		}
		
		if (customer.getCustomerId() != customerId) {
			LOGGER.error("Oops. You should not try to access someone else's profile!");
			model.addAttribute(CUSTOMER, customer);
			model.addAttribute(ERROR,"You cannot access someone else's profile");
			return UPDATE;
		}


		model.addAttribute(CUSTOMER, customerDTO);

		return UPDATE;
	}

	@PostMapping(value = "/myAccount/updateAccount")
	public String updateAccountPost(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult result,
			HttpServletRequest request, Authentication authentication, Model model) throws CustomLogicException {
		
		try {
			customerService.update(customerDTO);
			LOGGER.info(customerDTO.getLastName() + " has updated his/her account");
		}
		catch (DataIntegrityViolationException ex) {
			model.addAttribute(ERROR,
							   "Your have used the email address which is already taken on this website. Please try again.");
			LOGGER.error("Your have used the email address which is already taken on this website. Please try again.");
			return UPDATE;
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute(ERROR,"Please check your input, some fields are filled in incorrectly");
			LOGGER.error("Errors in user input");
			return UPDATE;
		}
		

		return REDIRECT;
	}

	@GetMapping("/myAccount/changeAddress")
	public String changeAddress(Authentication authentication, Model model) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());

		model.addAttribute("address", customerDTO.getAddress());

		return "changeAddress";
	}

	@PostMapping(value = "/myAccount/changeAddress")
	public String changeAddressPost(@Valid @ModelAttribute("address") AddressDTO addressDTO, BindingResult result,
			HttpServletRequest request, Authentication authentication, Model model) {
		
		try {
			addressService.update(addressDTO);
			LOGGER.info(authentication.getName() + " has updated his/her account");
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute(ERROR,
							   "Your have mistyped values for some of the fields. Please verify provided information (no negative values, correct zip code format)");
			LOGGER.error(ex.getMessage() + "WRONG values");
			return "changeAddress";
		}

		return REDIRECT;
	}

	@GetMapping("/myAccount/changePassword")
	public String changePassword(Authentication authentication, Model model) {
		return "changePassword";
	}

	@PostMapping(value = "/myAccount/changePassword")
	public String changePasswordPost(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
			HttpServletRequest request, Authentication authentication, Model model) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		if (!userService.verifyPassword(oldPassword, user.getUserId())) {
			model.addAttribute("mismatchMsg", "Oops, entered password does not match the stored value");
			LOGGER.error("Oops, entered password does not match the stored value");
			return "changePassword";
		}
		userService.changePassword(newPassword, user);
		LOGGER.info(username + "has changed his/her password");
		return REDIRECT;
	}

}
