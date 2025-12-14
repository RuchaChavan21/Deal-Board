package com.dealboard.customer.service;

import com.dealboard.customer.entity.Customer;
import com.dealboard.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }

    public List<Customer> getCustomersByOrg(Long orgId) {
        return repository.findByOrganizationId(orgId);
    }
}
