package com.dealboard.task.service;

import com.dealboard.task.entity.Task;
import com.dealboard.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task createTask(Long orgId, Task task) {

        if (task.getDealId() == null && task.getCustomerId() == null) {
            throw new RuntimeException(
                    "Task must be linked to deal or customer");
        }

        Task newTask = new Task(
                orgId,
                task.getDealId(),
                task.getCustomerId(),
                task.getAssignedToUserId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                "TODO"
        );

        return repository.save(newTask);
    }

    public List<Task> getTasks(Long orgId) {
        return repository.findByOrganizationId(orgId);
    }
}
