package com.dewlooper.springngblog.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;

@Service
public class JwtProvider {

    private Key key;

    @PostConstruct
    public void init(){

        key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateToken(Authentication authentication){

         User principal =(User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(key)
                .compact();
    }


    public boolean validateToken(String jwt){
        /*
        ** Old version
        Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
        */


        Jwts.parserBuilder().build().parseClaimsJws(jwt);
        return true;
    }

    public String getUserNameFromJWT(String token) {


        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();


        /*
        ** Old version
        Claims claims = Jwts.parser()
                        .setSigningKey(key)
                        .parseClaimsJws(token)
                        .getBody();

        **/


    }
}
