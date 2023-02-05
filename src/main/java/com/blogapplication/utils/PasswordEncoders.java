package com.blogapplication.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoders {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

        ;
        System.out.println(passwordEncoder.encode("klinton"));
        System.out.println(passwordEncoder.encode("ramesh"));

    }
}
