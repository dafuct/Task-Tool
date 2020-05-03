package com.makarenko.tasktool.web;

import com.makarenko.tasktool.domain.Task;
import com.makarenko.tasktool.services.MapValidationErrorService;
import com.makarenko.tasktool.services.TaskService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {

  private final TaskService service;
  private final MapValidationErrorService validationErrorService;

  public TaskController(
      TaskService service, MapValidationErrorService validationErrorService) {
    this.service = service;
    this.validationErrorService = validationErrorService;
  }

  @PostMapping("")
  public ResponseEntity<?> createNewTask(@Valid @RequestBody Task task,
      BindingResult result) {

    ResponseEntity<?> error = validationErrorService.mapValidationService(result);
    if (error != null) {
      return error;
    }

    return new ResponseEntity<>(service.saveOrUpdateTask(task), HttpStatus.CREATED);
  }

  @GetMapping("/{taskId}")
  public ResponseEntity<?> getProjectById(@PathVariable String taskId) {
    Task taskByIdentifier = service.findTaskByIdentifier(taskId);
    return new ResponseEntity<>(taskByIdentifier, HttpStatus.OK);
  }

  @GetMapping("/all")
  public Iterable<Task> findAllProjects() {
    return service.findAllTasks();
  }

  @DeleteMapping("{taskId}")
  public ResponseEntity<?> deleteProject(@PathVariable String taskId) {
    service.deleteTaskByIdentifier(taskId.toUpperCase());
    return new ResponseEntity<>("Task with ID'" + taskId + "' was deleted", HttpStatus.OK);
  }
}
