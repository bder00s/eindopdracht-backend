package nl.novi.eindopdrachtbackend.service;

import nl.novi.eindopdrachtbackend.dto.UserDto;
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


    public List<UserDto> getUsers() {
        List<UserDto> collection = new ArrayList<>();
        List<User> list = userRepository.findAll();
        for (User user : list) {
            collection.add(fromUserToDto(user));
        }
        return collection;
    }

    public UserDto getUser(String username) {
        UserDto userDto = new UserDto();
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()) {
            userDto = fromUserToDto(user.get());
        } else {
            throw new UsernameNotFoundException(username);
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
        userRepository.deleteById(username);
    }

    public void updateUser(String username, UserDto newUser) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        UserDto userDto = fromUserToDto(user);
        return userDto.getAuthorities();
    }
    // Welke rollen heeft iemand? - voor Admin

    public void addAuthority(String username, String authority) {

        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }
    //Rollen toevoegen - één rol per keer

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
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

