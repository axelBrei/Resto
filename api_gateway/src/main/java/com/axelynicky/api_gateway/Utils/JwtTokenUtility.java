package com.axelynicky.api_gateway.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.axelynicky.api_gateway.Domain.Client;
import com.axelynicky.api_gateway.Domain.Role;

import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenUtility {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private static final String USER_ID_CLAIM = "userId";
    private static final String USERNAME_CLAIM = "username";
    private static final String LAST_NAME_CLAIM = "lastName";
    private static final String ROLE_CLAIM = "role";
    private static final String SECRET = "nickyteamo";
    private static final String API_SUBJECT = "resto-gateway";
    private static final Algorithm ALGORITHM = Algorithm.HMAC512(SECRET);


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

    private JWTCreator.Builder getClaimsFromClient(Client client) {
        return JWT.create()
                .withClaim(USER_ID_CLAIM, client.getId())
                .withClaim(USERNAME_CLAIM, client.getName())
                .withClaim(ROLE_CLAIM, client.getRole().getName())
                .withClaim(LAST_NAME_CLAIM, client.getLastName());
    }

    public Boolean isValidToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(ALGORITHM).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        Client client = getAllClaimsFromToken(token);
        return decodedJWT.getAlgorithm().equals(ALGORITHM.getName()) && client.getName().equals(decodedJWT.getSubject());
    }

    //generate token for user
    public String generateToken(Client client) {
        JWTCreator.Builder builder = getClaimsFromClient(client);
        builder.withSubject(client.getName());
        builder.withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000));
        builder.withIssuedAt(new Date(System.currentTimeMillis()));
        return builder.sign(ALGORITHM);
    }



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
            return decodedJWT.getSubject().equals(API_SUBJECT) && decodedJWT.getClaim(USERNAME_CLAIM).equals(API_SUBJECT);
        }catch (JWTVerificationException e){
            return false;
        }
    }
}
