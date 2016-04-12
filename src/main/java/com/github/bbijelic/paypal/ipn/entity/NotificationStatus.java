package com.github.bbijelic.paypal.ipn.entity;

/**
 * Notification status
 *
 * @author Bojan Bijelic
 */
public enum NotificationStatus {

	PENDING("PENDING"), VERIFIED("VERIFIED"), INVALID("INVALID");

	private final String text;

	/**
	 * @param text
	 */
	private NotificationStatus(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
