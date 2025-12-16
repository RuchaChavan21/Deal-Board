package com.dealboard.task.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long organizationId;

    private Long dealId;
    private Long customerId;

    @Column(nullable = false)
    private Long assignedToUserId;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDate dueDate;

    private String status; // TODO, IN_PROGRESS, DONE

    public Task() {}

    public Task(Long organizationId, Long dealId, Long customerId,
                Long assignedToUserId, String title, String description,
                LocalDate dueDate, String status) {

        this.organizationId = organizationId;
        this.dealId = dealId;
        this.customerId = customerId;
        this.assignedToUserId = assignedToUserId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public Long getOrganizationId() { return organizationId; }
    public Long getDealId() { return dealId; }
    public Long getCustomerId() { return customerId; }
    public Long getAssignedToUserId() { return assignedToUserId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public String getStatus() { return status; }
}
