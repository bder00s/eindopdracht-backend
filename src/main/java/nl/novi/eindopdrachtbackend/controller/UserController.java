package nl.novi.eindopdrachtbackend.controller;


import nl.novi.eindopdrachtbackend.dto.UserDto;
import nl.novi.eindopdrachtbackend.model.Role;
import nl.novi.eindopdrachtbackend.model.User;
import nl.novi.eindopdrachtbackend.repository.RoleRepository;
import nl.novi.eindopdrachtbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    public String createUser(@RequestBody UserDto userDto){
        User newUser = new User();
        newUser.setEmail(userDto.email);
        newUser.setUsername(userDto.username);
        newUser.setPassword(passwordEncoder.encode(userDto.password));

        List<Role> userRoles = new ArrayList<>();
        for (String rolename : userDto.roles) {
            Optional<Role> or = roleRepository.findById("ROLE_" + rolename);

            userRoles.add(or.get());
        }
        newUser.setRoles(userRoles);
        userRepository.save(newUser);

        return "new user " + userDto.username + " is saved.";
    }
}
