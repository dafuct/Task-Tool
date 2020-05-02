package com.makarenko.tasktool.web;

import com.makarenko.tasktool.domain.Project;
import com.makarenko.tasktool.services.ProjectService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  @Autowired
  private ProjectService service;

  @PostMapping("")
  public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project,
      BindingResult result) {
    if (result.hasErrors()) {
      return new ResponseEntity<>("Invalid Project Object", HttpStatus.BAD_REQUEST);
    }
    Project task = service.saveOrUpdateProject(project);
    return new ResponseEntity<>(task, HttpStatus.CREATED);
  }

}
