package com.igtb.assessment.notificationdispatcher;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.igtb.assessment.notificationdispatcher.channel.adapter.EmailNotificationAdapter;
import com.igtb.assessment.notificationdispatcher.channel.adapter.SMSNotificationAdapter;
import com.igtb.assessment.notificationdispatcher.channel.adapter.WhatsAppNotificationAdapter;
import com.igtb.assessment.notificationdispatcher.model.NotificationRequest;
import com.igtb.assessment.notificationdispatcher.model.NotificationRequest.Channel;
import com.igtb.assessment.notificationdispatcher.service.NotificationDispatchService;

/**
 * The Class NotificationDispatcherServiceTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class NotificationDispatcherServiceTest {
  
  /** The notification service. */
  @InjectMocks
  private NotificationDispatchService notificationService;
  
  /** The email notification adapter. */
  @Mock
  private EmailNotificationAdapter emailNotificationAdapter;
  
  /** The sms notification adapter. */
  @Mock
  private SMSNotificationAdapter smsNotificationAdapter;
  
  /** The whatsapp notification adapter. */
  @Mock
  private WhatsAppNotificationAdapter whatsAppNotificationAdapter;
  
  /**
	 * Test send email notification.
	 */
  @Test
  public void testSendEmailNotification() {
    final String message = "Hello, this is a test email notification.";
    final List<String> recipients = Arrays.asList("test@site.com", "igtb@igtb.com");
    final NotificationRequest request = new NotificationRequest(message, recipients, Channel.Email);
    notificationService.dispatch(request);
    
    // Verify if the email notification adapter was called with request
    verify(emailNotificationAdapter, times(1)).send(request);
    
    // Verify if the SMS notification adapter was called with request
    verify(smsNotificationAdapter, times(0)).send(request);
    
    // Verify if the WhatsApp notification adapter was called with request
    verify(whatsAppNotificationAdapter, times(0)).send(request);
  }
  
  /**
	 * Test send SMS notification.
	 */
  @Test
  public void testSendSMSNotification() {
    final String message = "Hello, this is a test SMS notification.";
    final List<String> recipients = Arrays.asList("+00000000000");
    final NotificationRequest request = new NotificationRequest(message, recipients, Channel.SMS);
    notificationService.dispatch(request);
    
    // Verify if the email notification adapter was called with request
    verify(emailNotificationAdapter, times(0)).send(request);
    
    // Verify if the SMS notification adapter was called with request
    verify(smsNotificationAdapter, times(1)).send(request);
    
    // Verify if the WhatsApp notification adapter was called with request
    verify(whatsAppNotificationAdapter, times(0)).send(request);
  }
  
  /**
	 * Test send whatsapp notification.
	 */
  @Test
  public void testSendWhatsappNotification() {
    final String message = "Hello, this is a test whatsapp notification.";
    final List<String> recipients = Arrays.asList("+00000000000");
    final NotificationRequest request = new NotificationRequest(message, recipients, Channel.WhatsApp);
    notificationService.dispatch(request);
    
    // Verify if the email notification adapter was called with request
    verify(emailNotificationAdapter, times(0)).send(request);
    
    // Verify if the SMS notification adapter was called with request
    verify(smsNotificationAdapter, times(0)).send(request);
    
    // Verify if the WhatsApp notification adapter was called with request
    verify(whatsAppNotificationAdapter, times(1)).send(request);
  }
}
