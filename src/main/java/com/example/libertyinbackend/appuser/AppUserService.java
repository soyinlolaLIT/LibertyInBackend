package com.example.libertyinbackend.appuser;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.example.libertyinbackend.appuser.AppUserRole.ADMIN;
import static com.example.libertyinbackend.appuser.AppUserRole.USER;

@Service
@AllArgsConstructor
@Slf4j
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    public String signUpUser(AppUser appUser){
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail())
                .isPresent();

        if(userExists){
            throw new IllegalStateException("Email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        //TODO: Send confirmation token
        return "It works";
    }

    public List<AppUser> getAllUsers() {
        boolean hasUsers = appUserRepository.findAll().isEmpty();
        if(!hasUsers == false){
            throw new IllegalStateException("No users found");
        }
        return appUserRepository.findAll();
    }
}
