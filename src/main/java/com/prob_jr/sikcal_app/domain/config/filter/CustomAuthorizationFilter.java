package com.prob_jr.sikcal_app.domain.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prob_jr.sikcal_app.domain.controller.MemberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * 인증필터 토큰이 제대로된 토큰인지 확인
 * app에 들어오는 모든 요청을 가로채 해당 특정 토큰을 찾은 다음
 * 사용자가 특정 리소스에 엑세스 할 수 있는지 여부를 결정
 * 이게 token의 장점 token가지고만 유효한지만 검증하면 되기에 세션DB가 필요없음
 */

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

    //여기서  url 가로챔
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/login") ){//|| request.getServletPath().equals("/api/token/refresh")
            //user가 로그인부분에 들어왔다는걸 확인하고 filter적용할 준비
            filterChain.doFilter(request,response);
            LOGGER.info("login, refresh들어옴");
        }
        else{
            //권한부여 헤더 키 받아오기
            String authorizationHeader = request.getHeader(AUTHORIZATION); //header에 Authorization이라고 전달
            LOGGER.info("입력 제대로 받아옴? {}", authorizationHeader);
            if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){ //인증헤더임을 확인 시작은 Bearer
                try {
                    LOGGER.info("토큰 유효성 검사 시작 ");
                    String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); //전에 알고리즘으로 서명했기에 verify하기 위해서 알고리즘으로 확인 필요
                    JWTVerifier verifier = JWT.require(algorithm).build(); //검증기 제작
                    DecodedJWT decodedJWT = verifier.verify(token); //토큰 검증하기
                    String userid = decodedJWT.getSubject();
                    LOGGER.info("토큰 유효성 검사 시작22 ");
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class); //roles갖고오기
                    Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
                    stream(roles).forEach( role ->{
                        authorities.add(new SimpleGrantedAuthority(role));
                        // 권한확장을 위한 converter 변환기
                        //사용자 권한 명칭을 정해놓음
                    });
                    LOGGER.info("토큰 유효성 검사 시작33 ");
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userid,null,authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    filterChain.doFilter(request,response); //아직 인증이 진행중임
                }catch (Exception exception){ //토큰이 valid 만료되었거나 무슨일이 생길때
                    LOGGER.error("토큰 varify 과정중 error발생:{} ",exception.getMessage() );
                   // response.sendError(FORBIDDEN.value()); //403 forbidden 코드
                    response.setStatus(FORBIDDEN.value());
                    //에러시 json형태로 보내기
                    Map<String, String> error= new HashMap<>();
                    error.put("error_message",exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            }
            else{
                LOGGER.info("else부분!!!!");
                filterChain.doFilter(request,response);
            }

        }
    }
}
