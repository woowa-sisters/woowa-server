package com.woowaSisters.woowaSisters.config;

import com.woowaSisters.woowaSisters.domain.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

// 구글 로그인 관련
import com.woowaSisters.woowaSisters.service.PrincipalOauth2UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@RequiredArgsConstructor
//@EnableWebSecurity
//@Configuration

//@Lazy
//@EnableJpaRepositories(basePackages = {"com.hangangFlow.hangangFlow.domain.user", "com.hangangFlow.hangangFlow.domain", "com.hangangFlow.hangangFlow.domain.park"}) // "com.hangangFlow.hangangFlow.domain.park.Parks" 패키지를 추가
//public class SecurityConfig {
//    private final PrincipalOauth2UserService principalOauth2UserService;
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                // Request
//                .authorizeRequests()
//                // 인증
//                .antMatchers("/security-login/info").authenticated()
//                // 인가
//                .antMatchers("/security-login/admin/**").hasAuthority(UserRole.ADMIN.name())
//                .anyRequest().permitAll()
//
////                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
////                .antMatchers(
////                        "/api/login", "/api/register"
////                ).permitAll()
////                .anyRequest().hasAnyRole("USER")
//                .and()
////                .oauth2Login()
////                .loginPage("/security-login/login")
////                .defaultSuccessUrl("/security-login")
////                .userInfoEndpoint()
////                .userService(principalOauth2UserService)
////                .and()
//                // 사용하지 않는 필터
//                .formLogin()
//                .disable()
//                .csrf()
//                .disable()
//                .headers()
//                .disable()
//                .httpBasic()
//                .disable()
//                .rememberMe()
//                .disable()
//                .logout()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////                .and()
//                // 인증/인가 실패 Response
////                .exceptionHandling()
////                .authenticationEntryPoint(authenticationEntryPoint(objectMapper()))
////                .accessDeniedHandler(accessDeniedHandler(objectMapper()))
////                .and()
//                // Redis Session
////                .addFilterBefore(userAuthenticationFilter(),
////                        UsernamePasswordAuthenticationFilter.class)
//                // CORS
////                .cors().configurationSource(corsConfigurationSource());
//
//
//        return http.build();
//    }
//
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
////            http
////                    .exceptionHandling()
////                    .authenticationEntryPoint(new MyAuthenticationEntryPoint())
////                    .accessDeniedHandler(new MyAccessDeniedHandler());
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
//}



//
//
//
@RequiredArgsConstructor
//@EnableWebSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOauth2UserService principalOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                // 인증
                .antMatchers("/security-login/info").authenticated()
                // 인가
                .antMatchers("/security-login/admin/**").hasAuthority(UserRole.ADMIN.name())
                .anyRequest().permitAll()
                .and()
                // Form Login 방식 적용
                .formLogin()
                // 로그인 할 때 사용할 파라미터들
                .usernameParameter("loginId")
                .passwordParameter("password")
                .loginPage("/security-login/login")     // 로그인 페이지 URL
                .defaultSuccessUrl("/security-login")   // 로그인 성공 시 이동할 URL
                .failureUrl("/security-login/login")    // 로그인 실패 시 이동할 URL
                .and()
                .logout()
                .logoutUrl("/security-login/logout")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                // OAuth 로그인
                .and()
                .oauth2Login()
                .loginPage("/security-login/login")
                .defaultSuccessUrl("/security-login")
                .userInfoEndpoint()
                .userService(principalOauth2UserService)
        ;
//        http
//                .exceptionHandling()
//                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
//                .accessDeniedHandler(new MyAccessDeniedHandler());
    }
}