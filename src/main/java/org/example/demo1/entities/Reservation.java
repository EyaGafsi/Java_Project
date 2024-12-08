package org.example.demo1.entities;

import jakarta.persistence.*;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reservation;
    private int nb_place;
    private double montant_total;
    private LocalDate date_reservation;
    @OneToOne
    @JoinColumn(name = "id_groupe", nullable = false)//pour assurer un seul groupe est lié à une seule réservation
    private GroupeVoyageur groupe;
    @ManyToOne
    @JoinColumn(name = "id_voyage", nullable = false)
    private Voyage voyage;


}