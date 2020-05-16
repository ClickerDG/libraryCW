package com.kozlovruzudzhenkkovalova.library.rest;

import com.kozlovruzudzhenkkovalova.library.entity.Role;
import com.kozlovruzudzhenkkovalova.library.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

  private final RoleRepository repository;

  @GetMapping
  public List<Role> getAllRoles() {
    return repository.findAll();
  }

}
