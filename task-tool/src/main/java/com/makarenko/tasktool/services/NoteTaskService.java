package com.makarenko.tasktool.services;

import com.makarenko.tasktool.domain.Backlog;
import com.makarenko.tasktool.domain.NoteTask;
import com.makarenko.tasktool.repositories.BacklogRepository;
import com.makarenko.tasktool.repositories.NoteTaskRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteTaskService {

  private final BacklogRepository backlogRepository;
  private final NoteTaskRepository noteTaskRepository;

  public NoteTaskService(BacklogRepository backlogRepository,
      NoteTaskRepository noteTaskRepository) {
    this.backlogRepository = backlogRepository;
    this.noteTaskRepository = noteTaskRepository;
  }

  public NoteTask addNoteTask(String taskIdentifier, NoteTask noteTask) {
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
  }
}
