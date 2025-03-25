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
import javafx.scene.control.Label;


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
    private StackPane[] map = new StackPane[30];
    private CogPursuit partie;
    private boolean charger = false;

    @FXML
    private Scene scene;

    public JeuController() {

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
        affichage.setVisible(true);
        int maxx = 7, maxy = 7, compteur = 0;
        for (int i = 0; i < maxx; i++) {
            for (int j = 0; j < maxy; j++) {
                if (compteur < tailleMap) {
                    StackPane caseJeu = new StackPane();
                    map[compteur]=caseJeu;//repertorie les bouton pour mettre laffichage a jour
                    
                    Label joueurs = new Label(compteur+1+"");//permettra de mettre les noms des joueurs sur une case
                    joueurs.setStyle("-fx-font-size: 18px; -fx-text-fill: black;");
                    caseJeu.setPrefSize(100, 100);
                    caseJeu.setStyle("-fx-border-color: black; -fx-background-color: lightblue;");//peux changer en fonction theme
                    caseJeu.setOpacity(1.0);

                    caseJeu.getChildren().add(joueurs);
                    affichage.add(caseJeu,(j+1),(i+1));
                    compteur++;
                }
            }
        }
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

    private void miseajourMap() {
        
    }

    private void touchePressee(KeyEvent event) {
        if (KeyCode.ESCAPE == event.getCode()) {// Effectuer une action lorsque la touche "Échap" est pressée
            System.out.println("echap");
        }
    }
}
