package com.prob_jr.sikcal_app.domain.config;

import com.prob_jr.sikcal_app.domain.config.filter.CustomAuthenticationFilter;
import com.prob_jr.sikcal_app.domain.config.filter.CustomAuthorizationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
/**
 * spring security를 이용해 pw 해싱
 */
public class SpringSecurity extends
        WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/api/token/refresh","/api/join");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    /**
    * stateless 쿠키와 다르게 reauest가 독립적으로 다뤄짐 요청끼리 연결이 x 메모리가 x -> app이나 ios는 session사용 불가? 쿠키를 만들수 없음?
     * jwt는 세션 DB필요 x db resource필요하지 x 준 token을 유효한지 검증만 하면 됌 -> ex QrCode
     * 쿠키=그냥 옮기는 시스템-매개체 , ㅌ토큰=서버가 기억하는 이상하게 생간 String, session과 token의 장단점 !
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // FILTER PATH재정의 해주기 EXTENS부분 들어가면 DEFAULT로 /LOGIN되어있음 우리 app에 맞게 customize해주기

        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setUsernameParameter("memberId");
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS);
        //토큰없이도 모든자에게 permit되는 사이트 주소록들  swagger 밑 login , 회원가입 부분
        http.authorizeRequests().antMatchers("/api/login/**","/api/token/refresh/**","/api/join/**","/swagger-ui.html","/swagger/**",
                "/swagger-resources/**","/webjars/**","/v2/api-docs").permitAll();
        http.authorizeRequests().antMatchers(GET,"/api/user/**").hasAnyAuthority("ROLE_USER"); //해당 주소 뒤로는 권한이 있는사람만 접근가능
        http.authorizeRequests().antMatchers(POST,"/api/user/**").hasAnyAuthority("ROLE_USER");
        http.authorizeRequests().anyRequest().authenticated(); //인증을 쓰겠다 permitall이 아닌
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);


        /*
        http.csrf().disable()
                .sessionManagement().
                sessionCreationPolicy(STATELESS);
        http.authorizeRequests().anyRequest().permitAll();*/

    }
    //이 원리 말로 설명 못하겠음 너무 어려움 ㅠ
    //대충 상속받는 super클래스에있는 manager 의존성주입하는느낌 ex entitymanager너낌 임
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
