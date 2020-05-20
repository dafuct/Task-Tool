package com.makarenko.tasktool.services.impl;

import com.makarenko.tasktool.repositories.UserRepository;
import com.makarenko.tasktool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


}
