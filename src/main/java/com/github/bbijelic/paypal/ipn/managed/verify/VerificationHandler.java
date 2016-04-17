package com.github.bbijelic.paypal.ipn.managed.verify;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.paypal.ipn.config.VerificationManagerConfiguration;
import com.github.bbijelic.paypal.ipn.db.dao.NotificationDAO;
import com.github.bbijelic.paypal.ipn.entity.Notification;
import com.github.bbijelic.paypal.ipn.entity.NotificationStatus;

import io.dropwizard.hibernate.UnitOfWork;

/**
 * Verification manager
 * 
 * @author Bojan Bijelic
 *
 */
public class VerificationHandler implements Runnable {

	/**
	 * Logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(VerificationHandler.class);

	/**
	 * Notification DAO
	 */
	private NotificationDAO notificationDAO;

	/**
	 * Session factory
	 */
	private SessionFactory sessionFactory;

	/**
	 * Http client.
	 */
	private HttpClient httpClient;

	/**
	 * Verification manager config
	 */
	private VerificationManagerConfiguration config;

	/**
	 * Constructor
	 * 
	 * @param notificationDAO
	 */
	public VerificationHandler(NotificationDAO notificationDAO, SessionFactory sessionFactory, HttpClient httpClient,
			VerificationManagerConfiguration config) {
		this.notificationDAO = notificationDAO;
		this.sessionFactory = sessionFactory;
		this.httpClient = httpClient;
		this.config = config;
	}

	@UnitOfWork
	public void run() {
		logger.info("Performing verification task");

		Session session = sessionFactory.openSession();

		ManagedSessionContext.bind(session);
		Transaction transaction = session.beginTransaction();

		List<Notification> pendingNotifications = notificationDAO.findByStatus(NotificationStatus.PENDING);
		logger.info("Pending notifications: {}", pendingNotifications.size());

		try {

			for (Notification notification : pendingNotifications) {
				logger.info("Handling notification: {}", notification.getTransactionId());
				notification.setStatus(NotificationStatus.VERIFIED);

				HttpPost verificationRequest = new HttpPost(config.getVerificationUrl());

				String paypalCmd = "cmd=_notify-validate&";
				String entityStr = paypalCmd + notification.getRawParameters();

				verificationRequest.setEntity(new StringEntity(entityStr));
				HttpResponse httpResponse = httpClient.execute(verificationRequest);
				
				logger.debug(httpResponse.toString());
				
				String verificationStatus = EntityUtils.toString(httpResponse.getEntity());
				
				logger.info("Notification verification status: [Transaction: {}, Status: {}]",
						notification.getTransactionId(), verificationStatus);

				// Updates notification
				notificationDAO.create(notification);
			}

			transaction.commit();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

			transaction.rollback();

		} finally {
			session.close();
			ManagedSessionContext.unbind(sessionFactory);
		}
	}

}
