package com.makarenko.tasktool.services.impl;

import com.makarenko.tasktool.domain.User;
import com.makarenko.tasktool.repositories.UserRepository;
import com.makarenko.tasktool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder;

  @Autowired
  public UserServiceImpl(UserRepository userRepository,
      BCryptPasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.encoder = encoder;
  }

  public User saveUser(User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    return userRepository.save(user);
  }
}
