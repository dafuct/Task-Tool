package com.makarenko.tasktool.services;

import com.makarenko.tasktool.domain.NoteTask;
import com.makarenko.tasktool.repositories.BacklogRepository;
import com.makarenko.tasktool.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteTaskService {

  private BacklogRepository backlogRepository;
  private TaskRepository taskRepository;

  public NoteTaskService(BacklogRepository backlogRepository,
      TaskRepository taskRepository) {
    this.backlogRepository = backlogRepository;
    this.taskRepository = taskRepository;
  }

//  public NoteTask addNoteTask() {
//
//  }
}
