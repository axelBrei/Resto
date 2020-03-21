package com.axelynicky.api_gateway.Security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.axelynicky.api_gateway.Domain.Client;
import com.axelynicky.api_gateway.Service.Client.ClientService;
import com.axelynicky.api_gateway.Utils.JwtTokenUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    @Autowired
    ClientService clientService;

    JwtTokenUtility jwtTokenUtility = new JwtTokenUtility();

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER);
        return bearerToken.substring(7).trim();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            if (existeJWTToken(request, response) && isValidToken(request)) {
                String token = getTokenFromRequest(request);
                Client client = jwtTokenUtility.getAllClaimsFromToken(token);

                List<? extends GrantedAuthority> authorities = Arrays.asList(
                        new SimpleGrantedAuthority(client.getName())
                );

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(client.getId(), client, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            chain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Credenciales Invalidas");
            return;
        }
    }

    private boolean existeJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX))
            return false;
        return true;
    }

    private Boolean isValidToken(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        return jwtTokenUtility.isValidToken(token);
    }
}
