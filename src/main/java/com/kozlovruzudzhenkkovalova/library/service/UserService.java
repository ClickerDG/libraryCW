package com.kozlovruzudzhenkkovalova.library.service;

import com.kozlovruzudzhenkkovalova.library.dto.UserDto;
import com.kozlovruzudzhenkkovalova.library.entity.Role;
import com.kozlovruzudzhenkkovalova.library.entity.User;
import com.kozlovruzudzhenkkovalova.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

  private final RoleService roleService;

  private final UserRepository userRepository;

  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  public void deleteUser(UserDto user) {
    var foundedUser = userRepository.findByUsername(user.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("No such user"));
    userRepository.delete(foundedUser);
  }

  public void updateUser(UserDto user) {
    var foundedUser = userRepository.findByUsername(user.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("No such user"));
    if(user.getPassword() != null) {
      foundedUser.setPassword(user.getPassword());
    }
    if(user.getFullName() != null) {
      foundedUser.setFullName(user.getFullName());
    }
    if(user.getPhoneNumber() != null) {
      foundedUser.setPhoneNumber(user.getPhoneNumber());
    }
    if(user.getRoles() != null && !user.getRoles().isEmpty()) {
      Set<Role> roleList = user.getRoles().stream()
          .map(roleService::findRoleByName)
          .collect(Collectors.toSet());
      foundedUser.setRoles(roleList);
    }
    userRepository.save(foundedUser);
  }

  public Optional<User> searchByName(String name) {
    return userRepository.findByUsername(name);
  }

  public User addUser(UserDto userDto) {

    Set<Role> roleList = userDto.getRoles().stream()
        .map(roleService::findRoleByName)
        .collect(Collectors.toSet());

    var user = User.builder()
        .fullName(userDto.getFullName())
        .username(userDto.getUsername())
        .password(userDto.getPassword())
        .phoneNumber(userDto.getPhoneNumber())
        .roles(roleList)
        .build();
    return addUser(user);
  }

  public User addUser(User user) {
    userRepository.findByUsername(user.getUsername()).ifPresent(thisUser -> {
      throw new IllegalArgumentException(
          "User with such username is already exist. Username: " + thisUser.getUsername());
    });
    userRepository.save(user);
    return user;
  }
}
