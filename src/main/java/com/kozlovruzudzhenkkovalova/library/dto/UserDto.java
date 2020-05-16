package com.kozlovruzudzhenkkovalova.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private String fullName;

  private String username;

  private String password;

  private String phoneNumber;

  private List<String> roles;

}
