package ru.alikhano.cyberlife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	// test controller, this method will actually redirect to a home page
	@RequestMapping("/home")
	public String home() {
		return "home";
	}

}
