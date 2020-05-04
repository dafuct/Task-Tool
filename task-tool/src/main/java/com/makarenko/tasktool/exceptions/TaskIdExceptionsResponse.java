package com.makarenko.tasktool.exceptions;

public class TaskIdExceptionsResponse {

  private String taskIdentifier;

  public TaskIdExceptionsResponse(String taskIdentifier) {
    this.taskIdentifier = taskIdentifier;
  }

  public String getTaskIdentifier() {
    return taskIdentifier;
  }

  public void setTaskIdentifier(String taskIdentifier) {
    this.taskIdentifier = taskIdentifier;
  }
}
