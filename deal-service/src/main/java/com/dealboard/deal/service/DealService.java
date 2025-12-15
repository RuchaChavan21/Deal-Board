package com.dealboard.deal.service;

import com.dealboard.deal.entity.Deal;
import com.dealboard.deal.repository.DealRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DealService {

    private final DealRepository repository;

    public DealService(DealRepository repository) {
        this.repository = repository;
    }

    public Deal createDeal(Long orgId, Long userId, Deal deal) {

        Deal newDeal = new Deal(
                orgId,
                deal.getCustomerId(),
                userId,
                deal.getTitle(),
                deal.getValue(),
                "LEAD",
                deal.getExpectedCloseDate()
        );

        return repository.save(newDeal);
    }

    public List<Deal> getDeals(Long orgId) {
        return repository.findByOrganizationId(orgId);
    }

    public Deal updateStage(Long dealId, String stage) {

        Deal deal = repository.findById(dealId)
                .orElseThrow(() -> new RuntimeException("Deal not found"));

        deal = new Deal(
                deal.getOrganizationId(),
                deal.getCustomerId(),
                deal.getOwnerUserId(),
                deal.getTitle(),
                deal.getValue(),
                stage,
                deal.getExpectedCloseDate()
        );

        return repository.save(deal);
    }
}
