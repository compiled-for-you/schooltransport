package com.vbcodes.schooltransport.configuration;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurtiyConfiguration {
    
    @Bean
    SecurityFilterChain projectSecurityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf(c -> c.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(antMatcher("/getData")
                                ,antMatcher("/students")
                                ,antMatcher("/register/**"))
                // .anyRequest()
                                .permitAll()
                .anyRequest().authenticated()
            )
            // .formLogin(login -> 
            //     login.loginPage("/check").permitAll())
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}