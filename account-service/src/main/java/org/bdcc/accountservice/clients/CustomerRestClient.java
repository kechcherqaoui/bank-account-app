package org.bdcc.accountservice.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bdcc.accountservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/customers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomers")
    List<Customer> getAllCustomers();

    @GetMapping("/customers/{id}")
    @CircuitBreaker(name = "CustomerService", fallbackMethod = "getDefaultCustomer")
    Customer getCustomer(@PathVariable Long id);

    default Customer getDefaultCustomer(@PathVariable Long id, Exception exception){
        return Customer.builder()
              .id(id)
              .firstName("Not available")
              .lastName("Not available")
              .email("Not available")
              .build();
    }

    default List<Customer> getDefaultCustomers(Exception exception){
        return List.of();
    }
}
