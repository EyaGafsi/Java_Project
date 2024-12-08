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
import org.example.demo1.entities.Avis;
import org.example.demo1.services.AvisService;
import org.example.demo1.services.servicesImpl.AvisServiceImpl;

import java.time.LocalDate;
import java.util.List;

public class AvisListView {
    private AvisService avisService;

    public AvisListView() {
        this.avisService = new AvisServiceImpl();
    }
    private ObservableList<Avis> avisList = FXCollections.observableArrayList();

    public BorderPane createAvisListView(Stage owner) {
        TableView<Avis> tableView = new TableView<>();

        // Définir les colonnes de la TableView
        TableColumn<Avis, Long> idColumn = new TableColumn<>("Id_Avis");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idAvisProperty().asObject());

        TableColumn<Avis, Double> noteColumn = new TableColumn<>("Note");
        noteColumn.setCellValueFactory(cellData -> cellData.getValue().noteProperty().asObject());

        TableColumn<Avis, String> commentaireColumn = new TableColumn<>("Commentaire");
        commentaireColumn.setCellValueFactory(cellData -> cellData.getValue().commentaireProperty());

        TableColumn<Avis, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());


        TableColumn<Avis, String> updateColumn = new TableColumn<>("");
        updateColumn.setCellFactory(new Callback<TableColumn<Avis, String>, TableCell<Avis, String>>() {
            @Override
            public TableCell<Avis, String> call(TableColumn<Avis, String> param) {
                return new TableCell<Avis, String>() {
                    private final Button button = new Button("Mettre à jour");

                    {
                        button.setOnAction(event -> {
                            Avis avis = getTableView().getItems().get(getIndex());
                            openUpdateModal(owner, avis, tableView); // Passer la TableView pour la mise à jour
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

        TableColumn<Avis, String> deleteColumn= new TableColumn<>("");
        deleteColumn.setCellFactory(new Callback<TableColumn<Avis, String>, TableCell<Avis, String>>() {
            @Override
            public TableCell<Avis, String> call(TableColumn<Avis, String> param) {
                return new TableCell<Avis, String>() {
                    private final Button button = new Button("Supprimer");

                    {
                        button.setOnAction(event -> {
                            Avis avis = getTableView().getItems().get(getIndex());
                            deleteAvis(avis, tableView); // Passer la TableView pour la mise à jour
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



        tableView.getColumns().addAll(idColumn, noteColumn, commentaireColumn, dateColumn, updateColumn,deleteColumn);

        loadAvisFromDatabase();

        tableView.setItems(avisList);

        // Créer un BorderPane pour contenir la TableView
        BorderPane avisListPane = new BorderPane();
        avisListPane.setCenter(tableView);

        return avisListPane;
    }

    private void loadAvisFromDatabase() {
        List<Avis> avis = avisService.getAvis();
        avisList.setAll(avis);
    }
    private void deleteAvis( Avis avis, TableView<Avis> tableView) {
        avisService.deleteAvis(avis);
        loadAvisFromDatabase();
        tableView.setItems(avisList); // Rafraîchir la TableView avec les nouvelles données

        // Afficher un message de confirmation
        showAlert("Suppression réussie", "L'avis a été supprimé.");
    }

    private void openUpdateModal(Stage owner, Avis avis, TableView<Avis> tableView) {
        // Créer une nouvelle fenêtre modale
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL); // Modal : bloque l'accès à la fenêtre principale
        modalStage.initOwner(owner);
        modalStage.setTitle("Mettre à jour les informations");

        // Créer les éléments du formulaire
        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(10);

        Label noteLabel = new Label("Note:");
        TextField noteField = new TextField(String.valueOf(avis.getNote()));
        Label commentaireLabel = new Label("Commentaire:");
        TextField commentaireField = new TextField(avis.getCommentaire());
        Label dateLabel = new Label("Date:");
        TextField dateField = new TextField(avis.getDate_avis().toString());
        Button saveButton = new Button("Enregistrer");
        Button cancelButton = new Button("Annuler");

        // Changer les marges
        this.changeMarging(noteLabel, noteField);
        this.changeMarging(commentaireLabel, commentaireField);
        this.changeMarging(dateLabel, dateField);
        GridPane.setMargin(saveButton, new Insets(0, 0, 0, 10));
        GridPane.setMargin(cancelButton, new Insets(0, 0, 0, 10));

        // Ajouter les éléments au GridPane
        gridPane.add(noteLabel, 0, 0);
        gridPane.add(noteField, 1, 0);
        gridPane.add(commentaireLabel, 0, 1);
        gridPane.add(commentaireField, 1, 1);
        gridPane.add(dateLabel, 0, 2);
        gridPane.add(dateField, 1, 2);
        gridPane.add(saveButton, 0, 3);
        gridPane.add(cancelButton, 1, 3);

        // Gérer les actions des boutons
        saveButton.setOnAction(event -> {
            avis.setNote(Double.parseDouble(noteField.getText()));
            avis.setCommentaire(commentaireField.getText());
            avis.setDate_avis(LocalDate.parse(dateField.getText()));

            avisService.updateAvis(avis);

            modalStage.close();

            loadAvisFromDatabase();
            tableView.setItems(avisList);

            showAlert("Mise à jour réussie", "Les informations de l'avis ont été mises à jour.");
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
