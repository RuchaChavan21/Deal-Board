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
            @RequestBody Task task) {

        return service.createTask(orgId, task);
    }

    // Get tasks for org
    @GetMapping
    public List<Task> getTasks(
            @RequestHeader("X-ORG-ID") Long orgId) {

        return service.getTasks(orgId);
    }
}
