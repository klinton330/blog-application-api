package com.blogapplication.controller;

import com.blogapplication.payload.LoginDTO;
import com.blogapplication.payload.RegisterDTO;
import com.blogapplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class Authcontroller {
    @Autowired
    private AuthService authService;

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<String> getLoggedIn(@RequestBody  LoginDTO loginDTO)
    {
        String response= authService.login(loginDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = {"/signup","/register"})
    public ResponseEntity<String>getSignUp(@RequestBody  RegisterDTO registerDTO)
    {
        String response= authService.register(registerDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
