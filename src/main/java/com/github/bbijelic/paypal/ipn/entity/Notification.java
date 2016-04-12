package com.github.bbijelic.paypal.ipn.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Notification.
 *
 * @author Bojan Bijelic
 */
@Entity
@Table(name = "paypal_notification")
public class Notification {

	/**
	 * ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	/**
	 * ID getter.
	 * 
	 * @return the id.
	 */
	public long getId() {
		return id;
	}

	/**
	 * ID setter.
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Active from.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "received_at")
	private Date receivedAt;

	/**
	 * Received at getter
	 * 
	 * @return the time of reception
	 */
	public Date getReceivedAt() {
		return receivedAt;
	}

	/**
	 * Transaction ID.
	 */
	@Column(name = "transaction_id")
	private String transactionId;

	/**
	 * Transaction id getter
	 * 
	 * @return the transaction id
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * Transaction id setter
	 * 
	 * @param transactionId
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Reception time setter
	 * 
	 * @param receivedAt
	 */
	public void setReceivedAt(Date receivedAt) {
		this.receivedAt = receivedAt;
	}

	/**
	 * Raw message parameters
	 */
	@Column(name = "raw_parameters")
	private String rawParameters;

	/**
	 * Raw parameters getter
	 * 
	 * @return the raw parameters
	 */
	public String getRawParameters() {
		return rawParameters;
	}

	/**
	 * Raw parameters setter
	 * 
	 * @param rawParameters
	 */
	public void setRawParameters(String rawParameters) {
		this.rawParameters = rawParameters;
	}

	/**
	 * Message status
	 */
	@Column(name = "status")
	private NotificationStatus status;

	/**
	 * Notification status getter
	 * 
	 * @return the notification status
	 */
	public NotificationStatus getStatus() {
		return status;
	}

	/**
	 * Notification status setter
	 * 
	 * @param status
	 */
	public void setStatus(NotificationStatus status) {
		this.status = status;
	}

	/**
	 * Completed at
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "completed_at")
	private Date completedAt;

	/**
	 * Message verification completed at.
	 * 
	 * @return the time message verification is completed.
	 */
	public Date getCompletedAt() {
		return completedAt;
	}

	/**
	 * Message verification completed at setter
	 * 
	 * @param completedAt
	 */
	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	/**
	 * Notification version
	 */
	@Column(name = "notify_version")
	private String notificationVersion;

	/**
	 * Notification version getter.
	 * 
	 * @return the notification version
	 */
	public String getNotificationVersion() {
		return notificationVersion;
	}

	/**
	 * Notification version setter.
	 * 
	 * @param notificationVersion
	 */
	public void setNotificationVersion(String notificationVersion) {
		this.notificationVersion = notificationVersion;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", receivedAt=" + receivedAt + ", transactionId=" + transactionId
				+ ", rawParameters=" + rawParameters + ", status=" + status + ", completedAt=" + completedAt
				+ ", notificationVersion=" + notificationVersion + "]";
	}

}
