package org.example.demo1.views;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.example.demo1.entities.Client;
import org.example.demo1.services.ClientService;
import org.example.demo1.services.servicesImpl.ClientServiceImpl;

public class ClientAddView {

    private ClientService clientService;

    private TextField nameField;
    private TextField prenomField;
    private TextField emailField;
    private TextField telField;
    private TextField adresseField;

    public ClientAddView() {
        this.clientService = new ClientServiceImpl();
    }

    public GridPane showAddClientForm() {
        // Créer un GridPane pour disposer les champs
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        // Créer les champs de saisie
        Label nameLabel = new Label("Nom :");
        nameField = new TextField();
        Label prenomLabel = new Label("Prénom :");
        prenomField = new TextField();
        Label emailLabel = new Label("Email :");
        emailField = new TextField();
        Label telLabel = new Label("Téléphone :");
        telField = new TextField();
        Label adresseLabel = new Label("Adresse :");
        adresseField = new TextField();

        // Créer les boutons
        Button saveButton = new Button("Enregistrer");
        Button cancelButton = new Button("Annuler");

        // Ajouter des marges
        this.changeMarging(nameLabel, nameField);
        this.changeMarging(prenomLabel, prenomField);
        this.changeMarging(emailLabel, emailField);
        this.changeMarging(telLabel, telField);
        this.changeMarging(adresseLabel, adresseField);
        GridPane.setMargin(saveButton, new Insets(10, 0, 0, 10));
        GridPane.setMargin(cancelButton, new Insets(10, 0, 0, 10));

        // Ajouter les champs et les boutons au GridPane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(prenomLabel, 0, 1);
        gridPane.add(prenomField, 1, 1);
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailField, 1, 2);
        gridPane.add(telLabel, 0, 3);
        gridPane.add(telField, 1, 3);
        gridPane.add(adresseLabel, 0, 4);
        gridPane.add(adresseField, 1, 4);
        gridPane.add(saveButton, 0, 5);
        gridPane.add(cancelButton, 1, 5);

        // Gérer l'action du bouton "Enregistrer"
        saveButton.setOnAction(event -> {
            // Récupérer les valeurs des champs de texte
            String name = nameField.getText();
            String prenom = prenomField.getText();
            String email = emailField.getText();
            String tel = telField.getText();
            String adresse = adresseField.getText();

            // Vérification des champs vides
            if (name.isEmpty() || prenom.isEmpty() || email.isEmpty() || tel.isEmpty() || adresse.isEmpty()) {
                showAlert("Erreur", "Tous les champs doivent être remplis.");
                return;
            }

            Client newClient = new Client();
            newClient.setNom(name);
            newClient.setPrenom(prenom);
            newClient.setEmail(email);
            newClient.setTelephone(tel);
            newClient.setAdresse(adresse);

            try {
                clientService.addClient(newClient);
                showAlert("Succès", "Le client a été ajouté avec succès.");
                clearForm();
            } catch (Exception e) {
                showAlert("Erreur", "Une erreur est survenue lors de l'ajout du client.");
            }
        });

        cancelButton.setOnAction(event -> {
            clearForm();
        });

        return gridPane;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        nameField.clear();
        prenomField.clear();
        emailField.clear();
        telField.clear();
        adresseField.clear();
    }
    private void changeMarging(Label label, TextField field) {
        GridPane.setMargin(label, new Insets(0, 0, 0, 10));
        GridPane.setMargin(field, new Insets(0, 0, 0, 10));
    }
}
