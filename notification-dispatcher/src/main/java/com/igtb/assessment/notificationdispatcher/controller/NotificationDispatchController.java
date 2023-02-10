package com.igtb.assessment.notificationdispatcher.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igtb.assessment.notificationdispatcher.model.NotificationRequest;
import com.igtb.assessment.notificationdispatcher.model.NotificationResponse;
import com.igtb.assessment.notificationdispatcher.service.NotificationDispatchService;
import com.igtb.assessment.notificationdispatcher.validation.NotificationRequestValidator;

@RestController
@RequestMapping("/notifications")
// in real time the documentation for the below endpoint(s) could be done via
// swagger which is more user friendly
public class NotificationDispatchController {
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(NotificationDispatchController.class);
	
	private final NotificationRequestValidator validator;

    @Autowired
    public NotificationDispatchController(final NotificationRequestValidator validator) {
        this.validator = validator;
    }

	@Autowired
	private NotificationDispatchService notificationDispatcher;

	@PostMapping
	public ResponseEntity<String> sendNotification(@Validated @RequestBody final NotificationRequest notificationRequest,
			@RequestHeader("Authorization") final String authorization, final BindingResult bindingResult) {
		// validate the incoming request against with our custom validator
		validator.validate(notificationRequest, bindingResult);
		// stop the process if there are validation errors, it's a no go.
		if (bindingResult.hasErrors()) {
			LOG.error("Encountered following validation errors: {}" + bindingResult.getAllErrors());
			return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
		}
		
		final NotificationResponse response = notificationDispatcher.dispatch(notificationRequest);
		// notification is successfully dispatched to the respective channel
		// adapter, now the response is sent back to the caller.
		return ResponseEntity.ok(response.getMessage());
	}
}