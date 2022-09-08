package com.example.libertyinbackend.appuser.userprofile.misc.skills;

import com.example.libertyinbackend.appuser.userprofile.misc.certifications.Certification;
import com.example.libertyinbackend.appuser.userprofile.misc.certifications.CertificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SkillService {
    private final SkillRepository skillRepository;

    private final static String error_msg = "Skill %s doesn't exist";


    //Find Skill by name
    public Skill loadSkillByName(String name)throws UsernameNotFoundException {
        return skillRepository.findSkillByNameIgnoreCase(name)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(error_msg,name)));
    }

    //Check if Skill exists
    public Boolean skillExists(String name){
        return skillRepository.findSkillByNameIgnoreCase(name).isPresent();
    }

    //Add a Skill
    public String addSkill(String name, String description){
        //first check if exists
        if(skillExists(name)){
            throw new IllegalArgumentException(String.format("The Skill %s already exists",name));
        }
        else{
            Skill skill = new Skill(name,description);
            skillRepository.save(skill);
        }
        return String.format("New Skill, %s , created!",name);
    }

    //Remove a Skill
    public String removeSkill(String name){
        //first check if exists
        if(skillExists(name)){
            Skill skill = loadSkillByName(name);
            skillRepository.delete(skill);
        }
        else{
            throw new IllegalArgumentException(String.format("The Skill %s doesn't exist",name));
        }
        return String.format("Skill, %s , has been removed!",name);
    }

    public void save(Skill skill) {
        skillRepository.save(skill);
    }
}
