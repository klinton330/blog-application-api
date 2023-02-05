package com.blogapplication.service;

import com.blogapplication.payload.LoginDTO;
import com.blogapplication.payload.RegisterDTO;

public interface AuthService {

    String login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);
}
