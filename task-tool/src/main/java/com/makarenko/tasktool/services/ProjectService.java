package com.makarenko.tasktool.services;

import com.makarenko.tasktool.domain.Project;

public interface ProjectService {

  Project saveOrUpdateProject(Project project);

  Project findProjectByIdentifier(String projectId);

  Iterable<Project> findAllProjects();

  void deleteProjectByIdentifier(String projectId);
}
