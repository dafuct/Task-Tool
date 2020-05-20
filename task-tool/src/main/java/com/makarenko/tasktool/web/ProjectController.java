package com.makarenko.tasktool.web;

import com.makarenko.tasktool.domain.Project;
import com.makarenko.tasktool.services.impl.MapValidationErrorService;
import com.makarenko.tasktool.services.ProjectService;
import java.security.Principal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {

  private final ProjectService projectService;
  private final MapValidationErrorService mapValidationErrorService;

  @Autowired
  public ProjectController(ProjectService projectService,
      MapValidationErrorService mapValidationErrorService) {
    this.projectService = projectService;
    this.mapValidationErrorService = mapValidationErrorService;
  }

  @PostMapping("")
  public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project,
      BindingResult result, Principal principal) {

    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
    if (errorMap != null) {
      return errorMap;
    }

    Project newProject = projectService.saveOrUpdateProject(project, principal.getName());
    return new ResponseEntity<>(newProject, HttpStatus.CREATED);
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
    Project project = projectService.findProjectByIdentifier(projectId);
    return new ResponseEntity<>(project, HttpStatus.OK);
  }


  @GetMapping("/all")
  public Iterable<Project> getAllProjects() {
    return projectService.findAllProjects();
  }


  @DeleteMapping("/{projectId}")
  public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
    projectService.deleteProjectByIdentifier(projectId);
    return new ResponseEntity<>("Project with ID: '" + projectId
        + "' was deleted", HttpStatus.OK);
  }
}
