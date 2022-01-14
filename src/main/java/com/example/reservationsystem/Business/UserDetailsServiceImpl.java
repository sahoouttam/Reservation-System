package com.example.reservationsystem.Business;

import com.example.reservationsystem.Persistence.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (user == null) throw new UsernameNotFoundException(username);

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                                    .password(user.getPasswordHash()).roles("USER").build();

        return userDetails;
    }
    
}
