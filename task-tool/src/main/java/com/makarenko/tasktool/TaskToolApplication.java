package com.makarenko.tasktool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.makarenko.tasktool.domain")
@SpringBootApplication
public class TaskToolApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskToolApplication.class, args);
  }

}
