package ru.eternallyu.taskschedulerbackend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@UtilityClass
public class JwtUtils {

    public static final String USER_DETAILS = "User Details";
    public static final String JOB_TRACKER_APPLICATION = "JOB TRACKER APPLICATION";
    public static final String EMAIL = "email";
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email) throws
            IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject(USER_DETAILS)
                .withClaim(EMAIL, email)
                .withIssuedAt(new Date())
                .withIssuer(JOB_TRACKER_APPLICATION)
                .sign(Algorithm.HMAC256(secret));

    }

    public String validateTokenAndRetrieveSubject(String token) throws
            JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject(USER_DETAILS)
                .withIssuer(JOB_TRACKER_APPLICATION)
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim(EMAIL).asString();
    }


}

