package com.kozlovruzudzhenkkovalova.library.serviceTests;

import com.kozlovruzudzhenkkovalova.library.dto.UserDto;
import com.kozlovruzudzhenkkovalova.library.entity.Role;
import com.kozlovruzudzhenkkovalova.library.entity.User;
import com.kozlovruzudzhenkkovalova.library.repositories.UserRepository;
import com.kozlovruzudzhenkkovalova.library.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock
  private UserRepository userRepository;
  @InjectMocks
  private UserService userService;

  @Test
  public void shouldDeleteUser() {
    var userDto = UserDto.builder().username("user1").fullName("user1fullname").password("123").roles(List.of("user"))
        .build();
    var user = User.builder()
        .username("user1")
        .fullName("user1fullname")
        .password("123")
        .roles(Set.of(Role.builder()
            .name("user")
            .build()))
        .build();
    when(userRepository.findByUsername("user1")).thenReturn(Optional.of(user));
    userService.deleteUser(userDto);
    verify(userRepository, times(1)).delete(user);
  }

  @Test
  public void shouldThrowExceptionWhileDeletingUser() {
    var userDto = UserDto.builder().username("user1").fullName("user1fullname").password("123").roles(List.of("user"))
        .build();
    when(userRepository.findByUsername("user1")).thenReturn(Optional.ofNullable(null));
    assertThrows(UsernameNotFoundException.class, () -> userService.deleteUser(userDto));
  }

}