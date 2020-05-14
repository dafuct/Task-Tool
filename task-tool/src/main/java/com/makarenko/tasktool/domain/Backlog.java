package com.makarenko.tasktool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Backlog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer NTSequence = 0;
  private String taskIdentifier;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "task_id", nullable = false)
  @JsonIgnore
  private Task task;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH,
      mappedBy = "backlog", orphanRemoval = true)
  private List<NoteTask> noteTasks = new ArrayList<>();

  public Backlog() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getNTSequence() {
    return NTSequence;
  }

  public void setNTSequence(Integer PTSequence) {
    this.NTSequence = PTSequence;
  }

  public String getTaskIdentifier() {
    return taskIdentifier;
  }

  public void setTaskIdentifier(String taskIdentifier) {
    this.taskIdentifier = taskIdentifier;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public List<NoteTask> getNoteTasks() {
    return noteTasks;
  }

  public void setNoteTasks(List<NoteTask> noteTasks) {
    this.noteTasks = noteTasks;
  }
}
