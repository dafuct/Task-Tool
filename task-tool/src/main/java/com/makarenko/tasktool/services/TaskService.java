package com.makarenko.tasktool.services;

import com.makarenko.tasktool.domain.Backlog;
import com.makarenko.tasktool.domain.Task;
import com.makarenko.tasktool.exceptions.TaskIdException;
import com.makarenko.tasktool.repositories.BacklogRepository;
import com.makarenko.tasktool.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private final BacklogRepository backlogRepository;

  public TaskService(TaskRepository taskRepository,
      BacklogRepository backlogRepository) {
    this.taskRepository = taskRepository;
    this.backlogRepository = backlogRepository;
  }

  public Task saveOrUpdateTask(Task task) {
    String taskIdentifier = task.getTaskIdentifier().toUpperCase();
    try {
      task.setTaskIdentifier(taskIdentifier);

      if (task.getId() == null) {
        Backlog backlog = new Backlog();
        task.setBacklog(backlog);
        backlog.setTask(task);
        backlog.setTaskIdentifier(taskIdentifier);
      }

      if (task.getId() != null) {
        task.setBacklog(
            backlogRepository.findByTaskIdentifier(taskIdentifier));
      }

      return taskRepository.save(task);
    } catch (Exception e) {
      throw new TaskIdException(
          "Task Id '" + taskIdentifier + "' already exist");
    }
  }

  public Task findTaskByIdentifier(String TaskId) {
    Task task = taskRepository.findByTaskIdentifier(TaskId.toUpperCase());
    if (task == null) {
      throw new TaskIdException(
          "Task Id '" + TaskId + "' does not exist");
    }
    return task;
  }

  public Iterable<Task> findAllTasks() {
    return taskRepository.findAll();
  }

  public void deleteTaskByIdentifier(String taskId) {
    Task task = taskRepository.findByTaskIdentifier(taskId);
    if (task == null) {
      throw new TaskIdException(
          "Cannot Task with ID '" + taskId + "'. This task does not exist");
    }
    taskRepository.delete(task);
  }
}
