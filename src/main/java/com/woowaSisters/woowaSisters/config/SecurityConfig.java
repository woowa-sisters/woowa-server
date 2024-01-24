package com.woowaSisters.woowaSisters.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;


//@Lazy
@EnableWebSecurity
//@EnableJpaRepositories(basePackages = {"com.hangangFlow.hangangFlow.domain.user", "com.hangangFlow.hangangFlow.domain", "com.hangangFlow.hangangFlow.domain.park"}) // "com.hangangFlow.hangangFlow.domain.park.Parks" 패키지를 추가
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Request
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                .antMatchers(
//                        "/api/login", "/api/register"
//                ).permitAll()
//                .anyRequest().hasAnyRole("USER")
                .and()
                // 사용하지 않는 필터
                .formLogin()
                .disable()
                .csrf()
                .disable()
                .headers()
                .disable()
                .httpBasic()
                .disable()
                .rememberMe()
                .disable()
                .logout()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .and()
                // 인증/인가 실패 Response
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint(objectMapper()))
//                .accessDeniedHandler(accessDeniedHandler(objectMapper()))
//                .and()
                // Redis Session
//                .addFilterBefore(userAuthenticationFilter(),
//                        UsernamePasswordAuthenticationFilter.class)
                // CORS
//                .cors().configurationSource(corsConfigurationSource());

        return http.build();
    }

//
//    @Bean
//    public SecurityFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {
//
//            // 인가(접근권한) 설정
////            http.authorizeRequests()
//////                    .antMatchers(HttpMethod.POST.valueOf("/api/register")).permitAll()
////                    .antMatchers(HttpMethod.valueOf("/api/**")).authenticated();
//
//            // 사이트 위변조 요청 방지 비활성화
//            http.csrf().disable();
//
//            // 세션 관리 설정
//            http.sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//            // 로그인 설정
//            http.formLogin()
//                    .loginProcessingUrl("/api/login")
//                    .defaultSuccessUrl("/api/home")
//                    .failureUrl("/api/login?error")
//                    .usernameParameter("loginId")
//                    .passwordParameter("password");
//
//            // 로그아웃 설정
//            http.logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
//                    .logoutSuccessUrl("/api/login?logout")
//                    .invalidateHttpSession(true)
//                    .deleteCookies("JSESSIONID");
//
//            return http.build();
//
//
//    }
//
}
