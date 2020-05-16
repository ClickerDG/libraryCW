package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.dto.UserDto;
import com.kozlovruzudzhenkkovalova.library.entity.User;
import com.kozlovruzudzhenkkovalova.library.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/userManagement")
@Slf4j
public class UserManagementController {

  private final UserService userService;

  @GetMapping
  public List<User> getAllUsers() {
    return userService.findAllUsers();
  }

  @PostMapping("/update")
  public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
    try {
      userService.updateUser(userDto);
    }
    catch (UsernameNotFoundException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body("No such user");
    }
    return ResponseEntity.ok().body("User was successfully updated");
  }

  @PostMapping("/delete")
  public ResponseEntity<?> deleteUser(@RequestBody UserDto userDto) {
    try {
      userService.deleteUser(userDto);
    }
    catch (UsernameNotFoundException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body("No such user");
    }
    return ResponseEntity.ok().body("User was successfully deleted");
  }

  @PostMapping("/add")
  public ResponseEntity<?> registerNewUser(@RequestBody UserDto userDto) {
    try {
      userService.addUser(userDto);
    }
    catch (IllegalArgumentException e) {
      log.error(e.getMessage());
      return ResponseEntity.badRequest().body("User with such username is already exist");
    }
    return ResponseEntity.ok("User was successfully created");
  }



}
