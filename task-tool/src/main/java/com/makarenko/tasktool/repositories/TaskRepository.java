package com.makarenko.tasktool.repositories;

import com.makarenko.tasktool.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

  Task findByTaskIdentifier(String taskId);

  @Override
  Iterable<Task> findAll();
}
