package com.dealboard.customer.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long organizationId;

    @Column(nullable = false)
    private String name;

    private String email;
    private String phone;

    private Long ownerUserId; // salesperson

    private String status; // LEAD, ACTIVE, INACTIVE

    public Customer() {}

    public Customer(Long organizationId, String name, String email,
                    String phone, Long ownerUserId, String status) {
        this.organizationId = organizationId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.ownerUserId = ownerUserId;
        this.status = status;
    }

    public Long getId() { return id; }
    public Long getOrganizationId() { return organizationId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public Long getOwnerUserId() { return ownerUserId; }
    public String getStatus() { return status; }
}
