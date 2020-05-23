package com.makarenko.tasktool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Email(message = "Имя пользователя должно быть электронной почтой")
  @NotBlank(message = "Имя пользователя обязательно для заполнения")
  @Column(unique = true)
  private String username;

  @NotBlank(message = "Пожалуйста введите свое имя")
  private String fullName;

  @NotBlank(message = "Поле пароля обязательно для заполнения")
  private String password;

  @Transient
  private String confirmPassword;
  private Date create_At;
  private Date update_At;

  @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER,
      mappedBy = "user", orphanRemoval = true)
  private List<Project> projects = new ArrayList<>();

  @PrePersist
  protected void onCreate() {
    this.create_At = new Date();
  }

  @PreUpdate
  protected void onUpdate() {
    this.update_At = new Date();
  }

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return true;
  }
}
