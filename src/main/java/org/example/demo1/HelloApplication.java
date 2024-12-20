package org.example.demo1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.demo1.views.*;

public class HelloApplication extends Application {
    private BorderPane root;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the MenuBar
        MenuBar menuBar = new MenuBar();

        Menu clientMenu = new Menu("Clients");

        MenuItem addItem = new MenuItem("Ajouter Client");
        addItem.setOnAction(event -> {
            showClientAdd();
        });

        MenuItem getItem = new MenuItem("Afficher Clients");
        getItem.setOnAction(event -> {
            showClientList();
        });

        clientMenu.getItems().addAll(addItem, getItem);

        Menu activiteMenu = new Menu("Activitées");

        addItem = new MenuItem("Ajouter Activitée");
        addItem.setOnAction(event -> {
        });

        getItem = new MenuItem("Afficher Activitées");
        getItem.setOnAction(event -> {
            showActiviteList();
        });

        activiteMenu.getItems().addAll(addItem, getItem);

        Menu compannieMenu = new Menu("Compannie Aérienne");

        addItem = new MenuItem("Ajouter Compannie");
        addItem.setOnAction(event -> {
        });

        getItem = new MenuItem("Afficher Compannies");
        getItem.setOnAction(event -> {
            showCompannieAerienneList();
        });

        compannieMenu.getItems().addAll(addItem, getItem);

        Menu destinationMenu = new Menu("Destinations");

        addItem = new MenuItem("Ajouter Destination");
        addItem.setOnAction(event -> {
        });

        getItem = new MenuItem("Afficher Destinations");
        getItem.setOnAction(event -> {
        });

        destinationMenu.getItems().addAll(addItem, getItem);

        Menu groupeMenu = new Menu("Groupes");

        addItem = new MenuItem("Ajouter Groupe");
        addItem.setOnAction(event -> {
        });

        getItem = new MenuItem("Afficher Groupes");
        getItem.setOnAction(event -> {
        });

        groupeMenu.getItems().addAll(addItem, getItem);
        Menu hotelMenu = new Menu("Hotels");

        addItem = new MenuItem("Ajouter Hotel");
        addItem.setOnAction(event -> {
        });

        getItem = new MenuItem("Afficher Hotels");
        getItem.setOnAction(event -> {
        });

        hotelMenu.getItems().addAll(addItem, getItem);

        Menu transportMenu = new Menu("Transports");

        addItem = new MenuItem("Ajouter Transport");
        addItem.setOnAction(event -> {
        });

        getItem = new MenuItem("Afficher Transports");
        getItem.setOnAction(event -> {
        });

        transportMenu.getItems().addAll(addItem, getItem);

        Menu ReservationMenu = new Menu("Reservations");

        addItem = new MenuItem("Ajouter Reservation");
        addItem.setOnAction(event -> {
        });

        getItem = new MenuItem("Afficher Reservations");
        getItem.setOnAction(event -> {
        });

        ReservationMenu.getItems().addAll(addItem, getItem);

        Menu volMenu = new Menu("Vols");

        addItem = new MenuItem("Ajouter Vol");
        addItem.setOnAction(event -> {
        });

        getItem = new MenuItem("Afficher Vols");
        getItem.setOnAction(event -> {
        });

        volMenu.getItems().addAll(addItem, getItem);

        Menu voyageMenu = new Menu("Voyages");

        addItem = new MenuItem("Ajouter Voyage");
        addItem.setOnAction(event -> {
        });

        getItem = new MenuItem("Afficher Voyages");
        getItem.setOnAction(event -> {
        });

        voyageMenu.getItems().addAll(addItem, getItem);


        Menu avisMenu = new Menu("Avis");

        addItem = new MenuItem("Ajouter Avis");
        addItem.setOnAction(event -> {
        });

        getItem = new MenuItem("Afficher Avis");
        getItem.setOnAction(event -> {
            showAvisList();
        });

        avisMenu.getItems().addAll(addItem, getItem);

        // Add the menus to the MenuBar
        menuBar.getMenus().addAll(clientMenu, activiteMenu,avisMenu,compannieMenu,destinationMenu,groupeMenu,hotelMenu,transportMenu,ReservationMenu,volMenu,voyageMenu);

        // Layout
        root = new BorderPane();
        root.setTop(menuBar);

        // Scene setup
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Agence de voyage");
        primaryStage.show();
    }

    // Utility method to show a simple alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showClientList() {
        ClientListView clientListView = new ClientListView();

        // Créer la vue de la liste des clients
        BorderPane clientListPane = clientListView.createClientListView(primaryStage);

        // Remplacer uniquement le "center" du BorderPane sans changer la scène entière
        root.setCenter(clientListPane);
    }
    private void showActiviteList() {
        ActiviteListView activiteListView = new ActiviteListView();

        // Créer la vue de la liste des clients
        BorderPane activiteListPane = activiteListView.createActiviteListView(primaryStage);

        // Remplacer uniquement le "center" du BorderPane sans changer la scène entière
        root.setCenter(activiteListPane);
    }
    private void showAvisList() {
         AvisListView avisListView = new AvisListView();

        // Créer la vue de la liste des clients
        BorderPane avisListPane = avisListView.createAvisListView(primaryStage);

        // Remplacer uniquement le "center" du BorderPane sans changer la scène entière
        root.setCenter(avisListPane);
    }
    private void showCompannieAerienneList() {
        CompannieAerienneListView compannieAerienneListView = new CompannieAerienneListView();

        // Créer la vue de la liste des clients
        BorderPane compannieAerienneListPane = compannieAerienneListView.createCompannieAerienneListView(primaryStage);

        // Remplacer uniquement le "center" du BorderPane sans changer la scène entière
        root.setCenter(compannieAerienneListPane);
    }
    private void showClientAdd() {
        ClientAddView clientAddView = new ClientAddView();
        GridPane clientAddPane = clientAddView.showAddClientForm();
        root.setCenter(clientAddPane);
    }
}
