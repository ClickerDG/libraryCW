package com.kozlovruzudzhenkkovalova.library.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticationResponse {

  private final String jwt;

}
