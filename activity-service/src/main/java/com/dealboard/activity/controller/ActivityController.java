package com.dealboard.activity.controller;

import com.dealboard.activity.entity.Activity;
import com.dealboard.activity.service.ActivityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    // INTERNAL API (called by other services)
    @PostMapping
    public Activity logActivity(@RequestBody Activity activity) {
        return service.logActivity(activity);
    }

    // FETCH TIMELINE
    @GetMapping
    public List<Activity> getTimeline(
            @RequestParam Long orgId,
            @RequestParam String entityType,
            @RequestParam Long entityId
    )
    {
        return service.getTimeline(orgId, entityType, entityId);
    }

}
