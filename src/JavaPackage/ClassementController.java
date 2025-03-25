package JavaPackage;

import cogPursuit.ConnexionBdd;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ClassementController {

    @FXML
    private ListView<String> listeScores;

    @FXML
    private AnchorPane origine; // Doit correspondre au fx:id dans le FXML

    @FXML
    public void initialize() {
        ConnexionBdd bdd = new ConnexionBdd();
        ArrayList<String> classement = bdd.getClassementJoueursBDD();
        listeScores.getItems().addAll(classement);

        // Gestion touche ESC
        origine.setFocusTraversable(true); // Nécessaire pour recevoir les touches
        origine.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::handleKeyPressed);
                origine.requestFocus(); // Pour bien capter les événements clavier
            }
        });
    }

    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            retourMenu();
        }
    }

    @FXML
    private void retourMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1200, 900);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            Stage stage = (Stage) listeScores.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
