//package com.kozlovruzudzhenkkovalova.library.configuration;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@RequiredArgsConstructor
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//  private final UserDetailsServiceImplementation userDetailsServiceImplementation;
//  private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userDetailsServiceImplementation).passwordEncoder(bCryptPasswordEncoder);
//  }
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
////    http.authorizeRequests()
////        .anyRequest().hasAnyRole("USER", "ADMIN")
////        .and()
////        .formLogin()
////        .and()
////        .logout().permitAll().logoutSuccessUrl("/login")
////        .and()
////        .csrf().disable();
//    http.authorizeRequests().anyRequest().permitAll();
//  }
//}
