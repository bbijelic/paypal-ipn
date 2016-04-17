package com.github.bbijelic.paypal.ipn.config;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.client.HttpClientConfiguration;

/**
 * Verificiation Manager configuration
 * 
 * @author Bojan Bijelic
 *
 */
public class VerificationManagerConfiguration {

	@Min(1)
	@Max(86400)
	@NotNull
	@JsonProperty("interval")
	private int interval;

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	@Min(0)
	@Max(86400)
	@JsonProperty("initialDelay")
	private int initialDelay = 0;

	public int getInitialDelay() {
		return initialDelay;
	}

	public void setInitialDelay(int initialDelay) {
		this.initialDelay = initialDelay;
	}

	@NotNull
	@JsonProperty("verificationUrl")
	private String verificationUrl;

	public String getVerificationUrl() {
		return verificationUrl;
	}

	public void setVerificationUrl(String verificationUrl) {
		this.verificationUrl = verificationUrl;
	}
	
	@Valid
    @NotNull
    @JsonProperty("paypalClient")
    private HttpClientConfiguration paypalClient = new HttpClientConfiguration();

    public HttpClientConfiguration getPaypalClient() {
        return paypalClient;
    }

	@Override
	public String toString() {
		return "VerificationManagerConfiguration [interval=" + interval + ", initialDelay=" + initialDelay
				+ ", verificationUrl=" + verificationUrl + ", paypalClient=" + paypalClient + "]";
	}

}
