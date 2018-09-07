package ru.alikhano.cyberlife.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.service.CategoryService;
import ru.alikhano.cyberlife.service.ConsciousnessService;
import ru.alikhano.cyberlife.service.ProductService;

@Controller
public class AdminProductController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ConsciousnessService consService;

	private Path path;

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		ProductDTO newProductDTO = new ProductDTO();
		model.addAttribute("newProductDTO", newProductDTO);
		model.addAttribute("categoryDTOList", categoryService.getCategoryDTOList());
		model.addAttribute("consDTOList", consService.getConsDTOList());
		return "addProduct";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST, consumes = "multipart/form-data")
	public String addProductPost(@ModelAttribute("newProductDTO") ProductDTO newProductDTO, BindingResult result,
			HttpServletRequest request, @RequestPart("file") MultipartFile file) throws IOException {

		if (result.hasErrors()) {
			return "addProduct";
		}

		newProductDTO.setImage(file.getBytes());

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		path = Paths.get(rootDirectory + "/static/images/" + newProductDTO.getModel() + ".jpg");

		if (file != null && !file.isEmpty()) {
			try {
				file.transferTo(new File(path.toString()));
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException("Product image saving failed", ex);
			}
		}

		productService.addProduct(newProductDTO);

		return "redirect:/productList";
	}

	@RequestMapping("/productList")
	public String getProducts(Model model) {
		List<ProductDTO> products = productService.getProductDTOList();
		model.addAttribute("products", products);

		return "productList";
	}

	@RequestMapping("/editProduct/{productId}")
	public String editProduct(@PathVariable("productId") int productId, Model model) {
		ProductDTO productDTO = productService.getProductDTOById(productId);
		model.addAttribute("categoryDTOList", categoryService.getCategoryDTOList());
		model.addAttribute("consDTOList", consService.getConsDTOList());
		model.addAttribute("product", productDTO);

		return "editProduct";
	}

	@RequestMapping(value = "/editProduct", method = RequestMethod.POST)
	public String editProductPost(@Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult result,
			HttpServletRequest request) {
		productService.editProduct(productDTO);

		return "redirect:/productList";
	}

	@RequestMapping(value = "/deleteProduct/{productId}")
	public String deleteProduct(@PathVariable("productId") int productId, Model model) {
		productService.deleteProduct(productService.getProductDTOById(productId));

		return "redirect:/productList";
	}

}
