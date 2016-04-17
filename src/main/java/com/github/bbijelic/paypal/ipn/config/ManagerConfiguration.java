package com.github.bbijelic.paypal.ipn.config;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Manager configuration
 * 
 * @author Bojan Bijelic
 *
 */
public class ManagerConfiguration {

	/**
	 * Verification manager configuration.
	 */
	@Valid
	@JsonProperty("verification")
	private VerificationManagerConfiguration verificationManagerConfiguration;

	@Override
	public String toString() {
		return "ManagerConfiguration [verificationManagerConfiguration=" + verificationManagerConfiguration + "]";
	}

	public VerificationManagerConfiguration getVerificationManagerConfiguration() {
		return verificationManagerConfiguration;
	}

	public void setVerificationManagerConfiguration(VerificationManagerConfiguration verificationManagerConfiguration) {
		this.verificationManagerConfiguration = verificationManagerConfiguration;
	}

}
