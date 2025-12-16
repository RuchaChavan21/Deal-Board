package com.dealboard.task.controller;

import com.dealboard.task.entity.Task;
import com.dealboard.task.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // Create task
    @PostMapping
    public Task createTask(
            @RequestHeader("X-ORG-ID") Long orgId,
            @RequestHeader("X-USER-ID") Long userId,
            @RequestHeader("X-ROLE") String role,
            @RequestBody Task task) {

        return service.createTask(orgId, userId, role, task);
    }


    // Get tasks for org
    @GetMapping
    public List<Task> getTasks(
            @RequestHeader("X-ORG-ID") Long orgId) {

        return service.getTasks(orgId);
    }

    @PatchMapping("/{taskId}/complete")
    public Task completeTask(
            @PathVariable Long taskId,
            @RequestHeader("X-USER-ID") Long userId,
            @RequestHeader("X-ROLE") String role) {

        return service.completeTask(taskId, userId, role);
    }


}
