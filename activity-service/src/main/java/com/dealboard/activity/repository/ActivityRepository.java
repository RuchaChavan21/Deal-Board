package com.dealboard.activity.repository;

import com.dealboard.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByOrganizationIdAndEntityTypeAndEntityIdOrderByCreatedAtDesc(
            Long organizationId,
            String entityType,
            Long entityId
    );
}
