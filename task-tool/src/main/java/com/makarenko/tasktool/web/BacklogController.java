package com.makarenko.tasktool.web;

import com.makarenko.tasktool.domain.NoteTask;
import com.makarenko.tasktool.services.MapValidationErrorService;
import com.makarenko.tasktool.services.NoteTaskService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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

  private final NoteTaskService noteTaskService;
  private final MapValidationErrorService mapValidationErrorService;

  public BacklogController(NoteTaskService noteTaskService,
      MapValidationErrorService mapValidationErrorService) {
    this.noteTaskService = noteTaskService;
    this.mapValidationErrorService = mapValidationErrorService;
  }

  @PostMapping("/{backlog_id}")
  public ResponseEntity<?> addNTBacklog(@Valid @RequestBody NoteTask noteTask,
      BindingResult result, @PathVariable String backlog_id) {

    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
    if (errorMap != null) {
      return errorMap;
    }

    return new ResponseEntity<>(noteTaskService.addNoteTask(backlog_id, noteTask),
        HttpStatus.CREATED);
  }

  @GetMapping("/{backlog_id}")
  public Iterable<NoteTask> getTaskBacklog(@PathVariable String backlog_id) {
    return noteTaskService.findBacklogById(backlog_id);
  }

  @GetMapping("/{backlog_id}/{nt_id}")
  public ResponseEntity<?> getNoteTask(@PathVariable String backlog_id,
      @PathVariable String nt_id) {
    NoteTask noteTask = noteTaskService.findNTByTaskSequence(backlog_id, nt_id);
    return new ResponseEntity<>(noteTask, HttpStatus.OK);
  }

  @PatchMapping("/{backlog_id}/{nt_id}")
  public ResponseEntity<?> updateNoteTask(@Valid @RequestBody NoteTask noteTask,
      BindingResult result, @PathVariable String backlog_id, @PathVariable String nt_id) {

    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
    if (errorMap != null) {
      return errorMap;
    }

    NoteTask note = noteTaskService.updateByTaskSequence(noteTask, backlog_id, nt_id);
    return new ResponseEntity<>(note, HttpStatus.OK);
  }
}
