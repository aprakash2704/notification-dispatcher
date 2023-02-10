package com.igtb.assessment.notificationdispatcher.model;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The Class NotificationRequest.
 */
public final class NotificationRequest {
	@NotEmpty(message = "message should not be empty")
	private final String message;
	
	@NotNull(message = "recipients should not be empty")
	@Size(min = 1, message = "request should contain atleast 1 recipient")
    private final List<String> recipients;
	
	@NotNull(message = "channel should not be empty")
    private final Channel channel;
    
	public enum Channel {
		Email, SMS, WhatsApp
	}
    
    public NotificationRequest(final String message, final List<String> recipients,
			final Channel channel) {
		this.message = message;
		this.recipients = recipients;
		this.channel = channel;
	}

    public String getMessage() {
      return message;
    }

    public List<String> getRecipients() {
      return recipients;
    }

    public Channel getChannel() {
      return channel;
    }
    
    @Override
	public int hashCode() {
		return Objects.hash(channel, message, recipients);
	}

    @Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final NotificationRequest other = (NotificationRequest) obj;
		return channel == other.channel
				&& Objects.equals(message, other.message)
				&& Objects.equals(recipients, other.recipients);
	}
}