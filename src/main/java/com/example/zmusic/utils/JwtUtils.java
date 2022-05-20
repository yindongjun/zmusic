package com.example.zmusic.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.List;

public class JwtUtils {
    public static String generate(String subject, List<String> roles, long expireTime, String secret) {
        return JWT.create()
                .withSubject(subject)
                .withClaim("roles", roles)
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .sign(Algorithm.HMAC512(secret));
    }
}
