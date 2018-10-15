package ru.alikhano.cyberlife.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.alikhano.cyberlife.DTO.CategoryDTO;
import ru.alikhano.cyberlife.DTO.ConsDTO;
import ru.alikhano.cyberlife.service.CategoryService;
import ru.alikhano.cyberlife.service.ConsciousnessService;

@Controller
public class AdminController {
	

	private static final Logger logger = LogManager.getLogger(AdminController.class);

	@Autowired
	CategoryService categoryService;

	@Autowired
	ConsciousnessService consService;

	@RequestMapping("/admin/admin-home")
	public String home() {
		return "admin-home";
	}

	@RequestMapping("admin/addCategory")
	public String addCategory(Model model) {
		CategoryDTO categoryDTO = new CategoryDTO();
		model.addAttribute("categories", categoryService.getAll());
		model.addAttribute("newCategory", categoryDTO);
		return "addCategory";
	}

	@RequestMapping("admin/addCons")
	public String addCons(Model model) {
		ConsDTO consDTO = new ConsDTO();
		model.addAttribute("consLevels", consService.getAll());
		model.addAttribute("newCons", consDTO);
		return "addCons";
	}

	@RequestMapping(value = "admin/addCategory", method = RequestMethod.POST)
	public @ResponseBody String addCategoryPost(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult result,
			HttpServletRequest request, Model model) {
		
		try {
			categoryService.create(categoryDTO);
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute("error","Please check for duplicate entries");
			//model.addAttribute("categories", categoryService.getAll());
			logger.error(ex.getMessage() + " DUPLICATE category entry");
			return "addCategory";
		}
		

		return "Category has been added";
	}

	@RequestMapping(value = "admin/addCons", method = RequestMethod.POST)
	public @ResponseBody String addConsPost(@RequestBody @Valid ConsDTO consDTO, BindingResult result,
			HttpServletRequest request, Model model) {

		try {
			consService.create(consDTO);
		}
		catch (ConstraintViolationException ex) {
			model.addAttribute("error","Please check for duplicate entries");
			logger.error(ex.getMessage() + " DUPLICATE cons entry");
			return "addCategory";
		}
		
		

		return "AI config has been added";
	}

}
