package com.blogapplication.service.serviceImpl;

import com.blogapplication.entity.Role;
import com.blogapplication.entity.Users;
import com.blogapplication.exception.BlogAPIException;
import com.blogapplication.payload.LoginDTO;
import com.blogapplication.payload.RegisterDTO;
import com.blogapplication.repository.RoleRepository;
import com.blogapplication.repository.UserRepository;
import com.blogapplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public String login(LoginDTO loginDTO) {
       Authentication authentication= authenticationManager.authenticate
               (new UsernamePasswordAuthenticationToken(loginDTO.getUserNameOrEmail(),loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Logged In Successfully";
    }

    @Override
    public String register(RegisterDTO registerDTO) {
        //Check username already in DB
        if(userRepository.existsByUsername(registerDTO.getUsername()))
        {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Username already exixt");
        }
        Users users=new Users();
        users.setName(registerDTO.getName());
        users.setUsername(registerDTO.getUsername());
        users.setEmail(registerDTO.getEmail());
        users.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role>roles=new HashSet<>();
        Role userRoles=roleRepository.findByName(registerDTO.getRole()).get();
        roles.add(userRoles);
        users.setRoles(roles);

        userRepository.save(users);

        return "user Register Successfully";
    }
}
