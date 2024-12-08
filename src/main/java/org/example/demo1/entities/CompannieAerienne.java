package org.example.demo1.entities;

import jakarta.persistence.*;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
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

    public SimpleLongProperty idCompannieArienneProperty() {
        return new SimpleLongProperty(id_CompannieArienne);
    }
    public SimpleStringProperty nomProperty() {
        return new SimpleStringProperty(nom_compagnnies);
    }

    public SimpleStringProperty paysProperty() {
        return new SimpleStringProperty(pays);
    }

    public SimpleStringProperty siteWebProperty() {
        return new SimpleStringProperty(site_web);
    }

}
