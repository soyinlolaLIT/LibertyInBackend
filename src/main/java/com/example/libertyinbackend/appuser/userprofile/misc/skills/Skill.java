package com.example.libertyinbackend.appuser.userprofile.misc.skills;

import com.example.libertyinbackend.appuser.userprofile.UserProfile;
import lombok.*;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Skill {
    @Id
    @SequenceGenerator(
            name = "skill_sequence",
            sequenceName = "skill_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "skill_sequence"
    )
    private Long id;
    private String name;
    private String description;


    public Skill(String name){
        this.name = name;
    }

    public Skill(String name, String description){
        this.name = name;
        this.description = description;
    }

}
