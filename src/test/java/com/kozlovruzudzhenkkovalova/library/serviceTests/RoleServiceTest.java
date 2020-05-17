package com.kozlovruzudzhenkkovalova.library.serviceTests;

import com.kozlovruzudzhenkkovalova.library.entity.Role;
import com.kozlovruzudzhenkkovalova.library.repositories.RoleRepository;
import com.kozlovruzudzhenkkovalova.library.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

  @Mock
  private RoleRepository roleRepository;
  @InjectMocks
  private RoleService roleService;

  @Test
  public void shouldCallFindByName1Time() {
    when(roleRepository.findByName("admin")).thenReturn(Optional.of(Role.builder().name("admin").build()));
    roleService.findRoleByName("admin");
    verify(roleRepository, times(1)).findByName("admin");
  }

}