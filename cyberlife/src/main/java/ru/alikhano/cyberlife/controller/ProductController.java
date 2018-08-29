package ru.alikhano.cyberlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ru.alikhano.cyberlife.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	

}
