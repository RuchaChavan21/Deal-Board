package com.dealboard.deal.dto; // change package in task-service

public class ActivityRequest {

    private Long organizationId;
    private String entityType;
    private Long entityId;
    private String activityType;
    private String message;
    private Long createdBy;

    public ActivityRequest() {}

    public ActivityRequest(Long organizationId, String entityType, Long entityId,
                           String activityType, String message, Long createdBy) {
        this.organizationId = organizationId;
        this.entityType = entityType;
        this.entityId = entityId;
        this.activityType = activityType;
        this.message = message;
        this.createdBy = createdBy;
    }

    public Long getOrganizationId() { return organizationId; }
    public String getEntityType() { return entityType; }
    public Long getEntityId() { return entityId; }
    public String getActivityType() { return activityType; }
    public String getMessage() { return message; }
    public Long getCreatedBy() { return createdBy; }
}
