package com.dealboard.deal.controller;

import com.dealboard.deal.entity.Deal;
import com.dealboard.deal.service.DealService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/deals")
public class DealController {

    private final DealService service;

    public DealController(DealService service) {
        this.service = service;
    }

    // Create deal
    @PostMapping
    public Deal createDeal(
            @RequestHeader("X-ORG-ID") Long orgId,
            @RequestHeader("X-USER-ID") Long userId,
            @RequestHeader("X-ROLE") String role,
            @RequestBody Deal deal) {

        if (!role.equals("ADMIN") && !role.equals("MANAGER")) {
            throw new RuntimeException("Access denied");
        }

        return service.createDeal(orgId, userId, deal);
    }


    // Get all deals for org
    @GetMapping
    public List<Deal> getDeals(
            @RequestHeader("X-ORG-ID") Long orgId) {

        return service.getDeals(orgId);
    }

    // Update deal stage
    @PatchMapping("/{dealId}/stage")
    public Deal updateStage(
            @RequestHeader("X-ROLE") String role,
            @PathVariable Long dealId,
            @RequestBody Map<String, String> body) {

        if (!role.equals("ADMIN") && !role.equals("MANAGER")) {
            throw new RuntimeException("Access denied");
        }

        return service.updateStage(dealId, body.get("stage"));
    }

}
