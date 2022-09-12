package com.example.libertyinbackend.appuser.userprofile.misc.certifications;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Certification {
    @Id
    @SequenceGenerator(
            name = "certification_sequence",
            sequenceName = "certification_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "certification_sequence"
    )
    private Long id;
    private String name;
    private String description;

    public Certification(String name){
        this.name = name;
    }
    public Certification(String name,String description){
        this.name = name;
        this.description = description;
    }

}
