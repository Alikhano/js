package ru.alikhano.cyberlife.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.alikhano.cyberlife.DTO.ProductInfo;
import ru.alikhano.cyberlife.DTO.SearchRequest;
import ru.alikhano.cyberlife.service.CategoryService;
import ru.alikhano.cyberlife.service.ConsciousnessService;
import ru.alikhano.cyberlife.service.ProductService;

@Controller
public class SearchController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ConsciousnessService consService;
	
	@Autowired
	ProductService productService;
	
	 @RequestMapping("/searchProduct")
	    public String parameterSearch(Model model) {
	       
	    	model.addAttribute("categoryDTOList", categoryService.getAll());
			model.addAttribute("consDTOList", consService.getAll());
			
	        return "searchProduct";
	    }
	 
	 @RequestMapping(value="/searchProduct",method=RequestMethod.POST,  produces="application/json")
	 public @ResponseBody List<ProductInfo> getSearchedProducts(@RequestBody SearchRequest searchRequest) {
		 int catId;
		 int consId;
		 String category = searchRequest.getCategory();
		 if(category.equals("any")) {
			 catId = 0;
		 }
		 else {
			 catId = categoryService.getByType(category).getCatId();
		 }

		 String consLevel = searchRequest.getConsLevel();
		 if(consLevel.equals("any")) {
			 consId = 0;
		 }
		 else {
	        consId = consService.getByLevel(consLevel).getConsId();
		 }
		
		 double fromPrice = searchRequest.getFromPrice();
		 double toPrice = searchRequest.getToPrice();
	 
	     return productService.searchParam(catId, consId, fromPrice, toPrice);
	 }

}
