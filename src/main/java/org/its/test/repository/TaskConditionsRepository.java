package org.its.test.repository;

import org.its.test.entity.TaskConditions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskConditionsRepository extends JpaRepository<TaskConditions, Long> {
    List<TaskConditions> findByTaskName(String taskName);
}
