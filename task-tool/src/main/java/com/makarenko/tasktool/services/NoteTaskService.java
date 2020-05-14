package com.makarenko.tasktool.services;

import com.makarenko.tasktool.domain.Backlog;
import com.makarenko.tasktool.domain.NoteTask;
import com.makarenko.tasktool.domain.Task;
import com.makarenko.tasktool.exceptions.TaskNotFoundException;
import com.makarenko.tasktool.repositories.BacklogRepository;
import com.makarenko.tasktool.repositories.NoteTaskRepository;
import com.makarenko.tasktool.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteTaskService {

  private final BacklogRepository backlogRepository;
  private final NoteTaskRepository noteTaskRepository;
  private final TaskRepository taskRepository;

  public NoteTaskService(BacklogRepository backlogRepository,
      NoteTaskRepository noteTaskRepository,
      TaskRepository taskRepository) {
    this.backlogRepository = backlogRepository;
    this.noteTaskRepository = noteTaskRepository;
    this.taskRepository = taskRepository;
  }

  public NoteTask addNoteTask(String taskIdentifier, NoteTask noteTask) {
    try {
      Backlog backlog = backlogRepository.findByTaskIdentifier(taskIdentifier);

      noteTask.setBacklog(backlog);
      Integer ntSequence = backlog.getNTSequence();
      ntSequence++;
      backlog.setNTSequence(ntSequence);
      noteTask.setTaskSequence(taskIdentifier + "-" + ntSequence);
      noteTask.setTaskIdentifier(taskIdentifier);

      if (noteTask.getStatus() == "" || noteTask.getStatus() == null) {
        noteTask.setStatus("TODO");
      }

      if (noteTask.getPriority() == null) {
        noteTask.setPriority(3);
      }

      return noteTaskRepository.save(noteTask);
    } catch (Exception e) {
      throw new TaskNotFoundException("Task not found");
    }
  }

  public Iterable<NoteTask> findBacklogById(String id) {
    Task task = taskRepository.findByTaskIdentifier(id);

    if (task == null) {
      throw new TaskNotFoundException("Task with ID: '" + id + "' does not exist");
    }

    return noteTaskRepository.findByTaskIdentifierOrderByPriority(id);
  }

  public NoteTask findNTByTaskSequence(String backlog_id, String nt_id) {
    return noteTaskRepository.findByTaskSequence(nt_id);
  }
}
