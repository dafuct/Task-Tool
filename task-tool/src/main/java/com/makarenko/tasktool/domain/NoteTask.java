package com.makarenko.tasktool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

@Entity
public class NoteTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(updatable = false, unique = true)
  private String taskSequence;
  @NotBlank(message = "Please include a task summary")
  private String summary;
  private String acceptanceCriteria;
  private String status;
  private Integer priority;
  private Date dueDate;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
  @JsonIgnore
  private Backlog backlog;

  @Column(updatable = false)
  private String taskIdentifier;
  private Date create_At;
  private Date update_At;

  public NoteTask() {
  }

  @PrePersist
  protected void onCreate() {
    this.create_At = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.update_At = new Date();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTaskSequence() {
    return taskSequence;
  }

  public void setTaskSequence(String taskSequence) {
    this.taskSequence = taskSequence;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getAcceptanceCriteria() {
    return acceptanceCriteria;
  }

  public void setAcceptanceCriteria(String acceptanceCriteria) {
    this.acceptanceCriteria = acceptanceCriteria;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public String getTaskIdentifier() {
    return taskIdentifier;
  }

  public void setTaskIdentifier(String taskIdentifier) {
    this.taskIdentifier = taskIdentifier;
  }

  public Date getCreate_At() {
    return create_At;
  }

  public void setCreate_At(Date create_At) {
    this.create_At = create_At;
  }

  public Date getUpdate_At() {
    return update_At;
  }

  public void setUpdate_At(Date update_At) {
    this.update_At = update_At;
  }

  public Backlog getBacklog() {
    return backlog;
  }

  public void setBacklog(Backlog backlog) {
    this.backlog = backlog;
  }

  @Override
  public String toString() {
    return "NoteTask{" +
        "id=" + id +
        ", taskSequence='" + taskSequence + '\'' +
        ", summary='" + summary + '\'' +
        ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
        ", status='" + status + '\'' +
        ", priority=" + priority +
        ", dueDate=" + dueDate +
        ", taskIdentifier='" + taskIdentifier + '\'' +
        ", create_At=" + create_At +
        ", update_At=" + update_At +
        '}';
  }
}
