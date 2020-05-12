package com.kozlovruzudzhenkkovalova.library.configuration;

import com.kozlovruzudzhenkkovalova.library.entity.Role;
import com.kozlovruzudzhenkkovalova.library.entity.User;
import com.kozlovruzudzhenkkovalova.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service("userDetailsService")
@Slf4j
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
      User user = userRepository.findByUsername(username).orElseThrow(
          () -> new UsernameNotFoundException("User not found."));

      Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
      for (Role role : user.getRoles()) {
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
      }

      return new org.springframework.security.core.userdetails.User(
          user.getUsername(), user.getPassword(), grantedAuthorities);
  }

}
