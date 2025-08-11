package com.vbcodes.schooltransport.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vbcodes.schooltransport.configuration.ProjectUserDetailsManager;

import java.io.IOException;

@Component
/**
 * CustomJWTFilter is a filter that processes JWT tokens in incoming HTTP requests.
 * It extracts the token from the request header, validates it, and sets the authentication
 * in the security context if the token is valid.
 */
public class CustomJWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private ProjectUserDetailsManager projectUserDetailsManager;

    private static final Logger logger = LoggerFactory.getLogger(CustomJWTFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.debug("CustomJWTFilter called for URI: {}", request.getRequestURI());
        String requestMethod = request.getMethod();
        if(requestMethod.equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(request, response);
        }
        try {
            String jwt = jwtUtils.getJWTFromHeader(request);

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUsernameFromJWT(jwt);
                UserDetails userDetails = projectUserDetailsManager.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());

                logger.debug("Roles from JWT: {}", userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }
}