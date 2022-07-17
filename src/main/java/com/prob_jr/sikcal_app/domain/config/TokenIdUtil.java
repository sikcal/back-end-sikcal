package com.prob_jr.sikcal_app.domain.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenIdUtil {
    public static String Decoder(String access_token){
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); //전에 알고리즘으로 서명했기에 verify하기 위해서 알고리즘으로 확인 필요
        JWTVerifier verifier = JWT.require(algorithm).build(); //검증기 제작
        DecodedJWT decodedJWT = verifier.verify(access_token); //토큰 검증하기
        return decodedJWT.getSubject(); //TOKEN에 SUBJECT에 MEMBERID저장해놨었음 !! JWT.IO확인!
    }

}
