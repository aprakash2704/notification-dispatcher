package com.igtb.assessment.notificationdispatcher.model;

/**
 * The Class NotificationResponse.
 */
public final class NotificationResponse {
	private final String message;
	
    public NotificationResponse(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}