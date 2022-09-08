package com.example.libertyinbackend.appuser.userprofile.team;

import com.example.libertyinbackend.appuser.userprofile.UserProfile;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TeamService {

    @Autowired
    private final TeamRepository teamRepository;

    private final static String error_msg = "Team %s doesn't exist";

    public Team loadTeamByName(String team)
            throws UsernameNotFoundException {
        return teamRepository.findTeamByNameIgnoreCase(team)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(error_msg,team)));
    }

    public String addTeam(String name){
        //first check if team exists
        Boolean exists = teamRepository.findTeamByNameIgnoreCase(name).isPresent();
        if(exists){
            throw new IllegalArgumentException("Team already exists");
        }else{
            Team newTeam = new Team(name);
            teamRepository.save(newTeam);
        }
        return String.format("Team '%s' successfully added to repository!",name);
    }

    public List<Team> getAll(){
        Boolean empty = teamRepository.findAll().isEmpty();
        if(empty){
            throw new IllegalStateException("No teams to display");
        }
        return teamRepository.findAll();
    }

    public void saveChange(Team team) {
        teamRepository.save(team);
    }
}
