package com.github.bbijelic.paypal.ipn.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.bbijelic.paypal.ipn.entity.Notification;

import io.dropwizard.hibernate.AbstractDAO;

/**
 * Notification DAO implementation.
 *
 * @author Bojan Bijelic
 */
public class NotificationDAO extends AbstractDAO<Notification> {

	/**
	 * Logger.
	 */
	private final static Logger logger = LoggerFactory.getLogger(NotificationDAO.class);

	/**
	 * Constructor
	 * 
	 * @param sessionFactory
	 */
	public NotificationDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
	 * Finds notification by id
	 * 
	 * @param id
	 * @return the notification
	 */
	public Notification findById(Long id) {
		return get(id);
	}

	/**
	 * Persists notification in database.
	 * 
	 * @param notification
	 */
	public void create(Notification notification) {
		persist(notification);
	}

	/**
	 * Lists all notification in database.
	 * 
	 * @return the notification list
	 */
	public List<Notification> findAll() {
		return list(this.currentSession().createCriteria(Notification.class));
	}
}
