package com.example.tpjaxrs.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement; // Correct JAXB import for Java 17+
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double sold;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Enumerated(EnumType.ORDINAL)
    private TypeCompte typeCompte;

}
