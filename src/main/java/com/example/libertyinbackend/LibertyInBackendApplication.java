package com.example.libertyinbackend;

import com.example.libertyinbackend.appuser.AppUser;
import com.example.libertyinbackend.appuser.AppUserConfig;
import com.example.libertyinbackend.appuser.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import static com.example.libertyinbackend.appuser.AppUserRole.ADMIN;
import static com.example.libertyinbackend.appuser.AppUserRole.USER;


@SpringBootApplication
public class LibertyInBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibertyInBackendApplication.class, args);
    }

//    @Bean
//    CommandLineRunner run(AppUserConfig appUserConfig){
//        return args -> {
//            appUserConfig.buildUser();
//        };
//    }
}
