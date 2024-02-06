package com.woowaSisters.woowaSisters.config;


import com.woowaSisters.woowaSisters.filter.CustomAuthenticationFilter;
import com.woowaSisters.woowaSisters.oauth.AuthService;
import com.woowaSisters.woowaSisters.oauth.PrincipalOAuth2DetailsService;
import com.woowaSisters.woowaSisters.oauth.jwt.JwtTokenProvider;
import com.woowaSisters.woowaSisters.oauth.jwt.config.CustomAccessDeniedHandler;
import com.woowaSisters.woowaSisters.oauth.jwt.config.CustomAuthenticationEntryPoint;
import com.woowaSisters.woowaSisters.oauth.jwt.config.OAuth2SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;


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
//@RequiredArgsConstructor
//@EnableWebSecurity
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOAuth2DetailsService principalOAuth2DetailsService;
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    private final CustomAuthenticationFilter customAuthenticationFilter;


    @Autowired
    public SecurityConfig(CustomAuthenticationFilter customAuthenticationFilter, PrincipalOAuth2DetailsService principalOAuth2DetailsService, AuthService authService, JwtTokenProvider jwtTokenProvider) {
        this.principalOAuth2DetailsService = principalOAuth2DetailsService;
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customAuthenticationFilter = customAuthenticationFilter;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/v1/token/save", "/v1/logout").permitAll() // 특정 경로에 대한 인증 요구 제외
                .anyRequest().authenticated() // 그 외 모든 요청은 인증이 필요함
                .and()
                .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // 커스텀 필터 적용
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(principalOAuth2DetailsService)
                .and()
                .successHandler(new OAuth2SuccessHandler(jwtTokenProvider, authService));
    }


}