package com.makarenko.tasktool.exceptions;

public class TaskIdExceptionsResponse {

  private String projectIdentifier;

  public TaskIdExceptionsResponse(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }
}
