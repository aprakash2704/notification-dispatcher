package com.igtb.assessment.notificationdispatcher.validation;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.igtb.assessment.notificationdispatcher.model.NotificationRequest.Channel;

/**
 * The Class GlobalExceptionHandler.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * Handle invalid format exception.
	 *
	 * @param response the response
	 * @param ex the ex
	 * @return the response entity
	 * @throws IOException
	 *                         Signals that an I/O exception has occurred.
	 */
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<Object> handleInvalidFormatException(final HttpServletResponse response, final InvalidFormatException ex) throws IOException {
	    if (ex.getTargetType().isAssignableFrom(Channel.class)) {
			return new ResponseEntity<>("Channel is unknown. Channel should be one of: "
							+ Arrays.asList(Channel.values()),
					HttpStatus.BAD_REQUEST);
	    } 
	    
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
