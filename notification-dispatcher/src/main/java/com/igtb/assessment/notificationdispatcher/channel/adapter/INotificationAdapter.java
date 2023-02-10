package com.igtb.assessment.notificationdispatcher.channel.adapter;

import com.igtb.assessment.notificationdispatcher.model.NotificationRequest;
import com.igtb.assessment.notificationdispatcher.model.NotificationResponse;

/**
 * The Interface INotificationAdapter.
 */
public interface INotificationAdapter {
	
	/**
	 * Send.
	 *
	 * @param notification the notification
	 * @return the notification response
	 */
	NotificationResponse send(NotificationRequest notification);
}