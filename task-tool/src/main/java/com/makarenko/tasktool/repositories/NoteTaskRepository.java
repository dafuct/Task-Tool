package com.makarenko.tasktool.repositories;

import com.makarenko.tasktool.domain.NoteTask;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteTaskRepository extends CrudRepository<NoteTask, Long> {

  List<NoteTask> findByTaskIdentifierOrderByPriority(String id);

  NoteTask findByTaskSequence(String sequence);
}
