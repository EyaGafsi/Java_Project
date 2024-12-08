package org.example.demo1.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.demo1.entities.Client;
import org.example.demo1.services.ClientService;
import org.example.demo1.services.servicesImpl.ClientServiceImpl;

import java.util.List;

public class TransportListView {
    private ClientService clientService;

    public TransportListView() {
        this.clientService = new ClientServiceImpl();
    }
    private ObservableList<Client> clientList = FXCollections.observableArrayList();

    public BorderPane createActiviteListView(Stage owner) {
        // Créer la TableView pour afficher la liste des clients
        TableView<Client> tableView = new TableView<>();

        // Définir les colonnes de la TableView
        TableColumn<Client, Long> idColumn = new TableColumn<>("Id_Client");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idClientProperty().asObject());

        TableColumn<Client, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());

        TableColumn<Client, String> prenomColumn = new TableColumn<>("Prenom");
        prenomColumn.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());

        TableColumn<Client, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        TableColumn<Client, String> telColumn = new TableColumn<>("Telephone");
        telColumn.setCellValueFactory(cellData -> cellData.getValue().telephoneProperty());

        TableColumn<Client, String> adrColumn = new TableColumn<>("Adresse");
        adrColumn.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());

        TableColumn<Client, String> updateColumn = new TableColumn<>("");
        updateColumn.setCellFactory(new Callback<TableColumn<Client, String>, TableCell<Client, String>>() {
            @Override
            public TableCell<Client, String> call(TableColumn<Client, String> param) {
                return new TableCell<Client, String>() {
                    private final Button button = new Button("Mettre à jour");

                    {
                        button.setOnAction(event -> {
                            Client client = getTableView().getItems().get(getIndex());
                            openUpdateModal(owner, client, tableView); // Passer la TableView pour la mise à jour
                        });
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button); // Afficher le bouton dans la cellule
                        }
                    }
                };
            }
        });

        TableColumn<Client, String> deleteColumn= new TableColumn<>("");
        deleteColumn.setCellFactory(new Callback<TableColumn<Client, String>, TableCell<Client, String>>() {
            @Override
            public TableCell<Client, String> call(TableColumn<Client, String> param) {
                return new TableCell<Client, String>() {
                    private final Button button = new Button("Supprimer");

                    {
                        button.setOnAction(event -> {
                            Client client = getTableView().getItems().get(getIndex());
                            deleteClient( client, tableView); // Passer la TableView pour la mise à jour
                        });
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(button); // Afficher le bouton dans la cellule
                        }
                    }
                };
            }
        });



        tableView.getColumns().addAll(idColumn, nameColumn, prenomColumn, emailColumn, telColumn, adrColumn, updateColumn,deleteColumn);

        // Charger les clients depuis la base de données
        loadClientsFromDatabase();

        // Assigner la liste des clients à la TableView
        tableView.setItems(clientList);

        // Créer un BorderPane pour contenir la TableView
        BorderPane clientListPane = new BorderPane();
        clientListPane.setCenter(tableView);

        return clientListPane;
    }

    // Méthode pour charger les clients depuis la base de données
    private void loadClientsFromDatabase() {
        List<Client> clients = clientService.getClients();
        clientList.setAll(clients); // Mettre à jour la liste observable des clients
    }
    private void deleteClient( Client client, TableView<Client> tableView) {
        clientService.deleteClient(client);
        loadClientsFromDatabase();
        tableView.setItems(clientList); // Rafraîchir la TableView avec les nouvelles données

        // Afficher un message de confirmation
        showAlert("Suppression réussie", "Le Client a été supprimé.");
    }

    // Méthode pour ouvrir la fenêtre modale et mettre à jour un client
    private void openUpdateModal(Stage owner, Client client, TableView<Client> tableView) {
        // Créer une nouvelle fenêtre modale
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL); // Modal : bloque l'accès à la fenêtre principale
        modalStage.initOwner(owner);
        modalStage.setTitle("Mettre à jour les informations");

        // Créer les éléments du formulaire
        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(10);

        Label nameLabel = new Label("Nom:");
        TextField nameField = new TextField(client.getNom());
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField(client.getEmail());
        Label adresseLabel = new Label("Adresse:");
        TextField adresseField = new TextField(client.getAdresse());
        Label prenomLabel = new Label("Prénom:");
        TextField prenomField = new TextField(client.getPrenom());
        Label telLabel = new Label("Telephone:");
        TextField telField = new TextField(client.getTelephone());
        Button saveButton = new Button("Enregistrer");
        Button cancelButton = new Button("Annuler");

        // Changer les marges
        this.changeMarging(nameLabel, nameField);
        this.changeMarging(prenomLabel, prenomField);
        this.changeMarging(emailLabel, emailField);
        this.changeMarging(telLabel, telField);
        this.changeMarging(adresseLabel, adresseField);
        GridPane.setMargin(saveButton, new Insets(0, 0, 0, 10));
        GridPane.setMargin(cancelButton, new Insets(0, 0, 0, 10));

        // Ajouter les éléments au GridPane
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

        // Gérer les actions des boutons
        saveButton.setOnAction(event -> {
            // Mettre à jour le client avec les nouvelles données
            client.setNom(nameField.getText());
            client.setEmail(emailField.getText());
            client.setAdresse(adresseField.getText());
            client.setPrenom(prenomField.getText());
            client.setTelephone(telField.getText());
            clientService.updateClient(client);

            modalStage.close();

            loadClientsFromDatabase();
            tableView.setItems(clientList);

            showAlert("Mise à jour réussie", "Les informations du client ont été mises à jour.");
        });

        cancelButton.setOnAction(event -> modalStage.close());

        Scene modalScene = new Scene(gridPane, 400, 300);
        modalStage.setScene(modalScene);
        modalStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void changeMarging(Label label, TextField field) {
        GridPane.setMargin(label, new Insets(0, 0, 0, 10)); // Marge gauche de 10
        GridPane.setMargin(field, new Insets(0, 0, 0, 10));
    }
}
