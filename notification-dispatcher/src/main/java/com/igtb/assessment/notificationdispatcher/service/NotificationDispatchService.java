package com.igtb.assessment.notificationdispatcher.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.igtb.assessment.notificationdispatcher.channel.adapter.EmailNotificationAdapter;
import com.igtb.assessment.notificationdispatcher.channel.adapter.INotificationAdapter;
import com.igtb.assessment.notificationdispatcher.channel.adapter.SMSNotificationAdapter;
import com.igtb.assessment.notificationdispatcher.channel.adapter.WhatsAppNotificationAdapter;
import com.igtb.assessment.notificationdispatcher.model.NotificationRequest;
import com.igtb.assessment.notificationdispatcher.model.NotificationRequest.Channel;
import com.igtb.assessment.notificationdispatcher.model.NotificationResponse;

/**
 * The Class NotificationDispatchService.
 */
@Service
public class NotificationDispatchService {
	
	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(NotificationDispatchService.class);
	
	/** The notification adapter map. */
	private final Map<Channel, INotificationAdapter> notificationAdapterMap;

	/**
	 * Instantiates a new notification dispatch service.
	 *
	 * @param emailNotificationAdapter the email notification adapter
	 * @param smsNotificationAdapter the sms notification adapter
	 * @param whatsAppNotificationAdapter the whatsapp notification adapter
	 */
	@Autowired
	public NotificationDispatchService(final EmailNotificationAdapter emailNotificationAdapter,
			final SMSNotificationAdapter smsNotificationAdapter,
			final WhatsAppNotificationAdapter whatsAppNotificationAdapter) {
		notificationAdapterMap = new HashMap<>();
		notificationAdapterMap.put(Channel.Email, emailNotificationAdapter);
		notificationAdapterMap.put(Channel.SMS, smsNotificationAdapter);
		notificationAdapterMap.put(Channel.WhatsApp, whatsAppNotificationAdapter);
	}

	/**
	 * Dispatch the notification to the respective channel adapter.
	 *
	 * @param notificationRequest
	 *                                the notification request
	 * @return the notification response
	 */
	public NotificationResponse dispatch(final NotificationRequest notificationRequest) {
		final INotificationAdapter adapter = notificationAdapterMap.get(notificationRequest.getChannel());
		LOG.info(
				"Request has incoming channel: {}, process started to dispatch the notification to adapter: {}",
				notificationRequest.getChannel(), adapter.getClass().getSimpleName());
		return adapter.send(notificationRequest);
	}
}