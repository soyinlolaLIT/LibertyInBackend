package com.example.libertyinbackend.registration;

import com.example.libertyinbackend.appuser.AppUser;
import com.example.libertyinbackend.appuser.AppUserRole;
import com.example.libertyinbackend.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private EmailValidator emailValidator;
    public String register(RegistrationRequest request){
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
    // change new AppUser to take in UserProfile
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                )
        );
    }
}
