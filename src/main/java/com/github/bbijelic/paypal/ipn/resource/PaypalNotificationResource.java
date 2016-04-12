package com.github.bbijelic.paypal.ipn.resource;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.github.bbijelic.paypal.ipn.db.dao.NotificationDAO;
import com.github.bbijelic.paypal.ipn.entity.Notification;
import com.github.bbijelic.paypal.ipn.entity.NotificationStatus;
import com.github.bbijelic.paypal.ipn.logging.LoggingHelper;

import io.dropwizard.hibernate.UnitOfWork;

/**
 * Paypal notification resource. Handles PayPal notifications.
 *
 * @author Bojan Bijelic
 */
@Path("/paypal/ipn")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class PaypalNotificationResource {

	private final static Logger logger = LoggerFactory.getLogger(PaypalNotificationResource.class);

	/**
	 * Notification DAO
	 */
	private NotificationDAO notificationDAO;

	/**
	 * Constructor.
	 * 
	 * @param notificationDAO
	 */
	public PaypalNotificationResource(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}

	@POST
	@UnitOfWork
	@Path("/notification")
	public Response handleNotifiation(MultivaluedMap<String, String> parameters) {

		logger.debug(parameters.toString());

		String transactionId = null;
		if (parameters.containsKey("txn_id")) {
			transactionId = parameters.getFirst("txn_id");
		} else {
			logger.error("Notification does not contain transaction id");
			return Response.status(400).build();
		}

		// Transaction id to the MDC context
		MDC.put(LoggingHelper.MDC_TRANSACTION_ID_KEY, transactionId);
		
		String notificationVersion = null;
		if (parameters.containsKey("notify_version")) {
			notificationVersion = parameters.getFirst("notify_version");
		} else {
			logger.error("Notification does not contain version");
			return Response.status(400).build();
		}

		logger.info("Handling transaction: {}", transactionId);
		logger.info("Notification version: {}", notificationVersion);

		// Notification
		Notification notification = new Notification();
		notification.setRawParameters(parameters.toString());
		notification.setReceivedAt(new Date());
		notification.setStatus(NotificationStatus.PENDING);
		notification.setTransactionId(transactionId);
		notification.setNotificationVersion(notificationVersion);

		// Persist notification in database
		notificationDAO.create(notification);

		return Response.ok().build();

	}

}
