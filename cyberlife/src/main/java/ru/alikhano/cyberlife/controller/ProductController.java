package ru.alikhano.cyberlife.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/product")
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "product";
	}
	
	@RequestMapping(value="/product", method=RequestMethod.POST)
	public String addProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request) {
		productService.addProduct(product);
		
		return "redirect:/product-all";
	}
	
	@RequestMapping("/product-all")
	public String listProduct(Model model) {
		List<Product> listProduct = productService.getAllProducts();
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("product", new Product());
		
		
		return "product";	
	}
	
	@RequestMapping(value="/product/delete/{id}")
	public String deleteCourse(@PathVariable("id") int id, Model model)
	{
		productService.deleteProduct(productService.getProductById(id));
		List<Product> listProduct = productService.getAllProducts();
		model.addAttribute("listProduct", listProduct);
		
		return "redirect:/product-all";
	}
	
	@RequestMapping("/product/edit/{id}")
	public String editProduct(@PathVariable("id") int id,Model model) {
		Product product = productService.getProductById(id);
		
		model.addAttribute("product", product);
		
		return "edit-product";
	}
	
	@RequestMapping(value="/product/edit", method=RequestMethod.POST)
	public String editProductPost(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request) {
		productService.updateProduct(product);
		
		return "redirect:/product-all";
	}
	
	

}

