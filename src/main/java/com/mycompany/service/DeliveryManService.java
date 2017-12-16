package com.mycompany.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.entity.DeliveryMan;

@Service
public class DeliveryManService {

	protected static final Logger logger = LoggerFactory.getLogger(DeliveryManService.class);
	private static final String NAMED_DELETE_DELIVERYMAN = "DELETE FROM DELIVERY_MAN";
	private static final String NAMED_QUERY_FIND_DELIVERYMAN_BY_ID = "FROM DeliveryMan o WHERE o.id = :id";
	private static final String NAMED_QUERY_FIND_DELIVERYMAN_BY_USERNAME_AND_PASSWORD = "FROM DeliveryMan o WHERE o.name = :name AND o.password = :password";

	@PersistenceContext
	private EntityManager entityManager = null;

	@Transactional(propagation = Propagation.REQUIRED)
	public int delete() {
		int result = 0;
		
		try {
			result = entityManager.createNativeQuery(NAMED_DELETE_DELIVERYMAN).executeUpdate();
		} catch (Throwable cause) {
			logger.error(cause.getMessage(), cause);
		}
		
		return result;
	}
	
	@Transactional(propagation = Propagation.NEVER)
	public DeliveryMan findDeliveryManByID(String id) {
		DeliveryMan result = null;

		try {
			result = (DeliveryMan) entityManager.createQuery(NAMED_QUERY_FIND_DELIVERYMAN_BY_ID)
					.setParameter("id", id).getSingleResult();
		} catch (Throwable cause) {
		}

		return result;
	}

	@Transactional(propagation = Propagation.NEVER)
	public DeliveryMan findDeliveryManByUsernameAndPassword(String username, String password) {
		DeliveryMan result = null;

		try {
			result = (DeliveryMan) entityManager.createQuery(NAMED_QUERY_FIND_DELIVERYMAN_BY_USERNAME_AND_PASSWORD)
					.setParameter("name", username).setParameter("password", password).getSingleResult();

		} catch (Throwable cause) {
		}

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(DeliveryMan entity) {
		try {
			entityManager.persist(entity);
		} catch (Throwable cause) {

		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void update(DeliveryMan entity) {
		try {
			entityManager.merge(entity);
		} catch (Throwable cause) {

		}
	}
}
