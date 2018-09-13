package ru.alikhano.cyberlife.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.alikhano.cyberlife.DTO.CategoryDTO;
import ru.alikhano.cyberlife.DTO.ConsDTO;
import ru.alikhano.cyberlife.service.CategoryService;
import ru.alikhano.cyberlife.service.ConsciousnessService;

@Controller
public class AdminController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ConsciousnessService consService;
	
	@RequestMapping("/admin/admin-home")
	public String home() {
		return "admin-home";
	}
	
	@RequestMapping("admin/addCategory")
    public String addCategory( Model model) {
		CategoryDTO categoryDTO = new CategoryDTO();
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("newCategory", categoryDTO);
        return "addCategory";
    }
	
	@RequestMapping(value="admin/addCategory", method = RequestMethod.POST)
    public String addCategoryPost(@ModelAttribute("newCategory") CategoryDTO categoryDTO, BindingResult result,
			HttpServletRequest request) {
		categoryService.create(categoryDTO);
		
        return "redirect:/admin/admin-home";
    }
	
	@RequestMapping("admin/addCons")
    public String addCons( Model model) {
		ConsDTO consDTO = new ConsDTO();
        model.addAttribute("consLevels", consService.getAll());
        model.addAttribute("newCons",consDTO);
        return "addCons";
    }
	
	@RequestMapping(value="admin/addCons", method = RequestMethod.POST)
    public String addCons(@ModelAttribute("newCons") ConsDTO consDTO, BindingResult result,
			HttpServletRequest request) {
		consService.create(consDTO);
		
        return "redirect:/admin/admin-home";
    }

}
