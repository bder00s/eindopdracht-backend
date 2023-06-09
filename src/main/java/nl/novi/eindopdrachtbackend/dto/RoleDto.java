package nl.novi.eindopdrachtbackend.dto;

import jakarta.validation.constraints.NotBlank;

public class RoleDto {

    @NotBlank
    public String rolename;
    public String[] users;

    public RoleDto(String rolename) {
        this.rolename = rolename;
    }
}
