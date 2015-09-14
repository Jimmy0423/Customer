package se.jimi.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import se.jimi.customer.service.CustomerService;

@Configuration
@EnableJpaRepositories("se.jimi.customer.repository")
@EnableTransactionManagement
public class ServiceConfig {

	@Bean
	public CustomerService customerService() {
		return new CustomerService();
	}

}
