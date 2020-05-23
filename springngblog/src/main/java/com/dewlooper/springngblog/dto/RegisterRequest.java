package com.dewlooper.springngblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String userName;
    private String password;
    private String email;


}
