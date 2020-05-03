package com.makarenko.tasktool.services;

import com.makarenko.tasktool.domain.Task;
import com.makarenko.tasktool.exceptions.TaskIdException;
import com.makarenko.tasktool.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository repository;

  public TaskService(TaskRepository repository) {
    this.repository = repository;
  }

  public Task saveOrUpdateTask(Task task) {
    try {
      task.setTaskIdentifier(task.getTaskIdentifier().toUpperCase());
      return repository.save(task);
    } catch (Exception e) {
      throw new TaskIdException(
          "Task Id '" + task.getTaskIdentifier().toUpperCase() + "' already exist");
    }
  }

  public Task findTaskByIdentifier(String TaskId) {
    Task task = repository.findByTaskIdentifier(TaskId.toUpperCase());
    if (task == null) {
      throw new TaskIdException(
          "Task Id '" + TaskId + "' does not exist");
    }
    return task;
  }

  public Iterable<Task> findAllTasks() {
    return repository.findAll();
  }

  public void deleteTaskByIdentifier(String taskId) {
    Task task = repository.findByTaskIdentifier(taskId);
    if (task == null) {
      throw new TaskIdException(
          "Cannot Task with ID '" + taskId + "'. This task does not exist");
    }
    repository.delete(task);
  }
}
