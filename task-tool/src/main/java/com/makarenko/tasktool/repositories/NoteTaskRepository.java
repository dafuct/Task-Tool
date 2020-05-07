package com.makarenko.tasktool.repositories;

import com.makarenko.tasktool.domain.NoteTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteTaskRepository extends CrudRepository<NoteTask, Long> {

}
