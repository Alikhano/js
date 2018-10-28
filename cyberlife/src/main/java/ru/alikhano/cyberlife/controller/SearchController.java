package ru.alikhano.cyberlife.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.ProductInfo;
import ru.alikhano.cyberlife.DTO.SearchRequest;
import ru.alikhano.cyberlife.service.CategoryService;
import ru.alikhano.cyberlife.service.ConsciousnessService;
import ru.alikhano.cyberlife.service.ProductService;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Controller
public class SearchController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ConsciousnessService consService;
	
	@Autowired
	ProductService productService;
	
	private static final Logger logger = LogManager.getLogger(SearchController.class);
	
	 /** 
	  * displays search page
	 * @param model
	 * @return jsp file name
	 */
	@GetMapping("/searchProduct")
	    public String parameterSearch(Model model) {
	       
	    	model.addAttribute("categoryDTOList", categoryService.getAll());
			model.addAttribute("consDTOList", consService.getAll());
			
	        return "searchProduct";
	    }
	 
	 /**
	  * returns search results
	 * @param searchRequest object containing details of a search request received from client side
	 * @return search result(s)
	 * @throws CustomLogicException
	 */
	@PostMapping(value="/searchProduct", produces="application/json")
	 public ResponseEntity<?> getSearchedProducts(@RequestBody SearchRequest searchRequest) throws CustomLogicException {
		 
		 logger.info(searchRequest.toString());
		 
		
		 if (searchRequest.getFromPrice() < 0 || searchRequest.getToPrice() < 0) {
			 logger.error("User have tried to search by negative price range");
			 return ResponseEntity.badRequest().body("Price range cannot be negative!");
		 }
		 
		 logger.info("Search request has produced some results");
		 
		 List<ProductInfo> searchResult = productService.searchParam(searchRequest);
		 
	
	     return ResponseEntity.ok(searchResult);
	 }
	 
}
