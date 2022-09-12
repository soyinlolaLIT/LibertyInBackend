package com.example.libertyinbackend.appuser;

import com.example.libertyinbackend.appuser.userprofile.UserProfileService;
import com.example.libertyinbackend.appuser.userprofile.misc.certifications.CertificationService;
import com.example.libertyinbackend.appuser.userprofile.misc.skills.SkillService;
import com.example.libertyinbackend.appuser.userprofile.team.TeamService;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.example.libertyinbackend.appuser.AppUserRole.USER;

@Configuration
public class AppUserConfig {

    private final AppUserService appUserService;
    private final UserProfileService userProfileService;
    private final SkillService skillService;
    private final CertificationService certificationService;

    private final TeamService teamService;

    public AppUserConfig(AppUserService appUserService, UserProfileService userProfileService, SkillService skillService, CertificationService certificationService, TeamService teamService){
        this.appUserService = appUserService;
        this.userProfileService = userProfileService;
        this.skillService = skillService;
        this.certificationService = certificationService;
        this.teamService = teamService;
    }


    public void buildUser(){
        List<AppUser> users = List.of(
                new AppUser("David","Faulkner","Samuel.Faulkner@liberty-it.co.uk","DFaulkner10",USER),
                new AppUser("Steven","Oyinlola","Steven.Oyinlola@liberty-it.co.uk","SOyinlola10",USER),
                new AppUser("Gerard","Gray","Gerard.Gray@liberty-it.co.uk","GGray10",USER),
                new AppUser("Joe","Bell","Joe.Bell@liberty-it.co.uk","JBell10",USER)
        );
        //create skills
        skillService.addSkill("Java", "");
        skillService.addSkill("Spring", "");
        skillService.addSkill("TypeScript", "");
        skillService.addSkill("Angular", "");
        skillService.addSkill("JavaScript", "");

        //create Certifications
        certificationService.addCertification("AWS Lambda", "");
        certificationService.addCertification("AWS Serverless", "");
        certificationService.addCertification("COMPTIA+", "");
        certificationService.addCertification("Azure", "");
        certificationService.addCertification("GCP", "");


        //create Team
        teamService.addTeam("Mutant Muffins");


        //signing up the users here
        users.stream()
                .forEach(user -> appUserService.signUpUser(user));
        //giving everyone the job title
        users.stream()
                .forEach(user -> userProfileService.setJobTitle(user.getUserProfile(),"Associate Software Engineer"));

        //give everyone skills
//        users.stream()
//                .forEach(user -> userProfileService.addSkills(user.getUserProfile(), skillService.loadSkillByName("Java")));
//        users.stream()
//                .forEach(user -> userProfileService.addSkills(user.getUserProfile(), skillService.loadSkillByName("Spring")));
//        users.stream()
//                .forEach(user -> userProfileService.addSkills(user.getUserProfile(), skillService.loadSkillByName("TypeScript")));
//        users.stream()
//                .forEach(user -> userProfileService.addSkills(user.getUserProfile(), skillService.loadSkillByName("Angular")));
//        users.stream()
//                .forEach(user -> userProfileService.addSkills(user.getUserProfile(), skillService.loadSkillByName("JavaScript")));
//
//        //give everyone certifications
//        users.stream()
//                .forEach(user -> userProfileService.addCertifications(user.getUserProfile(), certificationService.loadCertificationByName("AWS Lambda")));
//        users.stream()
//                .forEach(user -> userProfileService.addCertifications(user.getUserProfile(), certificationService.loadCertificationByName("AWS Serverless")));
//        users.stream()
//                .forEach(user -> userProfileService.addCertifications(user.getUserProfile(), certificationService.loadCertificationByName("COMPTIA+")));
//        users.stream()
//                .forEach(user -> userProfileService.addCertifications(user.getUserProfile(), certificationService.loadCertificationByName("Azure")));
//        users.stream()
//                .forEach(user -> userProfileService.addCertifications(user.getUserProfile(), certificationService.loadCertificationByName("GCP")));

//        //Add everyone to the team
//        users.stream()
//                .forEach(user -> user.getUserProfile().setTeam(teamService.loadTeamByName("Mutant Muffins").getName()));
//        users.stream()
//                .forEach(user -> teamService.loadTeamByName("Mutant Muffins").addMember(user.getUserProfile()));
//
//        //save the team and the profiles
//        teamService.saveChange(teamService.loadTeamByName("Mutant Muffins"));
//        users.stream()
//                .forEach(user -> userProfileService.saveChange(user.getUserProfile()));



    }


}
