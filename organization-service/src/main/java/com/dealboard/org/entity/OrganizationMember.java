package com.dealboard.org.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "organization_members")
public class OrganizationMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long organizationId;

    @Column(nullable = false)
    private String role; // ADMIN, MANAGER, USER

    public OrganizationMember() {}

    public OrganizationMember(Long userId, Long organizationId, String role) {
        this.userId = userId;
        this.organizationId = organizationId;
        this.role = role;
    }

    public Long getUserId() { return userId; }
    public Long getOrganizationId() { return organizationId; }
    public String getRole() { return role; }
}
