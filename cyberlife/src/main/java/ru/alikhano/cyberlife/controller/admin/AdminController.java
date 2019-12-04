package ru.alikhano.cyberlife.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.alikhano.cyberlife.dto.CategoryDTO;
import ru.alikhano.cyberlife.dto.ConsDTO;
import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.service.CategoryService;
import ru.alikhano.cyberlife.service.ConsciousnessService;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Controller
public class AdminController {

	private static final Logger logger = LogManager.getLogger(AdminController.class);

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ConsciousnessService consService;

	@RequestMapping("/admin/admin-home")
	public String home() {
		return "admin-home";
	}

	/**
	 * @param model
	 * @return jsp file name
	 */
	@GetMapping("admin/addCategory")
	public String addCategory(Model model) {
		CategoryDTO categoryDTO = new CategoryDTO();
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("newCategory", categoryDTO);
		return "addCategory";
	}

	/**
	 * @param model
	 * @return jsp file name
	 */
	@GetMapping("admin/addCons")
	public String addCons(Model model) {
		ConsDTO consDTO = new ConsDTO();
		model.addAttribute("consLevels", consService.getAll());
		model.addAttribute("newCons", consDTO);
		return "addCons";
	}

	/**
	 * @param categoryDTO instance of CategoryDTO class, containing info about new category
	 * @param result
	 * @param request http request received from client side
	 * @param model
	 * @return jsp file name
	 * @throws CustomLogicException for duplicate entry
	 */
	@PostMapping(value = "admin/addCategory",  produces="application/json")
	public ResponseEntity<?> addCategoryPost(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult result,
			HttpServletRequest request, Model model) throws CustomLogicException {
		
		try {
			categoryService.create(categoryDTO);
		}
		catch (ConstraintViolationException ex) {
			logger.error(ex.getMessage() + " DUPLICATE category entry");
			throw new CustomLogicException("duplicate entry");
		}
		
		logger.info("New category: " + categoryDTO.getCategoryType());
		
		  return ResponseEntity.ok(categoryDTO);
	}

	/**
	 * @param consDTO instance of ConsDTO class, containing info about new category
	 * @param result
	 * @param request http request received from client side
	 * @param model
	 * @return jsp file name
	 * @throws CustomLogicException for duplicate entry
	 */
	@PostMapping(value = "admin/addCons",  produces="application/json")
	public ResponseEntity<?> addConsPost(@RequestBody @Valid ConsDTO consDTO, BindingResult result,
			HttpServletRequest request, Model model) throws CustomLogicException {

		try {
			consService.create(consDTO);
		}
		catch (ConstraintViolationException ex) {
			logger.error(ex.getMessage() + " DUPLICATE cons entry");
			throw new CustomLogicException("duplicate entry");
		}
		
		logger.info("New AI config: " + consDTO.getLevel());

		return ResponseEntity.ok(consDTO);
	}
}
