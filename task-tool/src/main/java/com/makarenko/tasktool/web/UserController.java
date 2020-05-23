package com.makarenko.tasktool.web;

import static com.makarenko.tasktool.security.SecurityConstants.TOKEN_PREFIX;

import com.makarenko.tasktool.domain.User;
import com.makarenko.tasktool.payload.JwtLoginSuccessResponse;
import com.makarenko.tasktool.payload.LoginRequest;
import com.makarenko.tasktool.security.JwtTokenProvider;
import com.makarenko.tasktool.services.UserService;
import com.makarenko.tasktool.services.impl.MapValidationErrorService;
import com.makarenko.tasktool.validator.UserValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
  private final UserValidator userValidator;
  private final JwtTokenProvider jwtTokenProvider;
  private final AuthenticationManager authenticationManager;

  @Autowired
  public UserController(
      MapValidationErrorService mapValidationErrorService,
      UserService userService, UserValidator userValidator,
      JwtTokenProvider jwtTokenProvider,
      AuthenticationManager authenticationManager) {
    this.mapValidationErrorService = mapValidationErrorService;
    this.userService = userService;
    this.userValidator = userValidator;
    this.jwtTokenProvider = jwtTokenProvider;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
      BindingResult result) {
    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
    if (errorMap != null) {
      return errorMap;
    }
    Authentication authenticate = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authenticate);
    String jwt = TOKEN_PREFIX + jwtTokenProvider. generateToken(authenticate);
    return ResponseEntity.ok(new JwtLoginSuccessResponse(true, jwt));
  }


  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
    userValidator.validate(user, result);

    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
    if (errorMap != null) {
      return errorMap;
    }
    User saveUser = userService.saveUser(user);
    return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
  }
}
