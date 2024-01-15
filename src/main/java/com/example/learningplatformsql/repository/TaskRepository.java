package com.example.learningplatformsql.repository;

import com.example.learningplatformsql.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
