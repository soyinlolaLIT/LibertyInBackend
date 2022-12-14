package com.example.libertyinbackend.appuser.userprofile.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TeamRepository extends JpaRepository<Team,Long> {
    Optional<Team> findTeamByNameIgnoreCase(String team);

    List<Team> findAll();
}
