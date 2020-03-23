package com.axelynicky.restorant_service.Security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.axelynicky.restorant_service.Domain.Client;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private static final String ACCES_TOKEN_HEADER = "ACCESS_TOKEN";
    private static final String USER_TOKEN_HEADER = "USER_TOKEN";


    JwtTokenUtility jwtTokenUtility = new JwtTokenUtility();

    private String getTokenFromRequest(HttpServletRequest request, String tokenType) {
        String bearerToken = request.getHeader(tokenType);
        return bearerToken.substring(7).trim();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        try {
            if (existeJWTToken(request, ACCES_TOKEN_HEADER)) {
                String accesToken = getTokenFromRequest(request, ACCES_TOKEN_HEADER);
                if(jwtTokenUtility.isValidAuthorizationToken(accesToken)) {
                    String subject = jwtTokenUtility.getTokenSubject(accesToken);


                    List<? extends GrantedAuthority> authorities = Arrays.asList(
                            new SimpleGrantedAuthority(subject)
                    );

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(subject, null, authorities);

                    if(existeJWTToken(request, USER_TOKEN_HEADER)){
                        String userToken = getTokenFromRequest(request, USER_TOKEN_HEADER);
                        Client client = jwtTokenUtility.getAllClaimsFromToken(userToken);
                        if(!client.getRole().getName().isEmpty()) {
                            authorities = Arrays.asList(
                                    new SimpleGrantedAuthority("ROLE_" + client.getRole().getName())
                            );
                            auth = new UsernamePasswordAuthenticationToken(client, null, authorities);
                        }
                    }

                    SecurityContextHolder.getContext().setAuthentication(auth);
                    chain.doFilter(request,response);
                    return;
                }
            }
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Autorizacion incorrecta");
            chain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Autorizacion incorrecta");
            return;
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ha ocurrido un error inesperado");
        }
    }




    private boolean existeJWTToken(HttpServletRequest request, String tokenType) {
        String authenticationHeader = request.getHeader(tokenType);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
            return false;
        return true;
    }



}
