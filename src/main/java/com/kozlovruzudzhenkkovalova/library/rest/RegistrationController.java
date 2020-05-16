package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.dto.UserDto;
import com.kozlovruzudzhenkkovalova.library.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegistrationController {

  private final UserService userService;


  @PostMapping("/register")
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
