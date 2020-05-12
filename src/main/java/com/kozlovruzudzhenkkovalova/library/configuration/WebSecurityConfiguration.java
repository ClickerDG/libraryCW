package com.kozlovruzudzhenkkovalova.library.configuration;

import com.kozlovruzudzhenkkovalova.library.entity.Role;
import com.kozlovruzudzhenkkovalova.library.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final RoleRepository roleRepository;
  private final UserDetailsServiceImplementation userDetailsServiceImplementation;
  private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth.userDetailsService(userDetailsServiceImplementation).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    Role userRole = roleRepository.findByName("user").orElseGet(() -> {
      Role newUserRole = Role.builder().name("user").build();
      roleRepository.save(newUserRole);
      return newUserRole;
    });
    Role adminRole = roleRepository.findByName("admin").orElseGet(() -> {
      Role newAdminRole = Role.builder().name("admin").build();
      roleRepository.save(newAdminRole);
      return newAdminRole;
    });
    http
        .authorizeRequests()
        .antMatchers("/register").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .successHandler(customAuthenticationSuccessHandler)
        .and()
        .csrf().disable();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
