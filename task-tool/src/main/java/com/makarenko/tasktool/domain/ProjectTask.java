package com.makarenko.tasktool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ProjectTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(updatable = false, unique = true)
  private String projectSequence;
  @NotBlank(message = "Пожалуйста введите название задачи")
  private String summary;
  private String acceptanceCriteria;
  private String status;
  private Integer priority;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date dueDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
  @JsonIgnore
  private Backlog backlog;

  @Column(updatable = false)
  private String projectIdentifier;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date create_At;
  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date update_At;

  @PrePersist
  protected void onCreate() {
    this.create_At = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.update_At = new Date();
  }
}