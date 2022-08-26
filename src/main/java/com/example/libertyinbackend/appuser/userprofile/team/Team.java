package com.example.libertyinbackend.appuser.userprofile.team;

import com.example.libertyinbackend.appuser.userprofile.UserProfile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Team {
    //has a name and a list of user profiles
    private String name;
    private ArrayList<UserProfile> members;
}
