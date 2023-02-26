package com.blogapplication.controller;

import com.blogapplication.payload.JWTAuthResponse;
import com.blogapplication.payload.LoginDTO;
import com.blogapplication.payload.RegisterDTO;
import com.blogapplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class Authcontroller {
    @Autowired
    private AuthService authService;

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JWTAuthResponse> getLoggedIn(@RequestBody  LoginDTO loginDTO)
    {
        String token= authService.login(loginDTO);
        JWTAuthResponse jwtAuthResponse=new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping(value = {"/signup","/register"})
    public ResponseEntity<String>getSignUp(@RequestBody  RegisterDTO registerDTO)
    {
        String response= authService.register(registerDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
