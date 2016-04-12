package com.github.bbijelic.paypal.ipn.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

/**
 * Service configuration.
 *
 * @author Bojan Bijelic
 */
public class ServiceConfiguration extends Configuration {

	/**
	 * Database source factory configuration
	 */
	@Valid
	@NotNull
	@JsonProperty("database")
	private DataSourceFactory database = new DataSourceFactory();

	/**
	 * Database source factory configuration getter
	 * 
	 * @return the data source factory
	 */
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

}
