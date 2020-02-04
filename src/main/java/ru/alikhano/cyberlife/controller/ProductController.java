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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;
import ru.alikhano.cyberlife.service.ProductService;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
	private static final Logger LOGGER = LogManager.getLogger(ProductController.class);
	
	private static final String VIEW = "viewProduct";

	/**
	 * @param model
	 * @return
	 */
	@GetMapping("/catalogue")
	public String getProducts(Model model) {
		List<ProductDTO> products = productService.getAll();
		model.addAttribute("products", products);

		return "productList";
	}

    
    /**
     * controller to view detailed product info
     * @param productId to retrieve the product from database
     * @param model
     * @return jsp file name
     */
    @GetMapping("/viewProduct/{productId}")
    public String viewProduct(@PathVariable("productId") int productId, Model model) {
    	ProductDTO productDTO;
    	try {
    		  productDTO = productService.getById(productId);
    	}
    	catch (CustomLogicException ex) {
    		LOGGER.error(ex.getErrMessage());
    		model.addAttribute("status", "No such product!");
    		List<ProductDTO> products = productService.getAll();
    		model.addAttribute("products", products);

    		return "productList";
    		
    	}
       
        model.addAttribute("product", productDTO);
        CartItemDTO cartItem = new CartItemDTO(); 
        model.addAttribute("newCartItem", cartItem);

        return VIEW;
    }
    
    /**
     * controller to add product to cart
     * @param productId
     * @param newCartItem object, containing details of a new cart item
     * @param result
     * @param request http request received from client side
     * @param model
     * @return redirect back to the catalogue
     * @throws CustomLogicException
     */
    @PostMapping(value = "/viewProduct")
    public String addToCart(@RequestParam("productId") int productId,
			@ModelAttribute("newCartItem") @Valid CartItemDTO newCartItem,
			BindingResult result, HttpServletRequest request, Model model) throws CustomLogicException {

        ProductDTO productDTO = productService.getById(productId);
        
        CartDTO cartDTO = cartService.getById(Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue()));
        
        if (newCartItem.getQuantity() > productDTO.getUnitsInStock()) {
        	model.addAttribute("error","Sorry, we do not have enough units in stock. Try again");
        	model.addAttribute("product", productDTO);
			LOGGER.error("Not enough units in stock");
			return VIEW;
        }       

		try {
			 cartItemService.create(productDTO, cartDTO, newCartItem);
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute("error","Plese check your input for negative values");
			model.addAttribute("product", productDTO);
			LOGGER.error("Negative values in user input");
			return VIEW;
		}
      
        cartService.update(cartDTO);
        
        LOGGER.info("new item has been added to cart: " + productDTO.getModel());

        return "redirect:/catalogue";
    }
}
