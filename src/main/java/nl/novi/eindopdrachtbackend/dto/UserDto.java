package nl.novi.eindopdrachtbackend.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {

  public String username;
  public String password;
  @NotBlank
  public String email;
  public String[] roles;

    public UserDto(String username, String password, String email, String[] roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }
}
