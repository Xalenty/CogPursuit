/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package JavaPackage;

import cogPursuit.CogPursuit;
import cogPursuit.Parametre;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ztsuk
 */
public class JeuController implements Initializable, Parametre {

    @FXML
    private AnchorPane origine;
    @FXML
    private GridPane affichage;
    
    private CogPursuit partie;
    private boolean charger = false;

    @FXML
    private Scene scene;
    public JeuController(){
    
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (!charger) {
            Chargement();
        } 
        setTerrain();
    }
    
    
    private void setTerrain() {
          affichage.setPrefSize(20, 400);
          affichage.setVisible(true);
        int maxx = 7, maxy = 7, compteur = 0;
        for (int i = 0; i < maxx; i++) {
            for (int j = 0; j < maxy; j++) {
                if (compteur < tailleMap) {
                    StackPane casePane = new StackPane();
                    casePane.setPrefSize(10, 10);
                    casePane.setStyle("-fx-border-color: black; -fx-background-color: lightgray;");
                    affichage.add(casePane, j, i);
                    compteur++;
                }
            }
        }
        Platform.runLater(() -> affichage.layout());//force la maj de l'affichage
    }
    private void Chargement() {
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
    }

    private void miseajour() {

    }

    private void touchePressee(KeyEvent event) {
        if (KeyCode.ESCAPE == event.getCode()) {// Effectuer une action lorsque la touche "Échap" est pressée
            System.out.println("echap");
        }
    }
}
