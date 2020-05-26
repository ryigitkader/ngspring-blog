package com.dewlooper.springngblog.service;

import com.dewlooper.springngblog.model.User;
import com.dewlooper.springngblog.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user =  userRepository.findByUserName(userName).orElseThrow(()->new UsernameNotFoundException("No user found" + userName));
        return new org.springframework
                .security.core.userdetails
                .User(user.getUserName(),user.getPassword(),true,true,
                true, true,
                getAuthorities("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {

        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }
}
