package com.makarenko.tasktool.services;

import com.makarenko.tasktool.domain.ProjectTask;

public interface ProjectTaskService {

  ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask);

  Iterable<ProjectTask> findBacklogById(String id);

  ProjectTask findPTByProjectSequence(String backlog_id, String pt_id);

  ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String pt_id);

  void deletePTByProjectSequence(String backlog_id, String pt_id);
}
