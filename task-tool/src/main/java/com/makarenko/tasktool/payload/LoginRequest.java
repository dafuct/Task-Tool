package com.makarenko.tasktool.payload;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

  @NotBlank(message = "Имя пользователя не может быть пустым")
  private String username;
  @NotBlank(message = "Пароль не может быть пустым")
  private String password;
}
