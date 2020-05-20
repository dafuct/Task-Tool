package com.makarenko.tasktool.web;

import com.makarenko.tasktool.domain.User;
import com.makarenko.tasktool.services.UserService;
import com.makarenko.tasktool.services.impl.MapValidationErrorService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final MapValidationErrorService mapValidationErrorService;
  private final UserService userService;

  @Autowired
  public UserController(
      MapValidationErrorService mapValidationErrorService,
      UserService userService) {
    this.mapValidationErrorService = mapValidationErrorService;
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
    if(errorMap != null) {
      return errorMap;
    }
    User saveUser = userService.saveUser(user);
    return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
  }
}
