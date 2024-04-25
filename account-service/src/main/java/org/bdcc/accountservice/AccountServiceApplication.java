package org.bdcc.accountservice;

import org.bdcc.accountservice.clients.CustomerRestClient;
import org.bdcc.accountservice.entities.BankAccount;
import org.bdcc.accountservice.enums.AccountType;
import org.bdcc.accountservice.model.Customer;
import org.bdcc.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository accountRepository,
										CustomerRestClient customerRestClient){
		return args -> {
			customerRestClient.getAllCustomers().forEach(customer -> {
					accountRepository.save(
						BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.balance(Math.random() * 70000)
							.createdAt(LocalDate.now())
							.type(AccountType.CURRENT_ACCOUNT)
							.currency("MAD")
							.customerId(customer.getId())
							.build()
					);

					accountRepository.save(
						BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.balance(Math.random() * 60000)
							.createdAt(LocalDate.now())
							.type(AccountType.SAVING_ACCOUNT)
							.currency("MAD")
							.customerId(customer.getId())
							.build()
					);
				}
			);
		};
	}
}
