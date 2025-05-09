package com.vbcodes.schooltransport.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.vbcodes.schooltransport.dto.LoginResponseDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.vbcodes.schooltransport.dto.LoginRequestDTO;
import com.vbcodes.schooltransport.jwt.JWTUtils;
import com.vbcodes.schooltransport.service.AppUserService;

@RestController
public class LoginController {
    private AuthenticationManager authenticationManager;
    private JWTUtils jwtUtils;

    @Autowired
    LoginController(AppUserService appUserService, AuthenticationManager authenticationManager, JWTUtils jwtUtils){
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/getData")
    public String getData(){
        return "This is you api fetched data";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginDTO){
        Authentication auth;
        try{
            auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        } catch (AuthenticationException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Bad Credentials");
            errorResponse.put("status", false);
            return new ResponseEntity<Object>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

        //if login is successful, and there is populated authentication object
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String jwtToken = jwtUtils.generateJWTFromUsername(userDetails);
        List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(userDetails.getUsername(), jwtToken, "Login Successful !", roles);
        return new ResponseEntity<Object>(loginResponseDTO, HttpStatus.OK);
    }
}
