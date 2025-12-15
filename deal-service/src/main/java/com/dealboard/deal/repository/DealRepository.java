package com.dealboard.deal.repository;

import com.dealboard.deal.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {

    List<Deal> findByOrganizationId(Long organizationId);
}
