package com.dewlooper.springngblog.service;

import com.dewlooper.springngblog.dto.LoginRequest;
import com.dewlooper.springngblog.dto.RegisterRequest;
import com.dewlooper.springngblog.model.User;
import com.dewlooper.springngblog.repository.IUserRepository;
import com.dewlooper.springngblog.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    public void signUp(RegisterRequest registerRequest){

        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());

        userRepository.save(user);
    }


    private String encodePassword(String password) {

        return passwordEncoder.encode(password);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(),
                        loginRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);

        return new AuthenticationResponse(authenticationToken, loginRequest.getUserName());

    }

    //!
    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User)
                                                                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Optional.of(principal);
    }
}
