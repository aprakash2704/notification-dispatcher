package com.igtb.assessment.notificationdispatcher;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.igtb.assessment.notificationdispatcher.model.NotificationRequest;
import com.igtb.assessment.notificationdispatcher.model.NotificationRequest.Channel;
import com.igtb.assessment.notificationdispatcher.validation.NotificationRequestValidator;

/**
 * The Class NotificationRequestValidatorTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationRequestValidatorTest {

	/** The validator. */
	@Autowired
	private NotificationRequestValidator validator;

	/**
	 * Test channel email validator.
	 */
	@Test
	public void testChannelEmailValidator() {
		NotificationRequest request = new NotificationRequest("test message",
				Arrays.asList("test@igtb.com", "test1"), Channel.Email);

		Errors errors = new BeanPropertyBindingResult(request, "notificationRequest");
		validator.validate(request, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertEquals("Invalid Email Format",
				errors.getFieldError().getDefaultMessage());
		Assert.assertEquals(errors.getFieldErrors().size(), 1);
		
		request = new NotificationRequest("test message",
				Arrays.asList("test@igtb.com", "test1@test.com"), Channel.Email);

		errors = new BeanPropertyBindingResult(request, "notificationRequest");
		validator.validate(request, errors);
		Assert.assertFalse(errors.hasErrors());
		Assert.assertEquals(errors.getFieldErrors().size(), 0);
	}
	
	/**
	 * Test channel phone and whatsapp validator.
	 */
	@Test
	public void testChannelPhoneAndWhatsappValidator() {
		NotificationRequest request = new NotificationRequest("test message",
				Arrays.asList("number", "+980998098908", "+111111111111111111111111111"), Channel.SMS);

		Errors errors = new BeanPropertyBindingResult(request, "notificationRequest");
		validator.validate(request, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertEquals("Invalid Phone number",
				errors.getFieldError().getDefaultMessage());
		Assert.assertEquals(errors.getFieldErrors().size(), 2);
		
		request = new NotificationRequest("test message",
				Arrays.asList("+980998098908"), Channel.SMS);
		errors = new BeanPropertyBindingResult(request, "notificationRequest");
		validator.validate(request, errors);
		Assert.assertFalse(errors.hasErrors());
		Assert.assertEquals(errors.getFieldErrors().size(), 0);
		
		request = new NotificationRequest("test message",
				Arrays.asList("$$$$", "+980998098908"), Channel.WhatsApp);
		errors = new BeanPropertyBindingResult(request, "notificationRequest");
		validator.validate(request, errors);
		Assert.assertTrue(errors.hasErrors());
		Assert.assertEquals("Invalid Phone number",
				errors.getFieldError().getDefaultMessage());
		Assert.assertEquals(errors.getFieldErrors().size(), 1);
		
		request = new NotificationRequest("test message",
				Arrays.asList("+280998098908", "+980998098908"), Channel.WhatsApp);
		errors = new BeanPropertyBindingResult(request, "notificationRequest");
		validator.validate(request, errors);
		Assert.assertFalse(errors.hasErrors());
		Assert.assertEquals(errors.getFieldErrors().size(), 0);
	}
}
