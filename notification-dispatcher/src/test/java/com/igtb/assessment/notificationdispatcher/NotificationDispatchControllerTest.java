package com.igtb.assessment.notificationdispatcher;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.igtb.assessment.notificationdispatcher.controller.NotificationDispatchController;
import com.igtb.assessment.notificationdispatcher.model.NotificationRequest;
import com.igtb.assessment.notificationdispatcher.model.NotificationRequest.Channel;
import com.igtb.assessment.notificationdispatcher.model.NotificationResponse;
import com.igtb.assessment.notificationdispatcher.service.NotificationDispatchService;
import com.igtb.assessment.notificationdispatcher.validation.NotificationRequestValidator;

/**
 * The Class NotificationDispatchControllerTest.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(NotificationDispatchController.class)
public class NotificationDispatchControllerTest {
  
  /** The mock mvc. */
  @Autowired
  private MockMvc mockMvc;
  
  /** The notification service. */
  @MockBean
  private NotificationDispatchService notificationService;
  
  /** The validator. */
  @MockBean
  private NotificationRequestValidator validator;
  
  /**
	 * Test send notification.
	 *
	 * @throws Exception  the exception
	 */
  @Test
  public void testSendNotification() throws Exception {
    final String message = "Hello, this is a test notification.";
    final List<String> recipients = Arrays.asList("igtb@igtb.com");
    final NotificationRequest request = new NotificationRequest(message, recipients, Channel.Email);
    
    final ObjectMapper mapper = new ObjectMapper();
    final ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
	final String json = writer.writeValueAsString(request);
    
    when(notificationService.dispatch(request)).thenReturn(new NotificationResponse(Channel.Email + " notification was sent successfully."));
    mockMvc.perform(post("/notifications").header("Authorization", "Basic aUdUQlVzZXI6aUdUQlBhc3N3b3Jk")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json))
      .andExpect(status().isOk());
    verify(notificationService).dispatch(request);
  }
  
  /**
	 * Test fail send notification for un authorized credentials.
	 *
	 * @throws Exception the exception
	 */
  @Test
  public void testFailSendNotificationForUnAuthorizedCredentials() throws Exception {
//    final String message = "Hello, this is a test notification.";
//    final List<String> recipients = Arrays.asList("igtb@igtb.com");
//    final NotificationRequest request = new NotificationRequest(message, recipients, Channel.Email);
//    
//    final ObjectMapper mapper = new ObjectMapper();
//    final ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
//	final String json = writer.writeValueAsString(request);
	
	final String reqJson = "{\n" + "\"message\": \"dd\",\n"
			+ "	\"recipients\": [\"sss@dd.com\"],\n"
			+ "	\"channel\": \"Email\"\n" + "}";

    mockMvc.perform(post("/notifications").header("Authorization", "Basic aUdUQlVzZXI6aUdUQlBhc3N3b3J")
      .contentType(MediaType.APPLICATION_JSON)
      .content(reqJson))
      .andExpect(status().isUnauthorized());
  }
  
  /**
	 * Test fail send notification bad request.
	 *
	 * @throws Exception the exception
	 */
  @Test
  public void testFailSendNotificationBadRequest() throws Exception {
    final String message = "";
    final List<String> recipients = Arrays.asList("igtb@igtb.com");
    final NotificationRequest request = new NotificationRequest(message, recipients, Channel.Email);
    
    final ObjectMapper mapper = new ObjectMapper();
    final ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
    final String json = writer.writeValueAsString(request);

    mockMvc.perform(post("/notifications").header("Authorization", "Basic aUdUQlVzZXI6aUdUQlBhc3N3b3Jk")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json))
      .andExpect(status().isBadRequest());
  }
  
  /**
	 * Test fail send notification bad request with invalid channel.
	 *
	 * @throws Exception  the exception
	 */
  @Test
  public void testFailSendNotificationBadRequestWithInvalidChannel() throws Exception {
	  final String reqJson = "{\n" + "\"message\": \"dd\",\n"
				+ "	\"recipients\": [\"sss@dd.com\"],\n"
				+ "	\"channel\": \"Telegram\"\n" + "}";

    mockMvc.perform(post("/notifications").header("Authorization", "Basic aUdUQlVzZXI6aUdUQlBhc3N3b3Jk")
      .contentType(MediaType.APPLICATION_JSON)
      .content(reqJson))
      .andExpect(status().isBadRequest());
  }
}
