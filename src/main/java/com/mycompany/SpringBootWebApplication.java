package com.mycompany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mycompany.entity.Customer;
import com.mycompany.entity.DeliveryMan;
import com.mycompany.service.CustomerService;
import com.mycompany.service.DeliveryManService;

@SpringBootApplication
public class SpringBootWebApplication
{
	protected static final Logger logger = LoggerFactory.getLogger( SpringBootWebApplication.class );
	
	public static final String SEPERATOR = ",";
	
	@Autowired
	private CustomerService customerService = null;
	@Autowired
	private DeliveryManService deliveryManService = null;

	public static void main(String[] args) {
		SpringApplication.run( SpringBootWebApplication.class, args );
	}

	@Bean
	public InitializingBean initDatabaseData() {
		return new InitializingBean() {

			public void afterPropertiesSet() throws Exception {
				customerService.delete();
				deliveryManService.delete();

				DeliveryMan deliveryA = new DeliveryMan( "employee1", "employee1", "運貨人員A", "25.0598416", "121.4295767" );
				DeliveryMan deliveryB = new DeliveryMan( "employee2", "employee2", "運貨人員B", "25.0598416", "121.4295767" );
				DeliveryMan deliveryC = new DeliveryMan( "employee3", "employee3", "運貨人員C", "25.0598416", "121.4295767" );

				deliveryManService.save( deliveryA );
				deliveryManService.save( deliveryB );
				deliveryManService.save( deliveryC );

				deliveryA = deliveryManService.findDeliveryManByUsernameAndPassword( "employee1", "employee1" );
				deliveryB = deliveryManService.findDeliveryManByUsernameAndPassword( "employee2", "employee2" );
				deliveryC = deliveryManService.findDeliveryManByUsernameAndPassword( "employee3", "employee3" );

				Customer customerA = new Customer( "user1", "user1", "消費者A", "中華民國臺北市中正區建國里重慶南路一段122號", deliveryA );
				Customer customerB = new Customer( "user2", "user2", "消費者B", "台北市信義路五段7號", deliveryB );
				Customer customerC = new Customer( "user3", "user3", "消費者C", "新北市土城區立德路二號", deliveryC );

				customerService.save( customerA );
				customerService.save( customerB );
				customerService.save( customerC );
			}
		};
	}
}
