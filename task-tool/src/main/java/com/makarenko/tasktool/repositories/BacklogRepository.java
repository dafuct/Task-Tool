package com.makarenko.tasktool.repositories;

import com.makarenko.tasktool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

  Backlog findByTaskIdentifier(String identifier);
}
