package com.kozlovruzudzhenkkovalova.library.configuration;

import com.kozlovruzudzhenkkovalova.library.entity.Role;
import com.kozlovruzudzhenkkovalova.library.entity.User;
import com.kozlovruzudzhenkkovalova.library.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service("userDetailsService")
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
      User user = userRepository.findByUsername(username).orElseThrow(
          () -> new UsernameNotFoundException("User not found."));

      return new org.springframework.security.core.userdetails.User(
          user.getUsername(), user.getPassword(), getAuthorities(user));
  }

  private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
      String[] userRoles = user.getRoles().stream().map((role) -> "ROLE_" + role.getName().toUpperCase()).toArray(String[]::new);
    return AuthorityUtils.createAuthorityList(userRoles);
  }

}
