package com.igtb.assessment.notificationdispatcher.channel.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.igtb.assessment.notificationdispatcher.model.NotificationRequest;
import com.igtb.assessment.notificationdispatcher.model.NotificationResponse;

/**
 * The Class SMSNotificationAdapter.
 */
@Service
public class SMSNotificationAdapter implements INotificationAdapter {
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(SMSNotificationAdapter.class);

  @Override
  public NotificationResponse send(final NotificationRequest notification) {
	  LOG.info("SMS notification was sent successfully.");
	  return new NotificationResponse("SMS notification was sent successfully.");
  }
}

