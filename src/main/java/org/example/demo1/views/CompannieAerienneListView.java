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
import org.example.demo1.entities.CompannieAerienne;
import org.example.demo1.services.ClientService;
import org.example.demo1.services.CompannieAerienneService;
import org.example.demo1.services.servicesImpl.ClientServiceImpl;
import org.example.demo1.services.servicesImpl.CompannieAerienneServiceImpl;

import java.util.List;

public class CompannieAerienneListView {
    private CompannieAerienneService compannieAerienneService;

    public CompannieAerienneListView() {
        this.compannieAerienneService = new CompannieAerienneServiceImpl();
    }
    private ObservableList<CompannieAerienne> compannieAerienneList = FXCollections.observableArrayList();

    public BorderPane createCompannieAerienneListView(Stage owner) {
        // Créer la TableView pour afficher la liste des clients
        TableView<CompannieAerienne> tableView = new TableView<>();

        // Définir les colonnes de la TableView
        TableColumn<CompannieAerienne, Long> idColumn = new TableColumn<>("Id_Compannie");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idCompannieArienneProperty().asObject());

        TableColumn<CompannieAerienne, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());

        TableColumn<CompannieAerienne, String> paysColumn = new TableColumn<>("Pays");
        paysColumn.setCellValueFactory(cellData -> cellData.getValue().paysProperty());

        TableColumn<CompannieAerienne, String> siteWebColumn = new TableColumn<>("Site Web");
        siteWebColumn.setCellValueFactory(cellData -> cellData.getValue().siteWebProperty());

        TableColumn<CompannieAerienne, String> updateColumn = new TableColumn<>("");
        updateColumn.setCellFactory(new Callback<TableColumn<CompannieAerienne, String>, TableCell<CompannieAerienne, String>>() {
            @Override
            public TableCell<CompannieAerienne, String> call(TableColumn<CompannieAerienne, String> param) {
                return new TableCell<CompannieAerienne, String>() {
                    private final Button button = new Button("Mettre à jour");

                    {
                        button.setOnAction(event -> {
                            CompannieAerienne compannieAerienne = getTableView().getItems().get(getIndex());
                            openUpdateModal(owner, compannieAerienne, tableView); // Passer la TableView pour la mise à jour
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

        TableColumn<CompannieAerienne, String> deleteColumn= new TableColumn<>("");
        deleteColumn.setCellFactory(new Callback<TableColumn<CompannieAerienne, String>, TableCell<CompannieAerienne, String>>() {
            @Override
            public TableCell<CompannieAerienne, String> call(TableColumn<CompannieAerienne, String> param) {
                return new TableCell<CompannieAerienne, String>() {
                    private final Button button = new Button("Supprimer");

                    {
                        button.setOnAction(event -> {
                            CompannieAerienne compannieAerienne = getTableView().getItems().get(getIndex());
                            deleteClient( compannieAerienne, tableView); // Passer la TableView pour la mise à jour
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



        tableView.getColumns().addAll(idColumn, nameColumn, paysColumn, siteWebColumn, updateColumn,deleteColumn);

        // Charger les clients depuis la base de données
        loadCompannieAerienneFromDatabase();

        // Assigner la liste des clients à la TableView
        tableView.setItems(compannieAerienneList);

        // Créer un BorderPane pour contenir la TableView
        BorderPane compannieAerienneListPane = new BorderPane();
        compannieAerienneListPane.setCenter(tableView);

        return compannieAerienneListPane;
    }

    // Méthode pour charger les clients depuis la base de données
    private void loadCompannieAerienneFromDatabase() {
        List<CompannieAerienne> compannieAeriennes = compannieAerienneService.getCompannieAerienne();
        compannieAerienneList.setAll(compannieAeriennes); // Mettre à jour la liste observable des clients
    }
    private void deleteClient( CompannieAerienne compannieAerienne, TableView<CompannieAerienne> tableView) {
        compannieAerienneService.deleteCompannieAerienne(compannieAerienne);
        loadCompannieAerienneFromDatabase();
        tableView.setItems(compannieAerienneList); // Rafraîchir la TableView avec les nouvelles données

        // Afficher un message de confirmation
        showAlert("Suppression réussie", "Le compannie a été supprimé.");
    }

    // Méthode pour ouvrir la fenêtre modale et mettre à jour un client
    private void openUpdateModal(Stage owner, CompannieAerienne compannieAerienne, TableView<CompannieAerienne> tableView) {
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
        TextField nameField = new TextField(compannieAerienne.getNom_compagnnies());
        Label paysLabel = new Label("Pays:");
        TextField paysField = new TextField(compannieAerienne.getPays());
        Label siteLabel = new Label("Site web:");
        TextField siteField = new TextField(compannieAerienne.getSite_web());
        Button saveButton = new Button("Enregistrer");
        Button cancelButton = new Button("Annuler");

        // Changer les marges
        this.changeMarging(nameLabel, nameField);
        this.changeMarging(paysLabel, paysField);
        this.changeMarging(siteLabel, siteField);
        GridPane.setMargin(saveButton, new Insets(0, 0, 0, 10));
        GridPane.setMargin(cancelButton, new Insets(0, 0, 0, 10));

        // Ajouter les éléments au GridPane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(paysLabel, 0, 1);
        gridPane.add(paysField, 1, 1);
        gridPane.add(siteLabel, 0, 2);
        gridPane.add(siteField, 1, 2);
        gridPane.add(saveButton, 0, 3);
        gridPane.add(cancelButton, 1, 3);

        // Gérer les actions des boutons
        saveButton.setOnAction(event -> {
            // Mettre à jour le client avec les nouvelles données
            compannieAerienne.setNom_compagnnies(nameField.getText());
            compannieAerienne.setPays(paysField.getText());
            compannieAerienne.setSite_web(siteField.getText());
            compannieAerienneService.updateCompannieAerienne(compannieAerienne);

            modalStage.close();

            loadCompannieAerienneFromDatabase();
            tableView.setItems(compannieAerienneList);

            showAlert("Mise à jour réussie", "Les informations du compannie ont été mises à jour.");
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
