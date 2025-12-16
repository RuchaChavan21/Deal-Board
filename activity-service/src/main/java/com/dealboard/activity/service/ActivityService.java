package com.dealboard.activity.service;

import com.dealboard.activity.entity.Activity;
import com.dealboard.activity.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository repository;

    public ActivityService(ActivityRepository repository) {
        this.repository = repository;
    }

    public Activity logActivity(Activity activity) {
        return repository.save(activity);
    }

    public List<Activity> getTimeline(
            Long orgId,
            String entityType,
            Long entityId
    ) {
        return repository
                .findByOrganizationIdAndEntityTypeAndEntityIdOrderByCreatedAtDesc(
                        orgId, entityType, entityId);
    }
}
