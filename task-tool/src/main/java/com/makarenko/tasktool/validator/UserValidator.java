package com.makarenko.tasktool.validator;

import com.makarenko.tasktool.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

  @Override
  public boolean supports(Class<?> aClass) {
    return User.class.equals(aClass);
  }

  @Override
  public void validate(Object o, Errors errors) {
    User user = (User) o;
    if (user.getPassword().length() < 6) {
      errors.rejectValue("password", "Length", "Пароль должен содержать не менее 6 символов");
    }

    if (!user.getPassword().equals(user.getConfirmPassword())) {
      errors.rejectValue("confirmPassword", "Match", "Пароли должны совпадать");
    }
  }
}
