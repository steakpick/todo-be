package com.app.todo.repository;

import com.app.todo.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    Task getTaskById(int id);
    List<Task> getAllByCreationDate(LocalDateTime time);
}
