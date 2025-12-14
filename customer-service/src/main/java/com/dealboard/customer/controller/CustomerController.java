package com.dealboard.customer.controller;

import com.dealboard.customer.entity.Customer;
import com.dealboard.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    // Create customer
    @PostMapping
    public Customer createCustomer(
            @RequestHeader("X-ORG-ID") Long orgId,
            @RequestHeader("X-USER-ID") Long userId,
            @RequestBody Customer customer) {

        customer = new Customer(
                orgId,
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                userId,
                "LEAD"
        );

        return service.createCustomer(customer);
    }

    // Get customers of organization
    @GetMapping
    public List<Customer> getCustomers(
            @RequestHeader("X-ORG-ID") Long orgId) {

        return service.getCustomersByOrg(orgId);
    }
}
