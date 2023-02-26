package com.blogapplication.Security;

import com.blogapplication.entity.Users;
import com.blogapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameorEmail) throws UsernameNotFoundException {
        System.out.println("This is email:"+usernameorEmail);
       Users users= userRepository.findByUsernameOrEmail(usernameorEmail,usernameorEmail).orElseThrow(()->
                new UsernameNotFoundException("User Not found with username or email"+usernameorEmail));

       System.out.println(users.getEmail()+" "+users.getPassword());

       Set<GrantedAuthority> authorities=
               users
                       .getRoles()
                       .stream()
                       .map((roles)->new SimpleGrantedAuthority(roles.getName())).collect(Collectors.toSet());

        return new User(users.getEmail(),users.getPassword(),authorities);
    }
}
