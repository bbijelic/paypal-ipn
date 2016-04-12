package com.github.bbijelic.paypal.ipn.bundle;

import com.github.bbijelic.paypal.ipn.config.ServiceConfiguration;

import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

public class DatabaseBundle extends HibernateBundle<ServiceConfiguration> {

	/**
	 * Constructor
	 * 
	 * @param entity
	 * @param entities
	 */
	protected DatabaseBundle(Class<?> entity, Class<?>[] entities) {
		super(entity, entities);
	}

	/**
	 * Constructor.
	 * @param entity
	 */
	public DatabaseBundle(Class<?> entity) {
		super(entity);
	}

	/**
	 * Data source factory getter getter.
	 * 
	 * @return the pooled data source factory
	 */
	public PooledDataSourceFactory getDataSourceFactory(ServiceConfiguration configuration) {
		return configuration.getDataSourceFactory();
	}

}
