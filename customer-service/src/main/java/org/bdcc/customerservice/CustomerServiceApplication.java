package org.bdcc.customerservice;

import org.bdcc.customerservice.entities.Customer;
import org.bdcc.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			customerRepository.save(
				Customer.builder()
					.firstName("Ahmed")
					.lastName("DAFYF")
					.email("ahmed@Gmail.com")
					.build()
			);

			customerRepository.save(
				Customer.builder()
					.firstName("Rachid")
					.lastName("OUALIL")
					.email("rachid@Gmail.com")
					.build()
			);
		};
	}
}
