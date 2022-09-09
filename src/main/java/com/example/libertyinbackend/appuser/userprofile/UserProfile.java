package com.example.libertyinbackend.appuser.userprofile;

import com.example.libertyinbackend.appuser.userprofile.misc.certifications.Certification;
import com.example.libertyinbackend.appuser.userprofile.misc.skills.Skill;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private String team;

    @OneToMany
    @CollectionTable(name = "user_profile_skills", joinColumns = @JoinColumn(name = "user_id"))
    private Collection<Skill> skills = new ArrayList<>();
    @OneToMany
    @CollectionTable(name = "user_profile_certifications", joinColumns = @JoinColumn(name = "user_id"))
    private Collection<Certification> certifications = new ArrayList<>();
    private String profilePic;
    private String jobTitle;


    //constructor with only details needed for User creation
    public UserProfile(String firstName,
                       String lastName,
                       String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUsername(){
        return email;
    }

    public void addSkill(Skill skill){
        if(!skills.contains(skill)){
            skills.add(skill);
        }
    }

    public void addCertifications(Certification cert){
        if(!certifications.contains(cert)){
            certifications.add(cert);
        }
    }


    public void removeSkill(Skill skill) {
        if(skills.contains(skill)){
            skills.remove(skill);
        }
    }

    public void removeCertifications(Certification cert) {
        if(certifications.contains(cert)){
            certifications.remove(cert);
        }
    }

}
