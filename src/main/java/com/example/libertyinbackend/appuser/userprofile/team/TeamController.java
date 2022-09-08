package com.example.libertyinbackend.appuser.userprofile.team;

import com.example.libertyinbackend.appuser.dto.Input;
import com.example.libertyinbackend.appuser.dto.TeamRequest;
import com.example.libertyinbackend.appuser.userprofile.UserProfile;
import com.example.libertyinbackend.appuser.userprofile.UserProfileService;
import com.sun.mail.iap.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team")
@RequiredArgsConstructor
@Slf4j
public class TeamController {

    @Autowired
    private final TeamService teamService;

    @Autowired
    private final UserProfileService userProfileService;

    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllTeams(){
        return ResponseEntity.ok().body(teamService.getAll());
    }

    // make so that I can create a team with just a string
    @PostMapping("/create")
    public void createTeam(@RequestBody Input name){
        teamService.addTeam(name.getInput());
    }

    //Currently not working
    @PutMapping("/add")
    public void addToTeam(@RequestBody TeamRequest request){
        //get profile and team
        UserProfile user = userProfileService.loadUserByUsername(request.getUserEmail());
        Team team = teamService.loadTeamByName(request.getTeam());

        //add to the team
        user.setTeam(team);
        team.addMember(user);

        //save in both repositories
        userProfileService.saveChange(user);
        teamService.saveChange(team);
    }
}
