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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Название цели должно быть заполнено")
  private String projectName;

  @NotBlank(message ="Поле идентификатора цели должно быть заполнено")
  @Size(min=4, max=5, message = "Пожалуйста используйте от 4 - 5 символов")
  @Column(updatable = false, unique = true)
  private String projectIdentifier;

  @NotBlank(message = "Описание цели должно быть заполнено")
  private String description;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date start_date;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date end_date;

  @JsonFormat(pattern = "yyyy-mm-dd")
  @Column(updatable = false)
  private Date created_At;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private Date updated_At;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
  @JsonIgnore
  private Backlog backlog;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  private User user;

  private String projectLeader;

  @PrePersist
  protected void onCreate(){
    this.created_At = new Date();
  }

  @PreUpdate
  protected void onUpdate(){
    this.updated_At = new Date();
  }
}
