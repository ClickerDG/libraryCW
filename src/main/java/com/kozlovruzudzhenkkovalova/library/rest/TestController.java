package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.entity.Role;
import com.kozlovruzudzhenkkovalova.library.entity.User;
import com.kozlovruzudzhenkkovalova.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@RestController("/")
@RequiredArgsConstructor
@Transactional
public class TestController {
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserRepository userRepository;


  @GetMapping
  public String index(Model model, Principal principal, Authentication authentication) {

    return "You are logged in as " + principal.getName() + ". Roles: "
        + authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(", "));
  }

  @GetMapping("/getUser")
  public User getUser() {
    Set<Role> roles = new HashSet<>();
    roles.add(Role.builder()
            .name("Admin")
            .id(1L).build());
    roles.add(Role.builder()
            .name("User")
            .id(2L).build());

    return User.builder()
        .address("hui")
        .birthDate(new Date())
        .fullName("Kozlov Vitalii Aleksandrovich")
        .username("admin")
        .password("admin")
        .passport("passport")
        .phoneNumber("+380777777777")
        .roles(roles)
        .build();


  }
  @PostMapping("/register")
  public Boolean register(@RequestBody User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    return true;
  }

}
