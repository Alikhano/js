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

import ru.alikhano.cyberlife.dto.CategoryDTO;
import ru.alikhano.cyberlife.dto.ConsciousnessDTO;
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

	private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ConsciousnessService consService;

	@GetMapping("/admin/admin-home")
	public String home() {
		return "stats";
	}


	@GetMapping("admin/addCategory")
	public String showCategoryPage(Model model) {
		CategoryDTO categoryDTO = new CategoryDTO();
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("newCategory", categoryDTO);
		return "addCategory";
	}


	@GetMapping("admin/addCons")
	public String showConsciousnessPage(Model model) {
		ConsciousnessDTO consciousnessDTO = new ConsciousnessDTO();
		model.addAttribute("consLevels", consService.getAll());
		model.addAttribute("newCons", consciousnessDTO);
		return "addCons";
	}

	@PostMapping(value = "admin/addCategory", produces="application/json")
	public ResponseEntity<?> addNewCategory(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult result,
			HttpServletRequest request, Model model) throws CustomLogicException {
		try {
			categoryService.create(categoryDTO);
		} catch (ConstraintViolationException ex) {
			LOGGER.error("DUPLICATE category entry");
			throw new CustomLogicException("duplicate entry");
		}
		LOGGER.info(String.format("New category: %s", categoryDTO.getCategoryType()));

		return ResponseEntity.ok(categoryDTO);
	}

	@PostMapping(value = "admin/addCons",  produces="application/json")
	public ResponseEntity<?> addNewConsciousnessLevel(@RequestBody @Valid ConsciousnessDTO consciousnessDTO, BindingResult result,
			HttpServletRequest request, Model model) throws CustomLogicException {
		try {
			consService.create(consciousnessDTO);
		}
		catch (ConstraintViolationException ex) {
			LOGGER.error("DUPLICATE cons entry");
			throw new CustomLogicException("duplicate entry");
		}
		LOGGER.info(String.format("New AI config: %s", consciousnessDTO.getLevel()));

		return ResponseEntity.ok(consciousnessDTO);
	}
}
