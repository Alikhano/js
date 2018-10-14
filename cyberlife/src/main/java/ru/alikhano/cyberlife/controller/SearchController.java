package ru.alikhano.cyberlife.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.ProductDTO;
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
	
	private static final Logger logger = LogManager.getLogger(SearchController.class);
	
	 @RequestMapping("/searchProduct")
	    public String parameterSearch(Model model) {
	       
	    	model.addAttribute("categoryDTOList", categoryService.getAll());
			model.addAttribute("consDTOList", consService.getAll());
			
	        return "searchProduct";
	    }
	 
	 @RequestMapping(value="/searchProduct",method=RequestMethod.POST,  produces="application/json")
	 public @ResponseBody List<ProductInfo> getSearchedProducts(@RequestBody SearchRequest searchRequest) throws CustomLogicException {
		 int catId;
		 int consId;
		 String category = searchRequest.getCategory();
		 String model = searchRequest.getModel();
		 
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
		 
	
	     return productService.searchParam(model, catId, consId, fromPrice, toPrice);
	 }
	 
	/* @RequestMapping(value="/searchModel", method = RequestMethod.POST)
		public String searchbyModel(@RequestParam("model") String modelName, Model model) throws CustomLogicException {
			
			ProductDTO product = productService.getByModel(modelName);
		
			if (product == null) {
				throw new CustomLogicException("Sorry! We do not have a model you're searching for. Please try again.");
			}
			
			int productId = product.getProductId();
			
			return "redirect:/viewProduct/" + productId;
			
		}*/

}
