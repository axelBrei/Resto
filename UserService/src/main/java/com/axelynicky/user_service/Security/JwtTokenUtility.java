package com.axelynicky.user_service.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.axelynicky.user_service.Domain.Client;
import com.axelynicky.user_service.Domain.Role;

import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenUtility {

    private static final String SECRET = "nickyteamo";
    private static final String API_SUBJECT = "resto-gateway";
    private static final String USERNAME_CLAIM = "username";
    private static final String USER_ID_CLAIM = "userId";
    private static final String LAST_NAME_CLAIM = "lastName";
    private static final String ROLE_CLAIM = "role";
    private static final Algorithm ALGORITHM = Algorithm.HMAC512(SECRET);

    public String createAutorizationToken() {
        return JWT.create()
                .withSubject(API_SUBJECT)
                .withClaim(USERNAME_CLAIM, API_SUBJECT)
                .withIssuedAt(new Date())
                .sign(ALGORITHM);
    }

    public Boolean isValidAuthorizationToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject().equals(API_SUBJECT) &&
                    decodedJWT.getClaim(USERNAME_CLAIM).asString().equals(API_SUBJECT) &&
                    isIssuedBetween(decodedJWT.getIssuedAt());
        }catch (JWTVerificationException e){
            return false;
        }
    }

    public String getTokenSubject(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        return verifier.verify(token).getSubject();
    }

    private Boolean isIssuedBetween(Date issuedAt) {
        Long timeInMills = System.currentTimeMillis();
        int hour = 15 * 60 * 1000; // 15 Minutes
        return issuedAt.after(new Date(timeInMills - hour)) && issuedAt.before(new Date(timeInMills + hour));
    }

    //for retrieveing any information from token we will need the secret key
    public Client getAllClaimsFromToken(String token) {
        Client client = new Client();
        DecodedJWT decodedJWT = JWT.decode(token);
        client.setId(decodedJWT.getClaim(USER_ID_CLAIM).asLong());
        client.setName(decodedJWT.getClaim(USERNAME_CLAIM).asString());
        client.setLastName(decodedJWT.getClaim(LAST_NAME_CLAIM).asString());
        client.setRole(new Role(decodedJWT.getClaim(ROLE_CLAIM).asString()));
        return client;
    }

    public Boolean isValidToken(String token) {
        try{
            JWTVerifier verifier = JWT.require(ALGORITHM).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            Client client = getAllClaimsFromToken(token);
            return decodedJWT.getAlgorithm().equals(ALGORITHM.getName()) && client.getName().equals(decodedJWT.getSubject());
        }catch (JWTVerificationException e){
            return false;
        }
    }
}
