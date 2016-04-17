package com.github.bbijelic.paypal.ipn.managed.verify;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.paypal.ipn.config.VerificationManagerConfiguration;

import io.dropwizard.lifecycle.Managed;

/**
 * Takes PENDING notifications from database and returns unmodified notification
 * back to Paypal as proof-of-possesion.
 * 
 * @author Bojan Bijelic
 *
 */
public class VerificationManager implements Managed {

	/**
	 * Logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(VerificationManager.class);

	/**
	 * Scheduled executor pool
	 */
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	/**
	 * Verification manager configuration
	 */
	private VerificationManagerConfiguration configuration;

	/**
	 * Verification handler
	 */
	private VerificationHandler handler;

	/**
	 * Constructor
	 * 
	 * @param configuration
	 */
	public VerificationManager(VerificationManagerConfiguration configuration, VerificationHandler handler) {
		this.configuration = configuration;
		this.handler = handler;
	}

	/**
	 * Starts verification manager
	 */
	public void start() throws Exception {
		logger.info("Starting verification manager: {}", configuration.toString());
		scheduler.scheduleAtFixedRate(handler, configuration.getInitialDelay(), configuration.getInterval(),
				TimeUnit.SECONDS);

	}

	/**
	 * Stops verification manager
	 */
	public void stop() throws Exception {
		logger.info("Stopping verification manager");
		scheduler.shutdown();
	}

}
