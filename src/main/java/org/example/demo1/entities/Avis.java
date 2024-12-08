package org.example.demo1.entities;

import jakarta.persistence.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "avis")
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_Avis;
    private double note;
    private String commentaire ;
    private LocalDate date_avis;
    @ManyToMany
    @JoinColumn(name = "id_client") // Clé étrangère vers Reservation
    private List<Client> clients;
    @ManyToMany
    @JoinColumn(name = "id_voyage") // Clé étrangère vers Reservation
    private List<Voyage> voyages;
    @ManyToMany
    @JoinColumn(name = "id_hotel") // Clé étrangère vers Reservation
    private List<Hotel> hotels;


    public SimpleLongProperty idAvisProperty() {
        return new SimpleLongProperty(id_Avis);
    }
    public SimpleStringProperty commentaireProperty() {
        return new SimpleStringProperty(commentaire);
    }

    public SimpleDoubleProperty noteProperty() {
        return new SimpleDoubleProperty(note);
    }

    public SimpleStringProperty dateProperty() {
        return new SimpleStringProperty(date_avis.toString());
    }

}
