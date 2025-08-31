package com.vbcodes.schooltransport.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.vbcodes.schooltransport.jwt.AuthEntryPointJWT;
import com.vbcodes.schooltransport.jwt.CustomJWTFilter;

@Configuration
public class ProjectSecurtiyConfiguration {

    @Autowired
    private AuthEntryPointJWT unauthorizedHandler;

    @Bean
    public CustomJWTFilter authenticationJwtTokenFilter() {
        return new CustomJWTFilter();
    }

    @Bean
    SecurityFilterChain projectSecurityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(c -> c.disable())
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(mvc.pattern(HttpMethod.OPTIONS, "/**")).permitAll() // Allow pre-flight requests, necessary when we add Authorization header from the client for JWT
                    .requestMatchers(mvc.pattern("/getData")).permitAll()
                    .requestMatchers(mvc.pattern("/login")).permitAll()
                    .requestMatchers(mvc.pattern("/register/**")).permitAll()
                    .requestMatchers(mvc.pattern("/vehicles/add")).hasRole("ORGANIZATION")
                    .anyRequest().authenticated())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
            .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}