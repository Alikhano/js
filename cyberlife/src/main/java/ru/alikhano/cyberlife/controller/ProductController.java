package ru.alikhano.cyberlife.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;

	@RequestMapping("/catalogue")
	public String getProducts(Model model) {
		List<ProductDTO> products = productService.getProductDTOList();
		model.addAttribute("products", products);

		return "productCatalogue";
	}


    
    @RequestMapping("/viewProduct/{productId}")
    public String viewProduct(@PathVariable("productId") int productId, Model model) throws IOException{
        ProductDTO productDTO = productService.getProductDTOById(productId);
        model.addAttribute("product", productDTO);

        return "viewProduct";
    }
}
