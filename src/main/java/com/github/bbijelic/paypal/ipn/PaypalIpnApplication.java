package com.github.bbijelic.paypal.ipn;

import com.codahale.metrics.health.HealthCheck;
import com.github.bbijelic.paypal.ipn.config.ServiceConfiguration;
import com.github.bbijelic.paypal.ipn.health.DefaultHealthCheck;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Service main class
 *
 * @author Bojan Bijelic
 */
public class PaypalIpnApplication extends Application<ServiceConfiguration> {

	private final HealthCheck defaultHealthCheck = new DefaultHealthCheck();

	public static void main(String[] args) throws Exception {
		new PaypalIpnApplication().run(args);
	}

	@Override
	public String getName() {
		return "paypal-ipn";
	}

	@Override
	public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
		// nothing to do yet
	}

	@Override
	public void run(ServiceConfiguration configuration, Environment environment) {
		environment.healthChecks().register("default", defaultHealthCheck);
	}

}
