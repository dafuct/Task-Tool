package com.makarenko.tasktool.web;

import com.makarenko.tasktool.domain.Project;
import com.makarenko.tasktool.services.MapValidationErrorService;
import com.makarenko.tasktool.services.ProjectService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  private final ProjectService service;
  private final MapValidationErrorService validationErrorService;

  public ProjectController(
      ProjectService service, MapValidationErrorService validationErrorService) {
    this.service = service;
    this.validationErrorService = validationErrorService;
  }

  @PostMapping("")
  public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project,
      BindingResult result) {

    ResponseEntity<?> error = validationErrorService.mapValidationService(result);
    if (error != null) {
      return error;
    }

    Project task = service.saveOrUpdateProject(project);
    return new ResponseEntity<>(task, HttpStatus.CREATED);
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
    Project projectByIdentifier = service.findProjectByIdentifier(projectId);
    return new ResponseEntity<>(projectByIdentifier, HttpStatus.OK);
  }

}
