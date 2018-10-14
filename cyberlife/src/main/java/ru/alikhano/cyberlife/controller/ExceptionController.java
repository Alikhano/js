package ru.alikhano.cyberlife.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import ru.alikhano.cyberlife.DTO.CustomLogicException;

@ControllerAdvice
public class ExceptionController {
	
	private static final Logger logger = LogManager.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleGenericException(Exception ex) {
		
		logger.error(ex.getMessage(), ex);
		ModelAndView model = new ModelAndView("generic_error");
		
		model.addObject("errorMessage", ex.getMessage());
		
		return model;
	}
	
	@ExceptionHandler(CustomLogicException.class)
	/*@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody*/
	public ResponseEntity<?> handleCustomException(CustomLogicException ex) {
		
		logger.error(ex.getErrMessage(), ex);
		
		return ResponseEntity.badRequest().body(ex.getErrMessage());
	}
	

}
