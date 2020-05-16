package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
@Transactional
public class TestController {



  @GetMapping("/anyone")
  public String unauthorizedPage() {
    return "Welcome to unauthorized page";
  }

  @GetMapping("/user")
  public String userPage(Authentication authentication) {
    return "Welcome to user page \n Your authorities : "
        + authentication
        .getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
  }

  @GetMapping("/admin")
  public String adminPage() {
    return "Welcome to admin page. You're admin";
  }

}
