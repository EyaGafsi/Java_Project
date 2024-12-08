package org.example.demo1.entities;

import jakarta.persistence.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;
import org.example.demo1.enums.TypeTransport;
import org.example.demo1.enums.EtatTransport;

import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "MoyendeTransport")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MoyenDeTransport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private Long id_moyen_transport ;
    private TypeTransport typeTransport;
    private int capacite;
    private String nom_compannies;
    private LocalDate date_disponible;
    private EtatTransport etatTransport;
    private double prix_par_personne;
    @ManyToMany
    @JoinColumn(name = "id_voyage", nullable = false)
    private List<Voyage> voyages;
    @ManyToOne
    @JoinColumn(name = "id_destination", nullable = false)
    private Destination destionation;
    public SimpleLongProperty idtransportProperty() {
        return new SimpleLongProperty(id_moyen_transport);
    }
    public SimpleStringProperty typeProperty() {
        return new SimpleStringProperty(typeTransport.toString());
    }

    public SimpleIntegerProperty capaciteProperty() {
        return new SimpleIntegerProperty(capacite);
    }

    public SimpleStringProperty nomProperty() {
        return new SimpleStringProperty(nom_compannies);
    }

    public SimpleStringProperty dateProperty() {
        return new SimpleStringProperty(date_disponible.toString());
    }

    public SimpleStringProperty etatProperty() {
        return new SimpleStringProperty(etatTransport.toString());
    }

    public SimpleDoubleProperty prixProperty() {
        return new SimpleDoubleProperty(prix_par_personne);
    }

}
