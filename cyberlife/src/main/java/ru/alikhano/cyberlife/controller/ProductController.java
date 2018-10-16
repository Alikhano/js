package ru.alikhano.cyberlife.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;
import ru.alikhano.cyberlife.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CartItemService cartItemService;
	
	private static final Logger logger = LogManager.getLogger(ProductController.class);

	@RequestMapping("/catalogue")
	public String getProducts(Model model) {
		List<ProductDTO> products = productService.getAll();
		model.addAttribute("products", products);

		return "productCatalogue";
	}

    
    @RequestMapping("/viewProduct/{productId}")
    public String viewProduct(@PathVariable("productId") int productId, Model model) {
        ProductDTO productDTO = productService.getById(productId);
        model.addAttribute("product", productDTO);
        CartItemDTO cartItem = new CartItemDTO(); 
        model.addAttribute("newCartItem", cartItem);

        return "viewProduct";
    }
    
    @RequestMapping(value = "/viewProduct", method = RequestMethod.POST)
    public String addToCart(@RequestParam("productId") int productId, @ModelAttribute("newCartItem") @Valid CartItemDTO newCartItem, BindingResult result, HttpServletRequest request, Model model) throws CustomLogicException {
      
        
        ProductDTO productDTO = productService.getById(productId);
        
        CartDTO cartDTO = cartService.getById(Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue()));
        
        if (newCartItem.getQuantity() > productDTO.getUnitsInStock()) {
        	model.addAttribute("error","Sorry, we do not have enough units in stock. Try again");
        	model.addAttribute("product", productDTO);
			logger.error("Not enough units in stock");
			return "viewProduct";
        }
        

		try {
			 cartItemService.create(productDTO, cartDTO, newCartItem);
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute("error","Plese check your input for negative values");
			model.addAttribute("product", productDTO);
			logger.error("Negative values in user input");
			return "viewProduct";
		}
        
   
      
        cartService.update(cartDTO);
        
        logger.info("new item has been added to cart: " + productDTO.getModel());

        return "redirect:/catalogue";
    }
    
}
