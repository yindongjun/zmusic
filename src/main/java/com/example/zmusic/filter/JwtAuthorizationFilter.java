package com.example.zmusic.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.zmusic.constants.AuthenticationConfigConstants;
import com.example.zmusic.dto.UserDto;
import com.example.zmusic.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserService userService;

    public JwtAuthorizationFilter(
            AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);

        // invalid token
        if (header == null || !header.startsWith(AuthenticationConfigConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // generate username password authentication token
        UsernamePasswordAuthenticationToken token = getUsernamePasswordAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(token);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(
            HttpServletRequest request) {
        String token = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);
        if (StringUtils.hasText(token)) {
            try {
                DecodedJWT verify =
                        JWT.require(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET))
                                .build()
                                .verify(token.replace(AuthenticationConfigConstants.TOKEN_PREFIX, ""));
                String username = verify.getSubject();
                if (username == null) {
                    return null;
                }

                UserDetails user = userService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken createdToken =
                        new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());

                setDetails(createdToken, username);

                return createdToken;
            } catch (Exception e) {
                log.warn("令牌校验异常, 令牌: {}, 异常信息: {}", token, e.getMessage());
            }
        }
        return null;
    }

    private void setDetails(UsernamePasswordAuthenticationToken token, String username) {
        UserDto user = userService.getByUsername(username);
        token.setDetails(user);
    }
}
