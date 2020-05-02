package com.makarenko.tasktool.services;

import com.makarenko.tasktool.domain.Project;
import com.makarenko.tasktool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

  @Autowired
  private ProjectRepository repository;

  public Project saveOrUpdateProject(Project project) {
    return repository.save(project);
  }
}
