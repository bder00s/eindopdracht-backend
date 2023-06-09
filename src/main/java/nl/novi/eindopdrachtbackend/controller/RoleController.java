package nl.novi.eindopdrachtbackend.controller;


import nl.novi.eindopdrachtbackend.repository.RoleRepository;
import nl.novi.eindopdrachtbackend.repository.UserRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    RoleRepository roleRepository;
    UserRepository userRepository;
}
