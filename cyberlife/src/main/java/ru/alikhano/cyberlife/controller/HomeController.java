package ru.alikhano.cyberlife.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.model.User;
import ru.alikhano.cyberlife.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;

	// test controller, this method will actually redirect to a home page
	@RequestMapping("/")
	public String home() {
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

	        return "register";
	    }

	    @RequestMapping(value = "/registration", method = RequestMethod.POST)
	    public String registration(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult, Model model, HttpServletRequest request) {

	        if (bindingResult.hasErrors()) {
	            return "registration";
	        }
	        
	        request.getSession().setAttribute("username", userForm.getUsername());

	        userService.register(userForm);
	        
	       
	        return "redirect:/createProfile";
	    }

}
