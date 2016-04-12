package com.github.bbijelic.paypal.ipn;

import com.codahale.metrics.health.HealthCheck;
import com.github.bbijelic.paypal.ipn.bundle.DatabaseBundle;
import com.github.bbijelic.paypal.ipn.config.ServiceConfiguration;
import com.github.bbijelic.paypal.ipn.db.dao.NotificationDAO;
import com.github.bbijelic.paypal.ipn.entity.Notification;
import com.github.bbijelic.paypal.ipn.health.DefaultHealthCheck;
import com.github.bbijelic.paypal.ipn.resource.PaypalNotificationResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Service main class
 *
 * @author Bojan Bijelic
 */
public class PaypalIpnApplication extends Application<ServiceConfiguration> {

	/**
	 * Health check
	 */
	private final HealthCheck defaultHealthCheck = new DefaultHealthCheck();

	/**
	 * Database bundle
	 */
	private final DatabaseBundle databaseBundle = new DatabaseBundle(Notification.class);

	/**
	 * Main method
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new PaypalIpnApplication().run(args);
	}

	/**
	 * Provides application name
	 */
	@Override
	public String getName() {
		return "paypal-ipn";
	}

	@Override
	public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
		// Register database bundle
		bootstrap.addBundle(databaseBundle);
	}

	@Override
	public void run(ServiceConfiguration configuration, Environment environment) {

		// Notification DAO instance
		NotificationDAO notificationDAO = new NotificationDAO(databaseBundle.getSessionFactory());

		// Register IPN message handler resource
		environment.jersey().register(new PaypalNotificationResource(notificationDAO));

		// Register health check
		environment.healthChecks().register("default", defaultHealthCheck);
	}

}
