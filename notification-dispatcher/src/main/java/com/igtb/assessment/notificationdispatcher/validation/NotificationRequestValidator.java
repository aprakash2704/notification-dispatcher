package com.igtb.assessment.notificationdispatcher.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.igtb.assessment.notificationdispatcher.model.NotificationRequest;
import com.igtb.assessment.notificationdispatcher.model.NotificationRequest.Channel;

/**
 * The Class NotificationRequestValidator - custom spring validator to validate the notification request.
 */
@Component
public class NotificationRequestValidator implements Validator {
    
	@Override
	public boolean supports(final Class<?> clazz) {
		return NotificationRequest.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		final NotificationRequest request = (NotificationRequest) target;
		final Channel channel = request.getChannel();
		switch (channel) {
			case Email :
				for (final String recipient : request.getRecipients()) {
					if (!EmailValidator.validateEmail(recipient)) {
						errors.rejectValue("recipients", "email.invalid", "Invalid Email Format");;
					}
				}
				break;
			case SMS :
			case WhatsApp :
				for (final String recipient : request.getRecipients()) {
					if (!PhoneNumberValidator.isValid(recipient)) {
						errors.rejectValue("recipients", "phonenumber.invalid", "Invalid Phone number");;
					}
				}
				break;
		}
	}
}