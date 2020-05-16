package com.kozlovruzudzhenkkovalova.library.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Data
@Configuration
@ConfigurationProperties("security-config")
public class SecurityUrisProperties {
  private Set<String> unauthorized;
  private Set<String> user;
}
