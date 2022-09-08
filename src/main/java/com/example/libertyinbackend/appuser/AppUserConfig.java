package com.example.libertyinbackend.appuser;

import com.example.libertyinbackend.appuser.userprofile.UserProfileService;
import com.example.libertyinbackend.appuser.userprofile.misc.certifications.CertificationService;
import com.example.libertyinbackend.appuser.userprofile.misc.skills.Skill;
import com.example.libertyinbackend.appuser.userprofile.misc.skills.SkillService;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.example.libertyinbackend.appuser.AppUserRole.USER;

@Configuration
public class AppUserConfig {

    private final AppUserService appUserService;
    private final UserProfileService userProfileService;
    private final SkillService skillService;
    private final CertificationService certificationService;

    public AppUserConfig(AppUserService appUserService, UserProfileService userProfileService, SkillService skillService, CertificationService certificationService){
        this.appUserService = appUserService;
        this.userProfileService = userProfileService;
        this.skillService = skillService;
        this.certificationService = certificationService;
    }
    public void buildUser(){
        List<AppUser> users = List.of(
                new AppUser("Bilal","Bilal","Bilal@gmail.com","Bilal",USER),
                new AppUser("Steven","Oyin","soyi@gmail.com","qwert2",USER),
                new AppUser("Khan","Bilal","kbil@gmail.com","Khan",USER),
                new AppUser("Ahmed","Saad","asaa@gmail.com","Ahmed",USER),
                new AppUser("Mohammed","Sodiq","msod@gmail.com","Mohammed",USER),
                new AppUser("Abdul","Abasi","aaba@gmail.com","Abdul",USER)
        );

        users.stream()
                .forEach(user -> appUserService.signUpUser(user));
        users.stream()
                .forEach(user -> userProfileService.setJobTitle(user.getUserProfile(),"Associate Software Engineer"));
        userProfileService.setJobTitle(users.get(2).getUserProfile(),"Data Scientist");

    }


}
