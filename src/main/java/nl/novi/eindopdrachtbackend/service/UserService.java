package nl.novi.eindopdrachtbackend.service;

import nl.novi.eindopdrachtbackend.dto.UserDto;
import nl.novi.eindopdrachtbackend.dto.UserOutputDto;
import nl.novi.eindopdrachtbackend.exception.BookNotFoundException;
import nl.novi.eindopdrachtbackend.model.Authority;
import nl.novi.eindopdrachtbackend.model.User;
import nl.novi.eindopdrachtbackend.repository.UserRepository;
import nl.novi.eindopdrachtbackend.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {


    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;


    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserOutputDto> getUsers() {
        List<UserOutputDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(simpleUserOutput(user));
        }
        return collection;
    }

    String errormessage = "User not found!";

    public UserDto getUser(String username) {
        UserDto userDto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            userDto = fromUserToDto(user.get());
        } else {
            throw new UsernameNotFoundException(errormessage);
        }
        return userDto;
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createUser(UserDto userDto) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        userDto.setApikey(randomString);
        User newUser = userRepository.save(fromDtoToUser(userDto));

        return newUser.getUsername();
    }

    public void deleteUser(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(username);

        } else {
            throw new UsernameNotFoundException(errormessage);
        }
    }


    /// HOE OUTDATED USER VERVANGEN VOOR UPDATED USER, IPV TWEE LOSSE USERS?
    public void updateUser(String username, UserDto updatedUser) {
        User user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(errormessage));

        user.setEnabled(updatedUser.enabled);
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        user.setEmail(updatedUser.email);
        user.setFullname(updatedUser.fullname);
        user.setAddress(updatedUser.address);
        user.setApikey(updatedUser.apikey);
        userRepository.save(user);


    }


    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(errormessage);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUserToDto(user);
        return userDto.getAuthorities();
    }


    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(errormessage);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }


    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(errormessage);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    public static UserOutputDto simpleUserOutput(User user) {
        UserOutputDto userOutputDto = new UserOutputDto();

        userOutputDto.username = user.getUsername();
        userOutputDto.email = user.getEmail();
        userOutputDto.fullname = user.getFullname();

        return userOutputDto;
    }

    public static UserDto fromUserToDto(User user) {

        UserDto userDto = new UserDto();

        userDto.username = user.getUsername();
        userDto.password = user.getPassword();
        userDto.enabled = user.isEnabled();
        userDto.apikey = user.getApikey();
        userDto.email = user.getEmail();
        userDto.fullname = user.getFullname();
        userDto.address = user.getAddress();
        userDto.authorities = user.getAuthorities();

        return userDto;
    }

    public User fromDtoToUser(UserDto userDto) {

        User user = new User();

        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEnabled(userDto.getEnabled());
        user.setApikey(userDto.getApikey());
        user.setEmail(userDto.getEmail());
        user.setFullname(userDto.getFullname());
        user.setAddress(userDto.getAddress());


        return user;
    }

}

