package com.example.libertyinbackend.appuser.dto;

import com.example.libertyinbackend.appuser.userprofile.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamRequest {
     String team;
     String userEmail;
}
