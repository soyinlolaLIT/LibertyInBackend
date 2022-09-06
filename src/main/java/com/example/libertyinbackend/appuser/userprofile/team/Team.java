package com.example.libertyinbackend.appuser.userprofile.team;

import com.example.libertyinbackend.appuser.AppUser;
import com.example.libertyinbackend.appuser.userprofile.UserProfile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Team {
    //has a name and a list of user profiles
    @Id
    @SequenceGenerator(
            name = "team_sequence",
            sequenceName = "team_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "team_sequence"
    )
    private Long id;
    private String name;

    @OneToMany(mappedBy = "UserProfile", cascade = CascadeType.ALL)
    @JoinColumn(name = "members", referencedColumnName = "id")
    private Collection<UserProfile> members = new ArrayList<>();

    public void addMember(UserProfile appUser){
        members.add(appUser);
    }
}
