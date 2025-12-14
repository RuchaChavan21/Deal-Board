package com.dealboard.org.controller;

import com.dealboard.org.entity.Organization;
import com.dealboard.org.entity.OrganizationMember;
import com.dealboard.org.service.OrganizationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orgs")
public class OrganizationController {

    private final OrganizationService service;

    public OrganizationController(OrganizationService service) {
        this.service = service;
    }

    // Create organization
    @PostMapping
    public Organization createOrganization(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestBody Map<String, String> body) {

        return service.createOrganization(
                userId,
                body.get("name"),
                body.get("description")
        );
    }

    // Get organizations of current user
    @GetMapping("/my")
    public List<OrganizationMember> myOrganizations(
            @RequestHeader("X-USER-ID") Long userId) {

        return service.getUserOrganizations(userId);
    }

    // Add member to organization
    @PostMapping("/{orgId}/members")
    public String addMember(
            @PathVariable Long orgId,
            @RequestBody Map<String, String> body) {

        service.addMember(
                orgId,
                Long.valueOf(body.get("userId")),
                body.get("role")
        );

        return "Member added successfully";
    }
}
