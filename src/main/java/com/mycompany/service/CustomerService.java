package com.mycompany.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.entity.Customer;

@Service
public class CustomerService
{

	protected static final Logger logger = LoggerFactory.getLogger( CustomerService.class );
	private static final String NAMED_DELETE_CUSTOMER = "DELETE FROM Customer o";
	private static final String NAMED_QUERY_FIND_CUSTOMER_BY_ID = "FROM Customer o WHERE o.id = :id";
	private static final String NAMED_QUERY_FIND_CUSTOMER_BY_USERNAME_AND_PASSWORD = "FROM Customer o WHERE o.name = :name AND o.password = :password";
	private static final String NAMED_QUERY_FIND_CUSTOMER_BY_DELIVERYMAN = "FROM Customer o WHERE o.deliveryMan.id = :deliveryManId";
	private static final String NAMED_QUERY_FIND_TO_BE_SHIPPED_CUSTOMER = "FROM Customer o WHERE o.deliveryMan IS NULL";

	@PersistenceContext
	private EntityManager entityManager = null;

	@Transactional(propagation = Propagation.REQUIRED)
	public int delete() {
		int result = 0;

		try {
			result = entityManager.createNativeQuery( NAMED_DELETE_CUSTOMER ).executeUpdate();
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}

	@Transactional(propagation = Propagation.NEVER)
	public Customer findCustomerById(String id) {
		Customer result = null;

		try {
			result = (Customer) entityManager.createQuery( NAMED_QUERY_FIND_CUSTOMER_BY_ID ).setParameter( "id", id ).getSingleResult();
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}

	@Transactional(propagation = Propagation.NEVER)
	public Customer findCustomerByDeliveryMan(String deliveryManId) {
		Customer result = null;

		try {
			result = (Customer) entityManager.createQuery( NAMED_QUERY_FIND_CUSTOMER_BY_DELIVERYMAN ).setParameter( "deliveryManId", deliveryManId )
			        .getSingleResult();
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}

	@Transactional(propagation = Propagation.NEVER)
	public Customer findCustomerByUsernameAndPassword(String username, String password) {
		Customer result = null;

		try {
			result = (Customer) entityManager.createQuery( NAMED_QUERY_FIND_CUSTOMER_BY_USERNAME_AND_PASSWORD ).setParameter( "name", username )
			        .setParameter( "password", password ).getSingleResult();
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}

	@Transactional(propagation = Propagation.NEVER)
	public List findToBeShippedCustomer() {
		List result = null;

		try {
			result = entityManager.createQuery( NAMED_QUERY_FIND_TO_BE_SHIPPED_CUSTOMER ).getResultList();
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}

	@Transactional(propagation = Propagation.NEVER)
	public boolean isShippedById(String id) {
		boolean result = false;

		try {
			Customer instance = findCustomerById( id );
			
			result = null != instance.getDeliveryMan();
		} catch (Throwable cause) {
			logger.error( cause.getMessage(), cause );
		}

		return result;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void save(Customer entity) {
		try {
			entityManager.persist( entity );
		} catch (Throwable cause) {

		}
	}
}
