package com.dealboard.task.repository;

import com.dealboard.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByOrganizationId(Long organizationId);
}
