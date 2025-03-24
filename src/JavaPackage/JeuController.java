/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package JavaPackage;

import cogPursuit.CogPursuit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ztsuk
 */
public class JeuController implements Initializable {

    @FXML
    private AnchorPane origine;
    private CogPursuit partie;
    private boolean charger = false;
    
    @FXML
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (!charger) {
            Chargement();
        } else {
            return;
        }

    }
    
    private void Chargement(){
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
        
    private void touchePressee(KeyEvent event) {
        if (KeyCode.ESCAPE == event.getCode()) {// Effectuer une action lorsque la touche "Échap" est pressée
            System.out.println("echap");
        }
    }
}
