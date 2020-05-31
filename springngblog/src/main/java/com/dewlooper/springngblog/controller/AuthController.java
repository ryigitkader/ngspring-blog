package com.dewlooper.springngblog.controller;

import com.dewlooper.springngblog.dto.LoginRequest;
import com.dewlooper.springngblog.dto.RegisterRequest;
import com.dewlooper.springngblog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody RegisterRequest registerRequest){

        authService.signUp(registerRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){

        return authService.login(loginRequest);
    }

}
