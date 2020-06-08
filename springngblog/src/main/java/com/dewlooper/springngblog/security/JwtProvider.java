package com.dewlooper.springngblog.security;


import com.dewlooper.springngblog.exception.SpringBlogException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {

    private KeyStore keyStore;

    @PostConstruct
    public void init() throws SpringBlogException {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springngblog.jks");
            keyStore.load(resourceAsStream,"springblog".toCharArray());

        }catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e){
            throw  new SpringBlogException("Exception occured while loading keystore");

        }

    }

    public String generateToken(Authentication authentication) throws SpringBlogException {

         User principal =(User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(getPrivateKey())
                .compact();
    }

    private PrivateKey getPrivateKey() throws SpringBlogException {
        try {
            return (PrivateKey)keyStore.getKey("springngblog","springblog".toCharArray());
        }catch (Exception e){
            throw new SpringBlogException("Exception occured while retrieving public key from keystore");
        }


    }


    public boolean validateToken(String jwt){

        Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublicKey() throws SpringBlogException {
        try {
            return keyStore.getCertificate("springngblog").getPublicKey();
        }catch (Exception e){
            throw new SpringBlogException("Exception occured while retrieving public key from keystore");

        }

    }

    public String getUserNameFromJWT(String token) throws SpringBlogException {


        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getPublicKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();





    }
}
