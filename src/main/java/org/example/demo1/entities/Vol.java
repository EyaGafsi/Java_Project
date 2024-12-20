package org.example.demo1.entities;

import jakarta.persistence.*;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_vol;
    private String numero_vol;
    private String aeroport_depart;
    private LocalDate date_depart;
    private String aeroport_arrivee;
    private LocalDate date_arrivee;
    private float prix_par_personne;
    private int place_disponible;
    @ManyToOne
    @JoinColumn(name = "id_CompannieArienne", nullable = false)
    private CompannieAerienne compannieAerienne;
    @ManyToOne
    @JoinColumn(name = "id_voyage", nullable = false)
    private Voyage voyage;


}
