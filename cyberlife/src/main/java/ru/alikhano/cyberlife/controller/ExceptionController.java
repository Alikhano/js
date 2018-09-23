package ru.alikhano.cyberlife.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import ru.alikhano.cyberlife.DTO.CustomException;

@ControllerAdvice
public class ExceptionController {
	
	private static final Logger logger = LogManager.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleGenericException(Exception ex) {
		
		logger.error(ex.getMessage(), ex);
		ModelAndView model = new ModelAndView("/generic_error");
		
		model.addObject("errorMessage", ex.getMessage());
		
		return model;
	}
	
	@ExceptionHandler(CustomException.class)
	public ModelAndView handleCustomException(CustomException ex) {
		
		logger.error(ex.getErrMessage(), ex);
		ModelAndView model = new ModelAndView("/generic-error");
		
		model.addObject("errorMessage", ex.getErrMessage());
		
		return model;
	}

}
