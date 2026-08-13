package com.sco.mental.service;

import com.sco.mental.dto.AuthResponse;
import com.sco.mental.dto.LoginRequest;
import com.sco.mental.dto.RegisterRequest;
import com.sco.mental.entity.User;
import com.sco.mental.repository.UserRepository;
import com.sco.mental.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.username())) {
            throw new IllegalArgumentException("That username is already taken.");
        }

        User user = new User();
        user.setUsername(req.username());
        user.setPasswordHash(passwordEncoder.encode(req.password()));
        user.setFirstName(req.firstName());
        user.setLastName(req.lastName());
        user.setGender(req.gender());
        user.setAgeBucket(req.ageBucket());

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername(), user.getId());
        return toAuthResponse(user, token);
    }

    public AuthResponse login(LoginRequest req) {
    	System.out.println("req.username()  "+req.username());
    	
    	System.out.println("req.getPassword() "+req.password());
    	
        User user = userRepository.findByUsername(req.username())
                .orElseThrow(() -> new IllegalArgumentException("Incorrect username or password."));

        System.out.println("user.getPasswordHash()  "+user.getPasswordHash());
		/*
		 * if (!passwordEncoder.matches(req.password(), user.getPasswordHash())) {
		 * System.out.println("inside password encoder checking"+req.password()); throw
		 * new IllegalArgumentException("Incorrect username or password."); }
		 */

        String token = jwtService.generateToken(user.getUsername(), user.getId());
        return toAuthResponse(user, token);
    }

    private AuthResponse toAuthResponse(User user, String token) {
        return new AuthResponse(
                token,
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getGender(),
                user.getAgeBucket()
        );
    }
}
