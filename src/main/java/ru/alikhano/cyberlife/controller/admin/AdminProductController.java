package ru.alikhano.cyberlife.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.service.CategoryService;
import ru.alikhano.cyberlife.service.ConsciousnessService;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.OrderService;
import ru.alikhano.cyberlife.service.ProductService;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Controller
public class AdminProductController {

    private static final Logger LOGGER = LogManager.getLogger(AdminProductController.class);

    @Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ConsciousnessService consService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	/** 
	 * displays page to add new product
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/admin/addProduct")
	public String getAddNewProductForm(Model model) {
		model.addAttribute("newProductDTO", new ProductDTO());
		model.addAttribute("categoryDTOList", categoryService.getAll());
		model.addAttribute("consDTOList", consService.getAll());
		return "addProduct";
	}

	/**
	 * adds new product
	 * @param newProductDTO
	 * @param result
	 * @param request http request received from client side
	 * @param file image file
	 * @param model
	 * @return redirect to main admin page
	 * @throws IOException
	 * @throws CustomLogicException
	 */
	@PostMapping(value = "/admin/addProduct", consumes = "multipart/form-data")
	public String addProductPost(@ModelAttribute("newProductDTO") @Valid ProductDTO newProductDTO, BindingResult result,
			HttpServletRequest request, @RequestPart("file") MultipartFile file, Model model)
			throws IOException, CustomLogicException {

	    String productModel = newProductDTO.getModel();

        if (productService.isProductExistingById(newProductDTO.getProductId())
				|| productService.isProductExistingByModel(productModel)) {
            model.addAttribute("error", "Oops, this model exists already");
            LOGGER.error("Oops, this model exists already");
            return "addProduct";
        }

		newProductDTO.setImage(file.getBytes());
        saveProductImage(request, productModel, file);

        productService.create(newProductDTO);

        model.addAttribute("status", "New product: " + productModel);

        LOGGER.info("New product: " + productModel);

        return "redirect:/admin/productList";
    }

	/**
	 * displays inventory list
	 * @param model
	 * @return jsp file name
	 */
	@GetMapping("/admin/productList")
	public String getProducts(Model model) {
		model.addAttribute("products", productService.getAll());

		return "productList";
	}

	/**
	 * displays page to edit a product entry
	 * @param productId id of a product to be edited
	 * @param model
	 * @return jsp file name
	 */
	@GetMapping("/admin/editProduct/{productId}")
	public String editProduct(@PathVariable("productId") int productId, Model model) {
		ProductDTO productDTO;
		try {
		productDTO = productService.getById(productId);
		}
		catch(CustomLogicException ex) {
			LOGGER.error(ex.getErrMessage());
			model.addAttribute("status", "No such product!");

			return getProducts(model);
		}
		model.addAttribute("categoryDTOList", categoryService.getAll());
		model.addAttribute("consDTOList", consService.getAll());
		model.addAttribute("product", productDTO);

		return "editProduct";
	}

	/**
	 * changes a product entry in database
	 * @param productDTO instance of ProductDTO with info about new product entry
	 * @param result
	 * @param request http request received from client side
	 * @param model
	 * @return redirect to inventory list
	 * @throws CustomLogicException
	 * @throws IOException
	 * @throws TimeoutException
	 */
	@PostMapping(value = "/admin/editProduct")
	public String editProductPost(@Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult result,
			HttpServletRequest request, Model model) throws CustomLogicException, IOException, TimeoutException {
		String opResult = productService.update(productDTO);	

		if (opResult.equals("negative units")) {
			model.addAttribute("error", "There should > 0 units in stock!");
			model.addAttribute("categoryDTOList", categoryService.getAll());
			model.addAttribute("consDTOList", consService.getAll());
			LOGGER.error("There should > 0 units in stock!");
			return "editProduct";
		}
		if (opResult.equals("negative price")) {
			model.addAttribute("error", "Price should be > 0!");
			model.addAttribute("categoryDTOList", categoryService.getAll());
			model.addAttribute("consDTOList", consService.getAll());
			LOGGER.error("Price should be > 0!");
			return "editProduct";
			
		}
		return "redirect:/admin/productList";
	}

	/**
	 * enables deletion of a product entry
	 * @param productId
	 * @param model
	 * @return jsp file name 
	 * @throws CustomLogicException
	 * @throws IOException
	 * @throws TimeoutException
	 */
	@GetMapping(value = "/admin/deleteProduct/{productId}")
	public String deleteProduct(@PathVariable("productId") int productId, Model model) throws CustomLogicException, IOException, TimeoutException {
		
		String result;
		try {
			result = productService.delete(productService.getById(productId));
		}
		catch (CustomLogicException ex) {
			LOGGER.error(ex.getErrMessage());
			model.addAttribute("status", "No such product!");
			List<ProductDTO> products = productService.getAll();
			model.addAttribute("products", products);

			return "productList";

		}
	
		if (result.equals("failed")) {
			LOGGER.error("You cannot delete a product that has not been delivered yet!");
			model.addAttribute("status", "Not delivered yet! " + productService.getById(productId).getModel());
			List<ProductDTO> products = productService.getAll();
			model.addAttribute("products", products);

			return "productList";
		}
		
		LOGGER.info("Product has been deleted");
		return "redirect:/admin/productList";
	}
	
	/**
	 * displays top 10 products
	 * @param model
	 * @return jsp file name
	 */
	@GetMapping(value="/admin/stats") 
	public String getTopProducts(Model model) {
		model.addAttribute("topProduct", productService.getTopProducts());
		model.addAttribute("topCustomer", customerService.getTopCustomers());
		model.addAttribute("monthlyRev", orderService.getMonthlyRevenue());

		double weeklyRev = orderService.getWeeklyRevenue();

		if (weeklyRev == 0) {
			model.addAttribute("weeklyNo", "nothing to show here, try later");
		}

		model.addAttribute("weeklyRev", orderService.getWeeklyRevenue() + " USD");
		
		return "stats";
	}

	private void saveProductImage(HttpServletRequest request, String productModel, MultipartFile productImage)
            throws CustomLogicException {

        String rootDirectory = request.getSession().getServletContext().getInitParameter("uploadDirectory");
        File uploads = new File(rootDirectory, productModel + ".jpg");

        if (uploads.exists() && !uploads.isDirectory()) {
        	LOGGER.info("The picture for this model exists; skip saving new file");
        	return;
		}

        try (InputStream input = productImage.getInputStream()) {
			Files.copy(input, uploads.toPath());
		}
        catch (IOException ex) {
			throw new CustomLogicException("Product image saving failed, try again later");
		}
    }
}
