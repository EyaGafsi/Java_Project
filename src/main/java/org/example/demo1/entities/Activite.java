package org.example.demo1.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name="activite")
@AllArgsConstructor
@NoArgsConstructor
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private Long id_activite;
    private String nom_activite;
    private double prix_activite;
    private String description;
    @ManyToMany
    @JoinColumn(name = "id_voyage") // Clé étrangère vers voyage
    private List<Voyage> voyages;
}
