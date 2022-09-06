package com.example.libertyinbackend.security.config;

import com.example.libertyinbackend.appuser.AppUserRole;
import com.example.libertyinbackend.appuser.AppUserService;
import com.example.libertyinbackend.filter.CustomAuthenticationFilter;
import com.example.libertyinbackend.filter.CustomAuthorizationFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@Slf4j
public class WebSecurityConfig {

    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf().disable().cors();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.cors().and().authorizeRequests().antMatchers("/api/v*/registration/**","/api/v*/token/refresh","/login").permitAll();
        http.cors().and().authorizeRequests().antMatchers(GET, "/api/v*/users**","/api/v*/account**").hasAnyAuthority(AppUserRole.USER.name(),AppUserRole.ADMIN.name());
        http.cors().and().authorizeRequests().antMatchers(POST, "/api/v*/account**","/api/v*/users/**").hasAnyAuthority(AppUserRole.USER.name(),AppUserRole.ADMIN.name());
        http.cors().and().authorizeRequests().antMatchers(PUT, "/api/v*/account**","/api/v*/users/**").hasAnyAuthority(AppUserRole.USER.name(),AppUserRole.ADMIN.name());
        http.cors().and().authorizeRequests().antMatchers(DELETE, "/api/v*/account**","/api/v*/users/**").hasAnyAuthority(AppUserRole.USER.name(),AppUserRole.ADMIN.name());
        http.cors().and().authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(daoAuthenticationProvider()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "HEAD", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));
        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception{
        return auth.getAuthenticationManager();
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
