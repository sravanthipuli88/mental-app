package com.sco.mental.service;


import com.sco.mental.security.CustomUserDetails;
import com.sco.mental.entity.User;
import com.sco.mental.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(userName)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: " + userName));

        return new CustomUserDetails(user);
    }
}