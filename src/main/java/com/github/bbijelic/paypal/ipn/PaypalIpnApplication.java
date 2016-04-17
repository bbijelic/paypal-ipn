package com.github.bbijelic.paypal.ipn;

import org.apache.http.client.HttpClient;

import com.codahale.metrics.health.HealthCheck;
import com.github.bbijelic.paypal.ipn.bundle.DatabaseBundle;
import com.github.bbijelic.paypal.ipn.config.ServiceConfiguration;
import com.github.bbijelic.paypal.ipn.db.dao.NotificationDAO;
import com.github.bbijelic.paypal.ipn.entity.Notification;
import com.github.bbijelic.paypal.ipn.health.DefaultHealthCheck;
import com.github.bbijelic.paypal.ipn.managed.verify.VerificationHandler;
import com.github.bbijelic.paypal.ipn.managed.verify.VerificationManager;
import com.github.bbijelic.paypal.ipn.resource.PaypalNotificationResource;

import io.dropwizard.Application;
import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
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

		// Enable variable substitution with environment variables
		bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
				bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor()));

	}

	@Override
	public void run(ServiceConfiguration configuration, Environment environment) {

		// Notification DAO instance
		NotificationDAO notificationDAO = new NotificationDAO(databaseBundle.getSessionFactory());

		// Paypal http client
		final HttpClient httpClient = new HttpClientBuilder(environment)
				.using(configuration.getManagerConfiguration().getVerificationManagerConfiguration().getPaypalClient())
				.build(null);

		// Verification handler and manager
		VerificationHandler verificationHandler = new VerificationHandler(notificationDAO,
				databaseBundle.getSessionFactory(), httpClient,
				configuration.getManagerConfiguration().getVerificationManagerConfiguration());
		VerificationManager verificationManager = new VerificationManager(
				configuration.getManagerConfiguration().getVerificationManagerConfiguration(), verificationHandler);

		environment.lifecycle().manage(verificationManager);

		// Register IPN message handler resource
		environment.jersey().register(new PaypalNotificationResource(notificationDAO));

		// Register health check
		environment.healthChecks().register("default", defaultHealthCheck);
	}

}
