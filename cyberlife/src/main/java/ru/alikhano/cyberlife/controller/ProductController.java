package ru.alikhano.cyberlife.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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
    public String addToCart(@RequestParam("productId") int productId, @ModelAttribute("newCartItem") CartItemDTO newCartItem, BindingResult result, HttpServletRequest request, Model model) throws CustomLogicException {
        ProductDTO productDTO = productService.getById(productId);
        
        if (newCartItem.getQuantity() < 0) {
        	throw new CustomLogicException("Quantity should be > 0!");
        }
        
        int quantity = newCartItem.getQuantity();
        
        double totalPrice = quantity * productDTO.getPrice();
        newCartItem.setTotalPrice(totalPrice);
        
        newCartItem.setProduct(productDTO);
        
        CartDTO cartDTO = cartService.getById(Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue()));
        Set<CartItemDTO> items = cartDTO.getItems();
        items.add(newCartItem);
        cartDTO.setItems(items);
        
        
        cartDTO.setGrandTotal(newCartItem.getTotalPrice() + cartDTO.getGrandTotal());
        newCartItem.setCart(cartDTO);
        
        cartItemService.create(newCartItem);
        cartService.update(cartDTO);

        return "redirect:/catalogue";
    }
    
}
