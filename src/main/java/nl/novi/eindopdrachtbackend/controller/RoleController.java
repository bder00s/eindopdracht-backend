package nl.novi.eindopdrachtbackend.controller;


import nl.novi.eindopdrachtbackend.dto.RoleDto;
import nl.novi.eindopdrachtbackend.model.Role;
import nl.novi.eindopdrachtbackend.repository.RoleRepository;
import nl.novi.eindopdrachtbackend.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RoleController {

  private  RoleRepository roleRepository;


    // INJECTION
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

        @GetMapping("/roles")
        public List<RoleDto> getRoles() {
            List <RoleDto> roleDtos = new ArrayList<>();
            for (Role allRoles : roleRepository.findAll()){
                RoleDto roleDto = new RoleDto();
                roleDto.rolename = allRoles.getRolename();
                roleDtos.add(roleDto);
        }
            return roleDtos;
    }

}
