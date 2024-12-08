package org.example.demo1.entities;

import jakarta.persistence.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;

import java.util.List;
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GroupeVoyageur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long id_groupe;
    private String nom_groupe;
    private int nb_total_membre;
    @ManyToMany
    @JoinColumn(name = "id_client", nullable = false)
    private List<GroupeVoyageur> groupes;
    @ManyToMany
    @JoinColumn(name = "id_agent", nullable = false)
    private List<Agent> agents;
    public SimpleLongProperty idGroupeProperty() {
        return new SimpleLongProperty(id_groupe);
    }
    public SimpleStringProperty nomProperty() {
        return new SimpleStringProperty(nom_groupe);
    }

    public SimpleIntegerProperty nbTotalProperty() {
        return new SimpleIntegerProperty(nb_total_membre);
    }

}
