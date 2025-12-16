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

    public Task createTask(Long orgId, Long creatorUserId, String role, Task task) {

        // 1️⃣ Authorization
        if (!role.equals("ADMIN") && !role.equals("MANAGER")) {
            throw new RuntimeException("Access denied");
        }

        // 2️⃣ Validation: must be linked
        if (task.getDealId() == null && task.getCustomerId() == null) {
            throw new RuntimeException(
                    "Task must be linked to a deal or a customer");
        }

        // 3️⃣ Validation: assignee must exist (simple check)
        if (task.getAssignedToUserId() == null) {
            throw new RuntimeException("Task must be assigned to a user");
        }

        // 4️⃣ Create new task (controlled fields)
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

    public boolean isTaskAssignedTo(Long taskId, Long userId) {
        Task task = repository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return task.getAssignedToUserId().equals(userId);
    }

    public Task completeTask(Long taskId, Long userId, String role) {

        Task task = repository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        // Prevent re-completion
        if ("DONE".equals(task.getStatus())) {
            throw new RuntimeException("Task already completed");
        }

        // Authorization
        if (role.equals("USER") &&
                !task.getAssignedToUserId().equals(userId)) {
            throw new RuntimeException("Not allowed to complete this task");
        }

        return repository.save(task);
    }


    public List<Task> getTasks(Long orgId) {
        return repository.findByOrganizationId(orgId);
    }
}
