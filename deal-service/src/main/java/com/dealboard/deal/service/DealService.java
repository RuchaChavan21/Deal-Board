package com.dealboard.deal.service;

import com.dealboard.deal.dto.ActivityRequest;
import com.dealboard.deal.entity.Deal;
import com.dealboard.deal.repository.DealRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class DealService {

    private final DealRepository repository;
    private final RestTemplate restTemplate;



    public DealService(DealRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
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

        Deal savedDeal = repository.save(newDeal);

        try {
            System.out.println("CALLING ACTIVITY SERVICE FOR DEAL " + savedDeal.getId());

            ActivityRequest activity = new ActivityRequest(
                    orgId,
                    "DEAL",
                    savedDeal.getId(),
                    "DEAL_CREATED",
                    "Deal created",
                    userId
            );

            restTemplate.postForObject(
                    "http://localhost:8087/activities",
                    activity,
                    Void.class
            );
        } catch (Exception ignored) {}

        return savedDeal;
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
