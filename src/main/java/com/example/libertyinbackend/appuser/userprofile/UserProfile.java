package com.example.libertyinbackend.appuser.userprofile;

import com.example.libertyinbackend.appuser.userprofile.team.Team;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserProfile {
    // In each profile we have:
    //1. profile picture
    //2. job title
    //3. team name
    //4. skills (plucked from trendyskills api)
    //5. certifications
    //6. team members

    private String profilePic;
    private String title;
    private Team teamName;
    private ArrayList<String> skills;
    private ArrayList<String> certifications;




}
