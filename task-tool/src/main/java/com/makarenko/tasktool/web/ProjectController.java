package com.makarenko.tasktool.web;

import com.makarenko.tasktool.domain.Project;
import com.makarenko.tasktool.services.ProjectService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
      Map<String, String> errorMap = new HashMap<>();

      for (FieldError fieldError : result.getFieldErrors()) {
        errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
      }
      return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
    Project task = service.saveOrUpdateProject(project);
    return new ResponseEntity<>(task, HttpStatus.CREATED);
  }

}
