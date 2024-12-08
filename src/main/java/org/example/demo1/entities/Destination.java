package org.example.demo1.entities;

import jakarta.persistence.*;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@Table(name = "destination")
@AllArgsConstructor
@NoArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_destination ;
    private String pays;
    private String ville;
    private String description;
    public SimpleLongProperty idDestinationProperty() {
        return new SimpleLongProperty(id_destination);
    }
    public SimpleStringProperty paysProperty() {
        return new SimpleStringProperty(pays);
    }

    public SimpleStringProperty villeProperty() {
        return new SimpleStringProperty(ville);
    }

    public SimpleStringProperty descriptionProperty() {
        return new SimpleStringProperty(description);
    }

}
