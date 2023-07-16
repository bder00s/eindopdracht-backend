package nl.novi.eindopdrachtbackend.controller;

import nl.novi.eindopdrachtbackend.dto.UserDto;
import nl.novi.eindopdrachtbackend.exception.BadRequestException;
import nl.novi.eindopdrachtbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<UserDto>> getUsers() {

        List<UserDto> userDtos = userService.getUsers();

        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable("username") String username) {

        UserDto optionalUser = userService.getUser(username);


        return ResponseEntity.ok().body(optionalUser);

    }

    @PostMapping(value = "/newuser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
        ;

        String newUsername = userService.createUser(dto);
        userService.addAuthority(newUsername, "ROLE_MEMBER");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{username}")
    public String updateUser(@PathVariable("username") String username, @RequestBody UserDto dto) {

        userService.updateUser(username, dto);

//        return ResponseEntity.noContent().build();
        return "User: " + username + " succesfully updated to: \n" + dto.username + "\n" + dto.email + "\n" + dto.fullname;
    }

    @DeleteMapping(value = "/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return "User " + username + " succesfully deleted!";
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PostMapping(value = "/{username}/authorities")
    public String addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userService.addAuthority(username, authorityName);
            return "New authority added to user: " + authorityName;
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public String deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userService.removeAuthority(username, authority);
        return "Authority: " + authority + "\nFrom user: " + username + "\nsuccesfully deleted!";
    }

}

