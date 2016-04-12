package com.github.bbijelic.paypal.ipn.resource;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
	public Response handleNotifiation(@Context HttpServletRequest request, @FormParam("txn_id") String transactionId,
			@FormParam("notify_version") String notificationVersion, @FormParam("txn_type") String transactionType,
			String rawBody) throws IOException {

		// Transaction id to the MDC context
		MDC.put(LoggingHelper.MDC.TRANSACTION_ID_KEY, transactionId);

		logger.info("Transaction type: {}", transactionType);
		logger.info("Transaction ID: {}", transactionId);
		logger.info("Notification version: {}", notificationVersion);

		Notification notification = new Notification();
		notification.setRawParameters(rawBody);
		notification.setReceivedAt(new Date());
		notification.setStatus(NotificationStatus.PENDING);
		notification.setTransactionId(transactionId);
		notification.setNotificationVersion(notificationVersion);
		notification.setTransactionType(transactionType);

		// Persist notification in database
		notificationDAO.create(notification);

		return Response.ok().build();
	}

}
