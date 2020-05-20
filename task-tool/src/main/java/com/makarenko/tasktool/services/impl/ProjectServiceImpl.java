package com.makarenko.tasktool.services.impl;

import com.makarenko.tasktool.domain.Backlog;
import com.makarenko.tasktool.domain.Project;
import com.makarenko.tasktool.exceptions.ProjectIdException;
import com.makarenko.tasktool.repositories.BacklogRepository;
import com.makarenko.tasktool.repositories.ProjectRepository;
import com.makarenko.tasktool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final BacklogRepository backlogRepository;

  @Autowired
  public ProjectServiceImpl(ProjectRepository projectRepository,
      BacklogRepository backlogRepository) {
    this.projectRepository = projectRepository;
    this.backlogRepository = backlogRepository;
  }

  public Project saveOrUpdateProject(Project project) {
    try {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

      if (project.getId() == null) {
        Backlog backlog = new Backlog();
        project.setBacklog(backlog);
        backlog.setProject(project);
        backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
      }

      if (project.getId() != null) {
        project.setBacklog(
            backlogRepository
                .findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
      }

      return projectRepository.save(project);
    } catch (Exception e) {
      throw new ProjectIdException(
          "Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
    }
  }


  public Project findProjectByIdentifier(String projectId) {
    Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    if (project == null) {
      throw new ProjectIdException("Project ID '" + projectId + "' does not exist");
    }
    return project;
  }

  public Iterable<Project> findAllProjects() {
    return projectRepository.findAll();
  }


  public void deleteProjectByIdentifier(String projectid) {
    Project project = projectRepository.findByProjectIdentifier(projectid.toUpperCase());

    if (project == null) {
      throw new ProjectIdException(
          "Cannot Project with ID '" + projectid + "'. This project does not exist");
    }
    projectRepository.delete(project);
  }
}