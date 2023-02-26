package com.blogapplication.config;

import com.blogapplication.Security.JWTAuthenticationFilter;
import com.blogapplication.Security.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecuityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception
    {
        httpSecurity.csrf().disable()
               // .authorizeHttpRequests((authorize)->authorize.anyRequest().authenticated())
                .authorizeHttpRequests((authorize)->authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                        .exceptionHandling(exception->exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    //In-Memory Authentication



   /* public UserDetailsService userDetails()
    {
        UserDetails hari= User.builder()
                .username("hari")
                .password(passwordEncoder().encode("haridharan"))
                .roles("USER").build();

        UserDetails admin=User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(hari,admin);

    }*/

}
