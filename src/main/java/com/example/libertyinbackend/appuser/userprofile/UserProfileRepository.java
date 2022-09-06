package com.example.libertyinbackend.appuser.userprofile;


import com.example.libertyinbackend.appuser.AppUser;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByEmail(String email);

    //Find all profiles
    List<UserProfile> findAll();

    //Finding profiles by job_title
    List<UserProfile> findAllByJobTitleContainsIgnoreCase(String title);
    // email,
    List<UserProfile> findAllByEmailContainsIgnoreCase(String email);
    // skills,
    List<UserProfile> findAllBySkillsContainsIgnoreCase(String skill);
    // certifications
    List<UserProfile> findAllByCertificationsContainsIgnoreCase(String title);

}
