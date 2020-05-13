package com.makarenko.tasktool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "Task name is required")
  private String taskName;
  @NotBlank(message = "Task Identifier is required")
  @Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
  @Column(updatable = false, unique = true)
  private String taskIdentifier;
  @NotBlank(message = "Task description is required")
  private String description;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date start_date;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date end_date;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date created_At;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date updated_At;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "task")
  @JsonIgnore
  private Backlog backlog;

  public Task() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskIdentifier() {
    return taskIdentifier;
  }

  public void setTaskIdentifier(String taskIdentifier) {
    this.taskIdentifier = taskIdentifier;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getStart_date() {
    return start_date;
  }

  public void setStart_date(Date start_date) {
    this.start_date = start_date;
  }

  public Date getEnd_date() {
    return end_date;
  }

  public void setEnd_date(Date end_date) {
    this.end_date = end_date;
  }

  public Date getCreated_At() {
    return created_At;
  }

  public void setCreated_At(Date created_At) {
    this.created_At = created_At;
  }

  public Date getUpdated_At() {
    return updated_At;
  }

  public void setUpdated_At(Date updated_At) {
    this.updated_At = updated_At;
  }

  public Backlog getBacklog() {
    return backlog;
  }

  public void setBacklog(Backlog backlog) {
    this.backlog = backlog;
  }

  @PrePersist
  protected void onCreate() {
    this.created_At = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updated_At = new Date();
  }
}
