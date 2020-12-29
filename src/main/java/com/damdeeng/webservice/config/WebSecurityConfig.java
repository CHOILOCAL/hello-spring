package com.damdeeng.webservice.config;

import com.damdeeng.webservice.common.service.CustomOAuth2UserService;
import com.damdeeng.webservice.test.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    // 정적 자원 Security 설정 X
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // 어떤 요청에 대해서 로그인을 요구할것이고, 어떤 요청에서 로그인을 요구하지 않을 것 인지 설정
    // form 기반 로그인 비활성화 -> json, post로 요청오는 API에 한함
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
            .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

//        http.csrf().disable().authorizeRequests()
//                // 토큰 활용시 모든 요청에 대한 접근 가능
//                .anyRequest().permitAll()
//                .and()
//                // 토큰을 활용하면 세션이 필요 없으므로 Stateless 설정 -> session 사용 X
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                // form 기반의 로그인에 대해 비활성화
//                .formLogin()
//                .disable();
    }

    // BCryptPasswordEncoder 객체에서 encode, matcheds 등 평문 해시화 기능, 해시결과 일치여부 확인 기능 제공
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
//
//        // authenticationManager (실제 인증을 처리할 여러 개의 Provider 존재)
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
//
//        customAuthenticationFilter.setFilterProcessesUrl("/user/login");
//        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
//        customAuthenticationFilter.afterPropertiesSet();
//
//        return customAuthenticationFilter;
//    }
//
//    @Bean
//    public CustomLoginSuccessHandler customLoginSuccessHandler() {
//        return new CustomLoginSuccessHandler();
//    }
//
//    @Bean
//    public CustomAuthenticationProvider customAuthenticationProvider() {
//        return new CustomAuthenticationProvider(bCryptPasswordEncoder());
//    }

//    @Bean
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
//        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
//    }

}
