package com.kozlovruzudzhenkkovalova.library.configuration;

import com.kozlovruzudzhenkkovalova.library.configuration.filters.JwtRequestFilter;
import com.kozlovruzudzhenkkovalova.library.entity.Role;
import com.kozlovruzudzhenkkovalova.library.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final RoleRepository roleRepository;
  private final UserDetailsService userDetailsServiceImplementation;
  private final SecurityUrisProperties securityUrisProperties;
  private final JwtRequestFilter jwtRequestFilter;

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
    auth.userDetailsService(userDetailsServiceImplementation);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    roleRepository.findByName("user").orElseGet(() -> {
      Role newUserRole = Role.builder().name("user").build();
      roleRepository.save(newUserRole);
      return newUserRole;
    });
    roleRepository.findByName("admin").orElseGet(() -> {
      Role newAdminRole = Role.builder().name("admin").build();
      roleRepository.save(newAdminRole);
      return newAdminRole;
    });
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers(securityUrisProperties.getUnauthorized().toArray(new String[0])).permitAll()
        .antMatchers(securityUrisProperties.getUser().toArray(new String[0])).hasRole("USER")
        .anyRequest().hasRole("ADMIN")
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
