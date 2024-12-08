package org.example.demo1.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CompannieAetienne")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CompannieAerienne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_CompannieArienne ;
    private String nom_compagnnies;
    private String pays;
    private String site_web;


}
