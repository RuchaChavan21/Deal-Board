package com.dealboard.org.controller;

import com.dealboard.org.entity.Organization;
import com.dealboard.org.entity.OrganizationMember;
import com.dealboard.org.service.OrganizationService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
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
            @RequestHeader("X-ROLE") String role,
            @RequestBody Map<String, String> body) {

        if (!"ADMIN".equalsIgnoreCase(role)) {
            throw new RuntimeException("Only ADMIN can create organization");
        }

        return service.createOrganization(
                userId,
                body.get("name"),
                body.get("description")
        );
    }


    // Get organizations of current user
    // Get organizations of current user
    // Get organizations of current user
    @GetMapping("/my")
    public List<Map<String, Object>> myOrganizations(@RequestHeader("X-USER-ID") Long userId) {

        List<OrganizationMember> members = service.getUserOrganizations(userId);

        // We will build a new list containing the Name and Description
        return members.stream().map(member -> {
            Map<String, Object> result = new HashMap<>();

            // 1. Put the member info
            result.put("userId", userId);
            result.put("role", member.getRole());
            result.put("organizationId", member.getOrganizationId());

            // 2. THE FIX: Use the Service to look up the Organization details by ID
            Organization org = service.getOrganizationById(member.getOrganizationId());

            if (org != null) {
                // Now we have the name!
                result.put("id", org.getId());
                result.put("name", org.getName());
                result.put("description", org.getDescription());
            } else {
                result.put("name", "Unknown Organization");
            }

            return result;
        }).collect(Collectors.toList());
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

    // Get all organizations (visible to everyone)
    @GetMapping
    public List<Organization> getAllOrganizations() {
        return service.getAllOrganizations();
    }

}
