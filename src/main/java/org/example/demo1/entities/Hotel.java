package org.example.demo1.entities;

import jakarta.persistence.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;
import org.example.demo1.enums.Classement;

@Entity
@Setter
@Getter
@ToString
@Table(name = "hotel")
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_hotel ;
    private String nom_hotel;
    private String adresse;
    private int capacite;
    private double prix_par_nuit;
    private Classement classement;
    private String description;
    //plusieur hôtel peut etre associé à une destination spécifique
    @ManyToOne
    @JoinColumn(name = "id_destination", nullable = false)
    private Destination destionation;
    public SimpleLongProperty idHotelProperty() {
        return new SimpleLongProperty(id_hotel);
    }
    public SimpleStringProperty nomProperty() {
        return new SimpleStringProperty(nom_hotel);
    }

    public SimpleStringProperty adresseProperty() {
        return new SimpleStringProperty(adresse);
    }

    public SimpleIntegerProperty capaciteProperty() {
        return new SimpleIntegerProperty(capacite);
    }

    public SimpleDoubleProperty prixProperty() {
        return new SimpleDoubleProperty(prix_par_nuit);
    }

    public SimpleStringProperty classementProperty() {
        return new SimpleStringProperty(classement.toString());
    }

    public SimpleStringProperty descriptionProperty() {
        return new SimpleStringProperty(description);
    }
}
