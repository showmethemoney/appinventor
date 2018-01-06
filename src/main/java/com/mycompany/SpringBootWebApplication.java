package com.mycompany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mycompany.entity.Customer;
import com.mycompany.entity.DeliveryMan;
import com.mycompany.service.CustomerService;
import com.mycompany.service.DeliveryManService;

@SpringBootApplication
@EnableAsync
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

	@Bean("automaticUpdateMapLocationExecutor")
	public TaskExecutor automaticUpdateMapLocationExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize( 4 );
		executor.setMaxPoolSize( 4 );
		executor.setThreadNamePrefix( "map" );
		executor.initialize();

		return executor;
	}

	@Bean
	public InitializingBean initDatabaseData() {
		return new InitializingBean() {

			public void afterPropertiesSet() throws Exception {
				customerService.delete();
				deliveryManService.delete();

				DeliveryMan deliveryA = new DeliveryMan( "employee1", "employee1", "運貨人員A", "25.03372310", "121.43363040" );
				DeliveryMan deliveryB = new DeliveryMan( "employee2", "employee2", "運貨人員B", "25.03372310", "121.43363040" );
				DeliveryMan deliveryC = new DeliveryMan( "employee3", "employee3", "運貨人員C", "25.03372310", "121.43363040" );

				deliveryManService.save( deliveryA );
				deliveryManService.save( deliveryB );
				deliveryManService.save( deliveryC );

//				deliveryA = deliveryManService.findDeliveryManByUsernameAndPassword( "employee1", "employee1" );
//				deliveryB = deliveryManService.findDeliveryManByUsernameAndPassword( "employee2", "employee2" );
//				deliveryC = deliveryManService.findDeliveryManByUsernameAndPassword( "employee3", "employee3" );

				Customer customerA = new Customer( "user1", "user1", "消費者A", "屏東縣車城鄉後灣村後灣路2號", null );
				Customer customerB = new Customer( "user2", "user2", "消費者B", "南投縣水里鄉55344中山路一段515號", null );
				Customer customerC = new Customer( "user3", "user3", "消費者C", "花蓮縣秀林鄉富世村富世291號", null );
				
				customerService.save( customerA );
				customerService.save( customerB );
				customerService.save( customerC );
			}
		};
	}
}
