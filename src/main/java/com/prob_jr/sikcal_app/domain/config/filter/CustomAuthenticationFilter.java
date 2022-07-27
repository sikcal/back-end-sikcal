package com.prob_jr.sikcal_app.domain.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prob_jr.sikcal_app.domain.controller.MemberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager ) {
        this.authenticationManager = authenticationManager;
    }

    //회원이 인증 시도 Authentication
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //프론트쪽에서 사용할 로그인 폼 parameter설정 저렇게 해달라고 요청
        String memberId = request.getParameter("userid");
        String password = request.getParameter("password");
        //로그인할때마다 log만들깅
        LOGGER.info("login 한 너의 id: {} ", memberId);
        LOGGER.info("login 한 너의 비번 : {} ", password);
        //아이디와 비번으로 인증token 생성
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(memberId,password);
        //매니저한테 token전달
        return authenticationManager.authenticate(authenticationToken);
    }

    //로그인 성공시 = 위에 인증 성공시 액세스 토큰과 refresh토큰 만들기
    //이게 이제 보면 request response있는걸 봐서 클라이언트 단으로 토큰을 다시 보내는 역할
    // 사용자에게 엑세스 토큰과 성공적으로 로그인한 후 refresh토큰 부여
    //로그인이 성공 한 후 밑 함수가 호출됌
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal(); //인증된 사용자 객체를 반환 캐스팅해야함  여기까지 사용자인증이 됌
        //알고리즘 정의
        //json web token jst와 refreshtoken에 서명 하는데 사용ㅇ할 알고리즘임
        Algorithm algorithm= Algorithm.HMAC256("secret".getBytes());
        String access_token = JWT.create()
                .withSubject(user.getUsername()) //회원 고유한걸로 해야함 key
                .withExpiresAt(new Date((System.currentTimeMillis() +60*60*1000))) //현재시간에서 일단 10분동안으로
                .withIssuer(request.getRequestURI().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        String refresh_token = JWT.create()
                .withSubject(user.getUsername()) //회원 고유한걸로 해야함 key
                .withExpiresAt(new Date((System.currentTimeMillis() +365*24*60*60*1000))) // refresh는 30분만 한달 1년 설정가능
                .withIssuer(request.getRequestURI().toString())
                .sign(algorithm);
        //프론트쪽에 토큰 보내기
        /* 헤더에다가만 보내기
        response.setHeader("access_token",access_token);
        response.setHeader("refresh_token",refresh_token);*/
        //위에는 header에다 보냈지만 json형식으로 보내보기
        Map<String, String> tokens= new HashMap<>();
        tokens.put("access_token",access_token);
        tokens.put("refresh_token",refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}

