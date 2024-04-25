package org.bdcc.accountservice.web;


import lombok.AllArgsConstructor;
import org.bdcc.accountservice.clients.CustomerRestClient;
import org.bdcc.accountservice.entities.BankAccount;
import org.bdcc.accountservice.model.Customer;
import org.bdcc.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountRestController {
    private final BankAccountRepository bankAccountRepository;
    private final CustomerRestClient customerRestClient;

    @GetMapping("/accounts")
    public List<BankAccount> getCustomerList() {
        return bankAccountRepository.findAll();
    }

    @GetMapping("/accounts/{id}")
    public BankAccount getCustomer(@PathVariable String id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomer(bankAccount.getCustomerId());

        bankAccount.setCustomer(customer);
        return bankAccount;
    }


}
