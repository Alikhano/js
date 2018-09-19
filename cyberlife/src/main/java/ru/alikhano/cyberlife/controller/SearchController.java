package ru.alikhano.cyberlife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.alikhano.cyberlife.service.CategoryService;
import ru.alikhano.cyberlife.service.ConsciousnessService;

@Controller
public class SearchController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ConsciousnessService consService;
	
	 @RequestMapping("/searchProduct")
	    public String parameterSearch(Model model) {
	       
	    	model.addAttribute("categoryDTOList", categoryService.getAll());
			model.addAttribute("consDTOList", consService.getAll());
			
	        return "searchProduct";
	    }

}
