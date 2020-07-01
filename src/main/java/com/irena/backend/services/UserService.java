package com.irena.backend.services;

import com.irena.backend.dto.UserDto;
import com.irena.backend.entities.User;
import com.irena.backend.repos.UserRepository;
import com.irena.backend.utils.exceptions.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        log.info("getUsers");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserException("User not found"));
    }

    public UserDto registerUser(UserDto userDto) {
        if (userRepository.findByName(userDto.getName()).isEmpty()) {
            return userRepository.save(new User(userDto)).toDto();
        } else {
            throw new UserException("User already exists");
        }
    }

    public UserDto login(UserDto userDto) {
        User user = userRepository.findByName(userDto.getName()).orElseThrow(this::loginException);
        if (user.passwordMatch(userDto.getPassword())) {
            return user.toDto();
        } else {
            throw loginException();
        }

    }

    private UserException loginException() {
        return new UserException("Incorrect login or password");
    }
}
