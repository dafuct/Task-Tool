package com.makarenko.tasktool.web;

import com.makarenko.tasktool.domain.ProjectTask;
import com.makarenko.tasktool.services.impl.MapValidationErrorService;
import com.makarenko.tasktool.services.impl.ProjectTaskServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

  private final ProjectTaskServiceImpl projectTaskServiceImpl;
  private final MapValidationErrorService mapValidationErrorService;

  @Autowired
  public BacklogController(ProjectTaskServiceImpl projectTaskServiceImpl,
      MapValidationErrorService mapValidationErrorService) {
    this.projectTaskServiceImpl = projectTaskServiceImpl;
    this.mapValidationErrorService = mapValidationErrorService;
  }

  @PostMapping("/{backlog_id}")
  public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask,
      BindingResult result, @PathVariable String backlog_id){
    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
    if (errorMap != null) return errorMap;

    ProjectTask projectTask1 = projectTaskServiceImpl.addProjectTask(backlog_id, projectTask);

    return new ResponseEntity<>(projectTask1, HttpStatus.CREATED);
  }

  @GetMapping("/{backlog_id}")
  public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id){
    return projectTaskServiceImpl.findBacklogById(backlog_id);
  }

  @GetMapping("/{backlog_id}/{pt_id}")
  public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
    ProjectTask projectTask = projectTaskServiceImpl.findPTByProjectSequence(backlog_id, pt_id);
    return new ResponseEntity<>( projectTask, HttpStatus.OK);
  }


  @PatchMapping("/{backlog_id}/{pt_id}")
  public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
      @PathVariable String backlog_id, @PathVariable String pt_id ){

    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
    if (errorMap != null) return errorMap;
    ProjectTask updatedTask = projectTaskServiceImpl
        .updateByProjectSequence(projectTask,backlog_id,pt_id);

    return new ResponseEntity<>(updatedTask,HttpStatus.OK);
  }

  @DeleteMapping("/{backlog_id}/{pt_id}")
  public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
    projectTaskServiceImpl.deletePTByProjectSequence(backlog_id, pt_id);
    return new ResponseEntity<>("Project Task "+pt_id+" was deleted successfully", HttpStatus.OK);
  }
}
