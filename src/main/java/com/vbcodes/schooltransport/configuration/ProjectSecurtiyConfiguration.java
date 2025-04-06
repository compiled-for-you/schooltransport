package com.vbcodes.schooltransport.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import jakarta.servlet.DispatcherType;

@Configuration
public class ProjectSecurtiyConfiguration {
    
    @Bean
    SecurityFilterChain projectSecurityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception{
        http
            .csrf(c -> c.disable())
            .authorizeHttpRequests(authorize -> authorize
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() //this line allows Spring Security all redirects, apparently any request routing from a controller to a jsp is also a redirect to the WEB-INF directory, so now it works!
                .requestMatchers(mvc.pattern("/getData")).permitAll()
                .requestMatchers(mvc.pattern("/register/**"), mvc.pattern("/js/**"), mvc.pattern("/css/**")).permitAll()
                .requestMatchers(mvc.pattern("/organizations")).permitAll() //hasAuthority("organization")
                                // ,antMatcher("/students")
                                // ,antMatcher("/WEB-INF/views/**") // not needed as allowing redirects as stated above!
                                .anyRequest().authenticated())
            .formLogin(login -> 
                login.loginPage("/login").defaultSuccessUrl("/home", true).permitAll());
            // .formLogin(Customizer.withDefaults());
            // .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
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