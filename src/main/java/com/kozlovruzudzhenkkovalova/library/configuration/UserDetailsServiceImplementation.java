//package com.kozlovruzudzhenkkovalova.library.configuration;
//
//import com.kozlovruzudzhenkkovalova.library.entity.Role;
//import com.kozlovruzudzhenkkovalova.library.entity.User;
//import com.kozlovruzudzhenkkovalova.library.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User.UserBuilder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//public class UserDetailsServiceImplementation implements UserDetailsService {
//
//  private final UserRepository userRepository;
//
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    User user = userRepository.findByUsername(username);
//    UserBuilder userBuilder;
//    if(user != null) {
//      userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
//      userBuilder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
//      var userRoles = user.getRoles();
//      List<String> roleNames = userRoles.stream().map(Role::getName).collect(Collectors.toList());
//      String[] roles = new String[roleNames.size()];
//      roleNames.toArray(roles);
//      userBuilder.roles(roles);
//    } else {
//      throw new UsernameNotFoundException("User not found");
//    }
//    return userBuilder.build();
//  }
//}
