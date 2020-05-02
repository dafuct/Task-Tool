package com.makarenko.tasktool.exceptions;

public class ProjectIdExceptionsResponse {

  private String projectIdentifier;

  public ProjectIdExceptionsResponse(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }
}
