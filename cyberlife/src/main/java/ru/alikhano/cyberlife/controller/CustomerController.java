package ru.alikhano.cyberlife.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.alikhano.cyberlife.DTO.AddressDTO;
import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.CustomerDTO;
import ru.alikhano.cyberlife.DTO.UserDTO;
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
	
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	private static final String CUSTOMER = "customer";
	private static final String UPDATE= "updateAccount";
	private static final String ERROR = "error";
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	private static final String REDIRECT = "redirect:/myAccount";  
	
	
	/** controller to access customer's profile
	 * @param model
	 * @param authentication to retrieve customer's username
	 * @return jsp file name
	 */
	@RequestMapping("/myAccount")
	public String viewAccount(Model model, Authentication authentication) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		model.addAttribute(CUSTOMER, customerDTO);
		
		return "customerAccount";
	}
	
	/** controller to show an empty customer's profile if profile generation step failed during the registration
	 * @param model
	 * @param authentication to retrieve customer's username
	 * @return jsp file name
	 */
	@GetMapping("/myAccount/updateAccount/")
	public String updateAccount(Model model, Authentication authentication) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customer = customerService.getByUserId(user.getUserId());
		
		if (customer == null) {
			logger.error("Customer profile has not been created before");
			customer = new CustomerDTO();
			customer.setUser(user);
			customerService.create(customer);
			model.addAttribute(CUSTOMER, customer);
			model.addAttribute(ERROR,"You seem to not have a profile. We generated it for you");
			return UPDATE;
		}
		
		return UPDATE;
			
		}
	
	
	/** controller to show a page where customer can update his personal info
	 * @param customerId to retrieve customer from database
	 * @param model
	 * @param authentication to retrieve customer's username
	 * @return jsp file name
	 */
	@GetMapping("/myAccount/updateAccount/{customerId}")
	public String updateAccount(@PathVariable("customerId") int customerId, Model model, Authentication authentication) {
		CustomerDTO customerDTO = customerService.getById(customerId);
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customer = customerService.getByUserId(user.getUserId());
		
		if (customerDTO == null) {
			logger.error("Customer profile has not been created before");
			customer = new CustomerDTO();
			customer.setUser(user);
			customerService.create(customerDTO);
			model.addAttribute(CUSTOMER, customer);
			model.addAttribute(ERROR,"You seem to not have a profile. We generated it for you");
			return UPDATE;
			
		}
		
		if (customer.getCustomerId() != customerId) {
			logger.error("Oops. You should not try to access someone else's profile!");
			model.addAttribute(CUSTOMER, customer);
			model.addAttribute(ERROR,"You cannot access someone else's profile");
			return UPDATE;
			
		}

		model.addAttribute(CUSTOMER, customerDTO);
		

		return UPDATE;
	}
	
	/**
	 * controller to update customer's personal info
	 * @param customerDTO object that contains new customer info
	 * @param result
	 * @param request
	 * @param authentication to retrieve customer's username
	 * @param model
	 * @return redirect to another page
	 * @throws CustomLogicException
	 */
	@PostMapping(value = "/myAccount/updateAccount")
	public String updateAccountPost(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult result,
			HttpServletRequest request, Authentication authentication, Model model) throws CustomLogicException {
		
		try {
			customerService.update(customerDTO);
			logger.info(customerDTO.getLastName() + " has updated his/her account");
		}
		catch (DataIntegrityViolationException ex) {
			model.addAttribute(ERROR,"Your have used the email address which is already taken on this website. Please try again.");
			logger.error("Your have used the email address which is already taken on this website. Please try again.");
			return UPDATE;
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute(ERROR,"Please check your input, some fields are filled in incorrectly");
			logger.error("Errors in user input");
			return UPDATE;
		}
		

		return REDIRECT;
	}
	
	/**
	 * controller to display a page where customers can change their address
	 * @param authentication to retrieve customer's username
	 * @param model
	 * @return jsp file name
	 */
	@GetMapping("/myAccount/changeAddress")
	public String changeAddress(Authentication authentication, Model model) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());

		model.addAttribute("address", customerDTO.getAddress());

		return "changeAddress";
	}
	
	/**
	 * controller to change the address
	 * @param addressDTO object that contains new address details
	 * @param result
	 * @param request
	 * @param authentication to retrieve customer's username
	 * @param model
	 * @return redirect to another page
	 */
	@PostMapping(value = "/myAccount/changeAddress")
	public String changeAddressPost(@Valid @ModelAttribute("address") AddressDTO addressDTO, BindingResult result,
			HttpServletRequest request, Authentication authentication, Model model) {
		
		try {
			addressService.update(addressDTO);
			logger.info(authentication.getName() + " has updated his/her account");
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute(ERROR,"Your have mistyped values for some of the fields. Please verify provided information (no negative values, correct zip code format)");
			logger.error(ex.getMessage() + "WRONG values");
			return "changeAddress";
		}
		
		
		
		return REDIRECT;
	}
	
	/** 
	 * controller to display a page where customer's can change their password
	 * @param authentication to retrieve customer's username
	 * @param model
	 * @return jsp file name
	 */
	@GetMapping("/myAccount/changePassword")
	public String changePassword(Authentication authentication, Model model) {
	

		return "changePassword";
	}
	
	/**
	 * controller to change the password
	 * @param oldPassword
	 * @param newPassword
	 * @param request
	 * @param authentication to retrieve customer's username
	 * @param model
	 * @return redirect to another page
	 */
	@PostMapping(value = "/myAccount/changePassword")
	public String changePasswordPost(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword,
			HttpServletRequest request, Authentication authentication, Model model) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		if (!userService.verifyPassword(oldPassword, user.getUserId())) {
			model.addAttribute("mismatchMsg", "Oops, entered password does not match the stored value");
			logger.error("Oops, entered password does not match the stored value");
			return "changePassword";
		}
		userService.changePassword(newPassword, user);
		logger.info(username + "has changed his/her password");
		return REDIRECT;
	}



}
