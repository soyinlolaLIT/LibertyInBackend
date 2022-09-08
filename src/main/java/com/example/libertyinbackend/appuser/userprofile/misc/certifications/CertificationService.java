package com.example.libertyinbackend.appuser.userprofile.misc.certifications;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CertificationService {
    private final CertificationRepository certificationRepository;

    private final static String error_msg = "Team %s doesn't exist";


    //Find Certification by name
    public Certification loadCertificationByName(String name)throws UsernameNotFoundException {
        return certificationRepository.findCertificationByNameIgnoreCase(name)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(error_msg,name)));
    }

    //Add a certification
    public String addCertification(String name, String description){
        //first check if exists
        boolean exists = certificationRepository.findCertificationByNameIgnoreCase(name).isPresent();
        if(exists){
            throw new IllegalArgumentException(String.format("The Certification %s already exists",name));
        }
        else{
            Certification cert = new Certification(name,description);
            certificationRepository.save(cert);
        }
        return String.format("New certification, %s , created!",name);
    }

    //Remove a certification
    public String removeCertification(String name){
        //first check if exists
        boolean exists = certificationRepository.findCertificationByNameIgnoreCase(name).isPresent();
        if(exists){
            Certification cert = loadCertificationByName(name);
            certificationRepository.delete(cert);
        }
        else{
            throw new IllegalArgumentException(String.format("The Certification %s doesn't exist",name));
        }
        return String.format("Certification, %s , has been removed!",name);
    }

    public boolean certificationExists(String name) {
        return certificationRepository.findCertificationByNameIgnoreCase(name).isPresent();
    }

    public void save(Certification cert) {
        certificationRepository.save(cert);
    }
}
