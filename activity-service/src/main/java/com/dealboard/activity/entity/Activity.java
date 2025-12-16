package com.dealboard.activity.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long organizationId;

    private String entityType; // DEAL, TASK, CUSTOMER
    private Long entityId;

    private String activityType; // DEAL_CREATED, STAGE_CHANGED, etc.
    private String message;

    private Long createdBy;
    private LocalDateTime createdAt;

    public Activity() {}

    public Activity(Long organizationId, String entityType, Long entityId,
                    String activityType, String message, Long createdBy) {
        this.organizationId = organizationId;
        this.entityType = entityType;
        this.entityId = entityId;
        this.activityType = activityType;
        this.message = message;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Long getOrganizationId() { return organizationId; }
    public String getEntityType() { return entityType; }
    public Long getEntityId() { return entityId; }
    public String getActivityType() { return activityType; }
    public String getMessage() { return message; }
    public Long getCreatedBy() { return createdBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
