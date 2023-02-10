package com.igtb.assessment.notificationdispatcher.channel.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.igtb.assessment.notificationdispatcher.model.NotificationRequest;
import com.igtb.assessment.notificationdispatcher.model.NotificationResponse;

/**
 * The Class EmailNotificationAdapter.
 */
@Service
public class EmailNotificationAdapter implements INotificationAdapter {
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(EmailNotificationAdapter.class);
	
  @Override
  public NotificationResponse send(final NotificationRequest notification) {
	  LOG.info("Email notification was sent successfully.");
	  return new NotificationResponse("Email notification was sent successfully.");
  }
}
 