package com.makarenko.tasktool.services.impl;

import com.makarenko.tasktool.domain.Backlog;
import com.makarenko.tasktool.domain.Project;
import com.makarenko.tasktool.domain.User;
import com.makarenko.tasktool.exceptions.ProjectIdException;
import com.makarenko.tasktool.exceptions.ProjectNotFoundException;
import com.makarenko.tasktool.repositories.BacklogRepository;
import com.makarenko.tasktool.repositories.ProjectRepository;
import com.makarenko.tasktool.repositories.UserRepository;
import com.makarenko.tasktool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

  private final ProjectRepository projectRepository;
  private final BacklogRepository backlogRepository;
  private final UserRepository userRepository;

  @Autowired
  public ProjectServiceImpl(ProjectRepository projectRepository,
      BacklogRepository backlogRepository,
      UserRepository userRepository) {
    this.projectRepository = projectRepository;
    this.backlogRepository = backlogRepository;
    this.userRepository = userRepository;
  }

  public Project saveOrUpdateProject(Project project, String username) {
    if (project.getId() != null) {
      Project existProject = projectRepository
          .findByProjectIdentifier(project.getProjectIdentifier());
      if (existProject != null && (!existProject.getProjectLeader().equals(username))) {
        throw new ProjectNotFoundException("Project not found in your account");
      } else if (existProject == null) {
        throw new ProjectNotFoundException("Project with ID: '" + project.getProjectIdentifier()
            + "' cannot be updated because it doesn`t exist");
      }
    }
    try {
      User user = userRepository.findByUsername(username);
      project.setUser(user);
      project.setProjectLeader(user.getUsername());

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


  public Project findProjectByIdentifier(String projectId, String username) {
    Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    if (project == null) {
      throw new ProjectIdException("Project ID '" + projectId + "' does not exist");
    }

    if (!project.getProjectLeader().equals(username)) {
      throw new ProjectNotFoundException("Project not found in your account");
    }
    return project;
  }

  public Iterable<Project> findAllProjects(String username) {
    return projectRepository.findAllByProjectLeader(username);
  }

  public void deleteProjectByIdentifier(String projectId, String username) {
    projectRepository.delete(findProjectByIdentifier(projectId, username));
  }
}