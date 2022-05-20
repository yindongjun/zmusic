package com.example.zmusic.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.zmusic.constants.AuthenticationConfigConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);

        if (header == null || !header.startsWith(AuthenticationConfigConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken token = getUsernamePasswordAuthenticationToken(request);

        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);
        if (token != null) {
            DecodedJWT verify = JWT.require(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET))
                    .build()
                    .verify(token.replace(AuthenticationConfigConstants.TOKEN_PREFIX, ""));
            String username = verify.getSubject();
            List<GrantedAuthority> authorities = getAuthority(verify);
            if (username == null) {
                return null;
            }

            return new UsernamePasswordAuthenticationToken(
                    username, null, authorities
            );
        }
        return null;
    }

    private List<GrantedAuthority> getAuthority(DecodedJWT decodedJWT) {
        return Optional.ofNullable(decodedJWT.getClaim("roles").asList(String.class))
                .orElseGet(ArrayList::new)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
