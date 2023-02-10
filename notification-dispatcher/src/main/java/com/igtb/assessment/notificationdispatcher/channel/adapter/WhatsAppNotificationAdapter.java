package com.igtb.assessment.notificationdispatcher.channel.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.igtb.assessment.notificationdispatcher.model.NotificationRequest;
import com.igtb.assessment.notificationdispatcher.model.NotificationResponse;

/**
 * The Class WhatsAppNotificationAdapter.
 */
@Service
public class WhatsAppNotificationAdapter implements INotificationAdapter {
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(WhatsAppNotificationAdapter.class);
	
  @Override
  public NotificationResponse send(final NotificationRequest notification) {
	  LOG.info("Whatsapp notification was sent successfully.");
	  return new NotificationResponse("Whatsapp notification was sent successfully.");
  }
}
