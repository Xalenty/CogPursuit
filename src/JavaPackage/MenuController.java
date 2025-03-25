/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package JavaPackage;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ztsuk
 */
public class MenuController implements Initializable {

    @FXML
    private Scene scene;
    @FXML
    private AnchorPane origine;
    @FXML
    private VBox hub;
    @FXML
    private VBox modeJeu;
    @FXML
    private Button modeSolo;
    @FXML
    private Button modeMulti;
    @FXML
    private Button option;

    private void touchePressee(KeyEvent event) {
        if (KeyCode.ESCAPE == event.getCode()) {// Effectuer une action lorsque la touche "Échap" est pressée

            if (!modeJeu.isDisable()) {
                inverseAffichage(hub, modeJeu);

            }
        }
    }

    private void inverseAffichage(VBox visible, VBox aActiver) {
        visible.setDisable(!visible.isDisable());
        visible.setVisible(!visible.isDisable());

        aActiver.setDisable(!aActiver.isDisable());
        aActiver.setVisible(!aActiver.isDisable());

    }

    /**
     * Zone bouton / interaction avec fxml
     */
    @FXML
    private void btnPlay() {
        inverseAffichage(hub, modeJeu);

    }

    @FXML
    private void btnOption() {

    }

    @FXML
    private void btnScores() {

    }

    @FXML
    private void btnQuitter() {
        System.exit(0);
    }

    @FXML
    private void solo() {
        try {
            FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("Jeu.fxml"));
            Parent parent = gameLoader.load();

            Scene sceneJeu = new Scene(parent, 800, 600);

            Stage stage = (Stage) origine.getScene().getWindow();

            // Afficher la scène du jeu
            stage.setScene(sceneJeu);
            stage.setTitle("CogPursuit - Jeu");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void multi() {

    }

    /**
     * Initializes the controller class.
     */
    private boolean charger = false;

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) { //se lance au chargement de la page FXML
        if (!charger) {
            origine.setFocusTraversable(true);
            origine.sceneProperty().addListener((observable, aScene, nScene) -> {//permet de savoir quand le premier element du FXML est generer afin de recuperer la scene
                if (nScene != null) {
                    scene = nScene;
                    scene.setOnKeyPressed(this::touchePressee);
                    origine.requestFocus(); //fait le focus une fois que la scène est définie
                    charger = !charger;
                } else {
                    System.out.println("Chargement...");
                }
            });

        } else {
            return;
        }

    }

}
