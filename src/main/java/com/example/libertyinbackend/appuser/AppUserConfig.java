package com.example.libertyinbackend.appuser;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static com.example.libertyinbackend.appuser.AppUserRole.ADMIN;
import static com.example.libertyinbackend.appuser.AppUserRole.USER;

@Configuration
public class AppUserConfig {

    private final AppUserService appUserService;

    public AppUserConfig(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    public void buildUser(){
        List<AppUser> users = List.of(
                new AppUser("Bilal","Bilal","Bilal@gmail.com","Bilal",USER),
                new AppUser("Steven","Oyin","soyi@gmail.com","qwert2",ADMIN)
        );

        users.stream()
                .forEach(user -> appUserService.signUpUser(user));
    }


}
