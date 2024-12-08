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
import org.example.demo1.entities.Activite;
import org.example.demo1.services.ActiviteService;
import org.example.demo1.services.servicesImpl.ActiviteServiceImpl;

import java.util.List;

public class ActiviteListView {
    private ActiviteService activiteService;

    public ActiviteListView() {
        this.activiteService = new ActiviteServiceImpl();
    }
    private ObservableList<Activite> activiteList = FXCollections.observableArrayList();

    public BorderPane createActiviteListView(Stage owner) {
        TableView<Activite> tableView = new TableView<>();

        // Définir les colonnes de la TableView
        TableColumn<Activite, Long> idColumn = new TableColumn<>("Id_Activité");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idActiviteProperty().asObject());

        TableColumn<Activite, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nomProperty());

        TableColumn<Activite, Double> prixColumn = new TableColumn<>("Prix");
        prixColumn.setCellValueFactory(cellData -> cellData.getValue().prixProperty().asObject());

        TableColumn<Activite, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        TableColumn<Activite, String> updateColumn = new TableColumn<>("");
        updateColumn.setCellFactory(new Callback<TableColumn<Activite, String>, TableCell<Activite, String>>() {
            @Override
            public TableCell<Activite, String> call(TableColumn<Activite, String> param) {
                return new TableCell<Activite, String>() {
                    private final Button button = new Button("Mettre à jour");

                    {
                        button.setOnAction(event -> {
                            Activite activite = getTableView().getItems().get(getIndex());
                            openUpdateModal(owner, activite, tableView); // Passer la TableView pour la mise à jour
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

        TableColumn<Activite, String> deleteColumn= new TableColumn<>("");
        deleteColumn.setCellFactory(new Callback<TableColumn<Activite, String>, TableCell<Activite, String>>() {
            @Override
            public TableCell<Activite, String> call(TableColumn<Activite, String> param) {
                return new TableCell<Activite, String>() {
                    private final Button button = new Button("Supprimer");

                    {
                        button.setOnAction(event -> {
                            Activite activite = getTableView().getItems().get(getIndex());
                            deleteActivite( activite, tableView); // Passer la TableView pour la mise à jour
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



        tableView.getColumns().addAll(idColumn, nameColumn, prixColumn, descriptionColumn, updateColumn,deleteColumn);

        loadAtivitesFromDatabase();

        tableView.setItems(activiteList);

        // Créer un BorderPane pour contenir la TableView
        BorderPane activiteListPane = new BorderPane();
        activiteListPane.setCenter(tableView);

        return activiteListPane;
    }

    private void loadAtivitesFromDatabase() {
        List<Activite> activites = activiteService.getActivitees();
        activiteList.setAll(activites);
    }
    private void deleteActivite( Activite activite, TableView<Activite> tableView) {
        activiteService.deleteActivite(activite);
        loadAtivitesFromDatabase();
        tableView.setItems(activiteList); // Rafraîchir la TableView avec les nouvelles données

        // Afficher un message de confirmation
        showAlert("Suppression réussie", "L'acitivité a été supprimé.");
    }

    private void openUpdateModal(Stage owner, Activite activite, TableView<Activite> tableView) {
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
        TextField nameField = new TextField(activite.getNom_activite());
        Label prixLabel = new Label("Prix:");
        TextField prixField = new TextField(String.valueOf(activite.getPrix_activite()));
        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField(activite.getDescription());
        Button saveButton = new Button("Enregistrer");
        Button cancelButton = new Button("Annuler");

        // Changer les marges
        this.changeMarging(nameLabel, nameField);
        this.changeMarging(prixLabel, prixField);
        this.changeMarging(descriptionLabel, descriptionField);
        GridPane.setMargin(saveButton, new Insets(0, 0, 0, 10));
        GridPane.setMargin(cancelButton, new Insets(0, 0, 0, 10));

        // Ajouter les éléments au GridPane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(prixLabel, 0, 1);
        gridPane.add(prixField, 1, 1);
        gridPane.add(descriptionLabel, 0, 2);
        gridPane.add(descriptionField, 1, 2);
        gridPane.add(saveButton, 0, 3);
        gridPane.add(cancelButton, 1, 3);

        // Gérer les actions des boutons
        saveButton.setOnAction(event -> {
            activite.setNom_activite(nameField.getText());
            activite.setPrix_activite(Double.parseDouble(prixField.getText()));
            activite.setDescription(descriptionField.getText());

            activiteService.updateActivite(activite);

            modalStage.close();

            loadAtivitesFromDatabase();
            tableView.setItems(activiteList);

            showAlert("Mise à jour réussie", "Les informations de l'activité ont été mises à jour.");
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
