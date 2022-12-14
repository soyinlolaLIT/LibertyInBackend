package com.example.libertyinbackend.appuser;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.libertyinbackend.appuser.dto.DataInput;
import com.example.libertyinbackend.appuser.dto.Input;
import com.example.libertyinbackend.appuser.userprofile.UserProfile;
import com.example.libertyinbackend.appuser.userprofile.UserProfileService;
import com.example.libertyinbackend.appuser.userprofile.misc.certifications.Certification;
import com.example.libertyinbackend.appuser.userprofile.misc.certifications.CertificationService;
import com.example.libertyinbackend.appuser.userprofile.misc.skills.Skill;
import com.example.libertyinbackend.appuser.userprofile.misc.skills.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Role;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class AppUserController {

    @Autowired
    private final AppUserService appUserService;
    @Autowired
    private final UserProfileService userProfileService;

    @Autowired
    private final SkillService skillService;

    @Autowired
    private final CertificationService certificationService;

    //TODO: import skillService and certificationService

    //discover related endpoints
    @GetMapping("/users")
    public ResponseEntity<List<UserProfile>> getUsers(){
        return ResponseEntity.ok().body(userProfileService.findAll());
    }

    @PutMapping("/users/email")
    public ResponseEntity<List<UserProfile>> getUsersByEmail(@RequestBody Input input){
        return ResponseEntity.ok().body(userProfileService.findAllByEmail(input.getInput()));
    }

    @PutMapping("/users/retrieve")
    public ResponseEntity<UserProfile> getUserByEmail(@RequestBody Input input){
        return ResponseEntity.ok().body(userProfileService.loadUserByUsername(input.getInput()));
    }

    @PutMapping("/users/title")
    public ResponseEntity<List<UserProfile>> getUsersByTitle(@RequestBody Input input){
        return ResponseEntity.ok().body(userProfileService.findAllByJobTitle(input.getInput()));
    }

    @PutMapping("/users/skill")
    public ResponseEntity<List<UserProfile>> getUsersBySkill(@RequestBody Input input){
        return ResponseEntity.ok().body(userProfileService.findAllBySkill(skillService.loadSkillByName(input.getInput())));
    }

    @PutMapping("/users/certification")
    public ResponseEntity<List<UserProfile>> getUsersByCert(@RequestBody Input input){
        return ResponseEntity.ok().body(userProfileService.findAllByCertification(certificationService.loadCertificationByName(input.getInput())));
    }

    //all account related endpoints
    @GetMapping("/account")
    public ResponseEntity<AppUser> getAccount(Authentication authentication){
        return ResponseEntity.ok().body((AppUser)appUserService.loadUserByUsername(authentication.getName()));
    }

    @PutMapping("/account/profile_picture")
    public void profilePicture(Authentication authentication, @RequestBody Input input){
        AppUser appUser = (AppUser)appUserService.loadUserByUsername(authentication.getName());
        UserProfile userProfile = userProfileService.loadUserByUsername(appUser.getUsername());
        userProfileService.setProfilePic(userProfile, input.getInput());
    }

    @PutMapping("/account/job_title")
    public void jobTitle(Authentication authentication, @RequestBody Input input){
        AppUser appUser = (AppUser)appUserService.loadUserByUsername(authentication.getName());
        UserProfile userProfile = userProfileService.loadUserByUsername(appUser.getUsername());
        userProfileService.setJobTitle(userProfile,input.getInput());
    }

    @PutMapping("/account/add_skill")
    public void addSkill(Authentication authentication, @RequestBody DataInput input){
        AppUser appUser = (AppUser)appUserService.loadUserByUsername(authentication.getName());
        UserProfile userProfile = userProfileService.loadUserByUsername(appUser.getUsername());
        Skill skill = skillService.skillExists(input.getName()) ? skillService.loadSkillByName(input.getName())
                : new Skill(input.getName(),input.getDescription());
        if(!skillService.skillExists(input.getName())){
            skillService.save(skill);
        }
        userProfileService.addSkills(userProfile,skill);
    }

    @PutMapping("/account/add_certification")
    public void addCertification(Authentication authentication, @RequestBody DataInput input){
        AppUser appUser = (AppUser)appUserService.loadUserByUsername(authentication.getName());
        UserProfile userProfile = userProfileService.loadUserByUsername(appUser.getUsername());
        Certification cert = certificationService.certificationExists(input.getName()) ? certificationService.loadCertificationByName(input.getName())
                : new Certification(input.getName(),input.getDescription());
        if(!certificationService.certificationExists(input.getName())){
            certificationService.save(cert);
        }

        userProfileService.addCertifications(userProfile,cert);
    }

    @DeleteMapping("/account/remove_skill")
    public void removeSkill(Authentication authentication, @RequestBody Input input){
        AppUser appUser = (AppUser)appUserService.loadUserByUsername(authentication.getName());
        UserProfile userProfile = userProfileService.loadUserByUsername(appUser.getUsername());
        if(skillService.skillExists(input.getInput())){
            userProfileService.removeSkills(userProfile,skillService.loadSkillByName(input.getInput()));
        }
    }

    @DeleteMapping("/account/remove_certification")
    public void removeCertification(Authentication authentication, @RequestBody Input input){
        AppUser appUser = (AppUser)appUserService.loadUserByUsername(authentication.getName());
        UserProfile userProfile = userProfileService.loadUserByUsername(appUser.getUsername());
        if(certificationService.certificationExists(input.getInput())){
            userProfileService.removeCertifications(userProfile,certificationService.loadCertificationByName(input.getInput()));
        }
    }



    //refactoring to be done here, lots of redundant code
    @PostMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                AppUser user = (AppUser)appUserService.loadUserByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("appUserRole", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);
                log.info(Arrays.toString(user.getAuthorities().toArray()));
                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);
            }catch (Exception exception){
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_msg",exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }

        }else{
                throw new RuntimeException("Refresh token is missing");
        }
    }
}
