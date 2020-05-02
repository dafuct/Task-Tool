package com.makarenko.tasktool.services;

import com.makarenko.tasktool.domain.Project;
import com.makarenko.tasktool.exceptions.ProjectIdException;
import com.makarenko.tasktool.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

  private final ProjectRepository repository;

  public ProjectService(ProjectRepository repository) {
    this.repository = repository;
  }

  public Project saveOrUpdateProject(Project project) {
    try {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
      return repository.save(project);
    } catch (Exception e) {
      throw new ProjectIdException(
          "Project Id '" + project.getProjectIdentifier().toUpperCase() + "' already exist");
    }
  }

  public Project findProjectByIdentifier(String projectId) {
    Project project = repository.findByProjectIdentifier(projectId.toUpperCase());
    if (project == null) {
      throw new ProjectIdException(
          "Project Id '" + projectId + "' does not exist");
    }
    return project;
  }
}
