package com.makarenko.tasktool.services;

import com.makarenko.tasktool.domain.Project;

public interface ProjectService {

  Project saveOrUpdateProject(Project project, String username);

  Project findProjectByIdentifier(String projectId,  String username);

  Iterable<Project> findAllProjects(String username);

  void deleteProjectByIdentifier(String projectId, String username);
}
