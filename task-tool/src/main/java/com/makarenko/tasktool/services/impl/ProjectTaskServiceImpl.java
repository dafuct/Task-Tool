package com.makarenko.tasktool.services.impl;

import com.makarenko.tasktool.domain.Backlog;
import com.makarenko.tasktool.domain.ProjectTask;
import com.makarenko.tasktool.exceptions.ProjectNotFoundException;
import com.makarenko.tasktool.repositories.ProjectTaskRepository;
import com.makarenko.tasktool.services.ProjectService;
import com.makarenko.tasktool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskServiceImpl implements ProjectTaskService {

  private final ProjectTaskRepository projectTaskRepository;
  private final ProjectService projectService;

  @Autowired
  public ProjectTaskServiceImpl(ProjectTaskRepository projectTaskRepository,
      ProjectService projectService) {
    this.projectTaskRepository = projectTaskRepository;
    this.projectService = projectService;
  }

  public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask,
      String username) {
    Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username)
        .getBacklog();
    projectTask.setBacklog(backlog);
    Integer BacklogSequence = backlog.getPTSequence();
    BacklogSequence++;

    backlog.setPTSequence(BacklogSequence);

    projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + BacklogSequence);
    projectTask.setProjectIdentifier(projectIdentifier);

    if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
      projectTask.setStatus("TO_DO");
    }

    if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
      projectTask.setPriority(3);
    }

    return projectTaskRepository.save(projectTask);
  }

  public Iterable<ProjectTask> findBacklogById(String id, String username) {
    projectService.findProjectByIdentifier(id, username);
    return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
  }


  public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id, String username) {
    projectService.findProjectByIdentifier(backlog_id, username);
    ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);

    if (projectTask == null) {
      throw new ProjectNotFoundException("Project Task '" + pt_id + "' not found");
    }

    if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
      throw new ProjectNotFoundException(
          "Project Task '" + pt_id + "' does not exist in project: '" + backlog_id);
    }
    return projectTask;
  }

  public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id,
      String pt_id, String username) {
    ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id, username);
    projectTask = updatedTask;
    return projectTaskRepository.save(projectTask);
  }

  public void deletePTByProjectSequence(String backlog_id, String pt_id, String username) {
    ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id, username);
    projectTaskRepository.delete(projectTask);
  }
}