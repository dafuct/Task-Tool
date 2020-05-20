package com.makarenko.tasktool.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class JwtLoginSuccessResponse {

  private boolean success;
  private String token;
}
