package com.example.libertyinbackend.appuser.userprofile.misc.skills;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface SkillRepository extends JpaRepository<Skill,Long> {
    Optional<Skill> findSkillByNameIgnoreCase(String name);
}
