package com.example.libertyinbackend.appuser.userprofile.misc.certifications;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CertificationRepository extends JpaRepository<Certification,Long> {
    Optional<Certification> findCertificationByNameIgnoreCase(String name);

}
