package org.example.demo1.entities;

import com.mysql.cj.conf.StringProperty;
import jakarta.persistence.*;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;

import java.util.List;
@Table(name = "client")
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation
    private long Id_Client;
    private String Nom;
    private String Prenom;
    private String Email;
    private String Telephone;
    private String Adresse;
    @ManyToMany(fetch = FetchType.EAGER)
   @JoinColumn(name = "id_groupe")
    private List<GroupeVoyageur> groupeVoyageurs;
    public SimpleLongProperty idClientProperty() {
        return new SimpleLongProperty(Id_Client);
    }
    public SimpleStringProperty nomProperty() {
        return new SimpleStringProperty(Nom);
    }

    public SimpleStringProperty prenomProperty() {
        return new SimpleStringProperty(Prenom);
    }

    public SimpleStringProperty emailProperty() {
        return new SimpleStringProperty(Email);
    }

    public SimpleStringProperty telephoneProperty() {
        return new SimpleStringProperty(Telephone);
    }

    public SimpleStringProperty adresseProperty() {
        return new SimpleStringProperty(Adresse);
    }

}
