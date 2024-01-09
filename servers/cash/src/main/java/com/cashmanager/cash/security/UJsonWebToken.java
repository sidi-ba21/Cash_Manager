package com.cashmanager.cash.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UJsonWebToken {

    public static Map<String, String> generateTokens(String subject, String issuer) {
        Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.JWT_SECRET.getBytes());

        String accessToken = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.JWT_TOKEN_DURATION))
                .withIssuer(issuer)
                .sign(algorithm)
                ;

        String refreshToken = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.JWT_REFRESH_DURATION))
                .withIssuer(issuer)
                .sign(algorithm)
                ;

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

}
