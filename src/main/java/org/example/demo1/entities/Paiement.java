package org.example.demo1.entities;

import jakarta.persistence.*;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;
import org.example.demo1.enums.ModePaiement;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="paiement")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_paiement ;
   private  double montant;
   private LocalDate date_paiement;
   private ModePaiement modePaiement;
   @ManyToMany
   @JoinColumn(name = "id_reservation") // Clé étrangère vers Reservation
   private List<Reservation> reservation;



}
