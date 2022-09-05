package com.example.libertyinbackend.appuser.userprofile;

import com.example.libertyinbackend.appuser.AppUser;
import com.example.libertyinbackend.appuser.AppUserRole;
import com.example.libertyinbackend.appuser.userprofile.team.Team;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserProfile {
    // In each profile we have:
    //1. profile picture
    //2. job title
    //3. team name
    //4. skills (plucked from trendyskills api)
    //5. certifications
    //6. team members

    @Id
    @Column(name = "id")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "team_id")
    private Team teamName;

    @ElementCollection
    @CollectionTable(name = "user_profile_skills", joinColumns = @JoinColumn(name = "user_skills"))
    private Collection<String> skills = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "user_profile_certifications", joinColumns = @JoinColumn(name = "user_certifications"))
    private Collection<String> certifications = new ArrayList<>();
    private String profilePic;
    private String jobTitle;


    //constructor with only details needed for User creation
    public UserProfile(AppUser appUser){
        this.id = appUser.getId();
        this.firstName = appUser.getFirstName();
        this.lastName = appUser.getLastName();
        this.email = appUser.getEmail();
        this.password = appUser.getPassword();
    }

    public void addSkill(String skill){
        skills.add(skill);
    }

    public void addCertifications(String cert){
        certifications.add(cert);
    }


}
