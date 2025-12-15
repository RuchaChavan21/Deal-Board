package com.dealboard.deal.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "deals")
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long organizationId;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private Long ownerUserId;

    @Column(nullable = false)
    private String title;

    private Double value;

    private String stage; // LEAD, CONTACTED, PROPOSAL_SENT, etc.

    private LocalDate expectedCloseDate;

    public Deal() {}

    public Deal(Long organizationId, Long customerId, Long ownerUserId,
                String title, Double value, String stage,
                LocalDate expectedCloseDate) {

        this.organizationId = organizationId;
        this.customerId = customerId;
        this.ownerUserId = ownerUserId;
        this.title = title;
        this.value = value;
        this.stage = stage;
        this.expectedCloseDate = expectedCloseDate;
    }

    public Long getId() { return id; }
    public Long getOrganizationId() { return organizationId; }
    public Long getCustomerId() { return customerId; }
    public Long getOwnerUserId() { return ownerUserId; }
    public String getTitle() { return title; }
    public Double getValue() { return value; }
    public String getStage() { return stage; }
    public LocalDate getExpectedCloseDate() { return expectedCloseDate; }
}
