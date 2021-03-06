package ru.alikhano.cyberlife.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.ModelAndView;

import ru.alikhano.cyberlife.dto.CustomLogicException;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@ControllerAdvice
public class ExceptionController {

	private static final Logger LOGGER = LogManager.getLogger(ExceptionController.class);

	/**
	 * handler for all exceptions
	 * @param ex instance of an exception
	 * @return view to display
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleGenericException(Exception ex) {

		LOGGER.error(ex.getMessage(), ex);
		ModelAndView model = new ModelAndView("generic_error");

		model.addObject("errorMessage", ex.getMessage());

		return model;
	}
	
	/**
	 * handler for custom exceptions
	 * @param ex instance of a custom exception
	 * @return error message in a body of an http response
	 */
	@ExceptionHandler(CustomLogicException.class)
	public ResponseEntity<?> handleCustomException(CustomLogicException ex) {

		LOGGER.error(ex.getErrMessage(), ex);

		return ResponseEntity.badRequest().body(ex.getErrMessage());
	}

}
