package com.makarenko.tasktool.repositories;

import com.makarenko.tasktool.domain.ProjectTask;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

  List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

  ProjectTask findByProjectSequence(String sequence);
}
