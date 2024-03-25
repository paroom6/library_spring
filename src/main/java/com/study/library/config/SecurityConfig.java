package com.study.library.config;

import com.study.library.Service.OAuth2PrincipalUserService;
import com.study.library.security.exception.AuthEntryPoint;
import com.study.library.security.filter.JwtAuthenticationFilter;
import com.study.library.security.filter.PerminAllfilter;
import com.study.library.security.handler.OAuth2SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private OAuth2PrincipalUserService oAuth2PrincipalUserService;
    @Autowired
    private PerminAllfilter perminAllfilter;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private AuthEntryPoint authEntryPoint;
    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();//WebMvcConfig의 cors 설정을 따라간다.
        http.csrf().disable();
//        필터 처리후 - 권한확인
        http.authorizeRequests()
                .antMatchers("/server/**", "/auth/**")
                .permitAll()
                .antMatchers("/mail/authenticate")
                .permitAll()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .addFilterAfter(perminAllfilter, LogoutFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .oauth2Login()
                .successHandler(oAuth2SuccessHandler)
                .userInfoEndpoint()
                //정보를 구해서  userService로 이동
                .userService(oAuth2PrincipalUserService);
    }

}
