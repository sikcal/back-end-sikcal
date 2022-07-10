package com.prob_jr.sikcal_app.domain.config.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * 인증필터 토큰이 제대로된 토큰인지 확인
 * app에 들어오는 모든 요청을 가로채 해당 특정 토큰을 찾은 다음
 * 사용자가 특정 리소스에 엑세스 할 수 있는지 여부를 결정
 */

public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/login")){
            //user가 로그인부분에 들어왔다는걸 확인하고 filter적용할 준비
            filterChain.doFilter(request,response);
        }
        else{
            //권한부여 헤더 키 받아오기
            String authorizationHeader = request.getHeader(AUTHORIZATION);

        }
    }
}
