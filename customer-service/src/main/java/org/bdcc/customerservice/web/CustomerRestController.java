package org.bdcc.customerservice.web;

import lombok.AllArgsConstructor;
import org.bdcc.customerservice.entities.Customer;
import org.bdcc.customerservice.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerRestController {
    private final CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getCustomerList() {

        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable long id) {
        return customerRepository.findById(id).get();
    }
}
