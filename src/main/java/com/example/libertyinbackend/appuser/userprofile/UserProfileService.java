package com.example.libertyinbackend.appuser.userprofile;

import com.example.libertyinbackend.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserProfileService {
    //service layer for interaction with UserProfile repository

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final UserProfileRepository userProfileRepository;

    //Grab a user profile
    public UserProfile loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userProfileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,email)));
    }

    // profile pic, job title and add skills/certifications

    public String setProfilePic(UserProfile userProfile, String ref){
        //firstly does user exist?

        boolean userExists = userProfileRepository.findByEmail(userProfile.getEmail())
                .isPresent();

        if(!userExists){
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,userProfile.getUsername()));
        }else{
            userProfile.setProfilePic(ref);
            userProfileRepository.save(userProfile);
        }

        return "works";
    }

    public String setJobTitle(UserProfile userProfile, String title){
        //firstly does user exist?

        boolean userExists = userProfileRepository.findByEmail(userProfile.getEmail())
                .isPresent();

        if(!userExists){
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,userProfile.getUsername()));
        }else{
            userProfile.setJobTitle(title);
            userProfileRepository.save(userProfile);
        }

        return "works";
    }

    public String addSkills(UserProfile userProfile, String skill){
        //firstly does user exist?

        boolean userExists = userProfileRepository.findByEmail(userProfile.getEmail())
                .isPresent();

        if(!userExists){
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,userProfile.getUsername()));
        }else{
            userProfile.addSkill(StringUtils.capitalize(skill.toLowerCase()));
            userProfileRepository.save(userProfile);
        }

        return "works";
    }

    public String addCertifications(UserProfile userProfile, String cert){
        boolean userExists = userProfileRepository.findByEmail(userProfile.getEmail())
                .isPresent();

        if(!userExists){
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,userProfile.getUsername()));
        }else{
            userProfile.addCertifications(StringUtils.capitalize(cert.toLowerCase()));
            userProfileRepository.save(userProfile);
        }

        return "works";
    }

    public String removeSkills(UserProfile userProfile, String skill){
        //firstly does user exist?

        boolean userExists = userProfileRepository.findByEmail(userProfile.getEmail())
                .isPresent();

        if(!userExists){
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,userProfile.getUsername()));
        }else{
            userProfile.removeSkill(StringUtils.capitalize(skill.toLowerCase()));
            userProfileRepository.save(userProfile);
        }

        return "works";
    }

    public String removeCertifications(UserProfile userProfile, String cert){
        boolean userExists = userProfileRepository.findByEmail(userProfile.getEmail())
                .isPresent();

        if(!userExists){
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG,userProfile.getUsername()));
        }else{
            userProfile.removeCertifications(StringUtils.capitalize(cert.toLowerCase()));
            userProfileRepository.save(userProfile);
        }

        return "works";
    }

    public List<UserProfile> findAllByEmail(String email){
        return userProfileRepository.findAllByEmailContainsIgnoreCase(email);
    }
    public List<UserProfile> findAllByJobTitle(String title){
        return userProfileRepository.findAllByJobTitleContainsIgnoreCase(title);
    }
    public List<UserProfile> findAllByCertification(String cert){
        return userProfileRepository.findAllByCertificationsContainsIgnoreCase(cert);
    }
    public List<UserProfile> findAllBySkill(String skill){
        return userProfileRepository.findAllBySkillsContainsIgnoreCase(skill);
    }


    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }
}