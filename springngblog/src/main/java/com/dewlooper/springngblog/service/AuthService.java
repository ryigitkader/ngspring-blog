package com.dewlooper.springngblog.service;

import com.dewlooper.springngblog.dto.RegisterRequest;
import com.dewlooper.springngblog.model.User;
import com.dewlooper.springngblog.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private IUserRepository userRepository;

    public void signUp(RegisterRequest registerRequest){

        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());

        userRepository.save(user);
    }

}
