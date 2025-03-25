package JavaPackage;

import cogPursuit.CogPursuit;
import cogPursuit.ConnexionBdd;
import cogPursuit.Parametre;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 */
public class JeuController implements Initializable {

    @FXML
    private AnchorPane origine;
    private CogPursuit partie;
    private boolean charger = false;

    @FXML
    private Scene scene;

    @FXML
    private javafx.scene.layout.GridPane plateau;

    @FXML
    private javafx.scene.layout.VBox classementBox;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (!charger) {
            chargement();
        }
    }

    private void chargement() {
        origine.setFocusTraversable(true);
        origine.sceneProperty().addListener((observable, aScene, nScene) -> {
            if (nScene != null) {
                scene = nScene;
                scene.setOnKeyPressed(this::touchePressee);
                origine.requestFocus();
                charger = true;
            } else {
                System.out.println("Chargement...");
            }
        });
    }

    private void touchePressee(KeyEvent event) {
        if (KeyCode.ESCAPE == event.getCode()) {
            System.out.println("Échap pressée - Retour en arrière (non encore implémenté).");
        }
    }

    private String getCouleurTheme(String theme) {
        return switch (theme) {
            case "Neurosciences" -> "#4CAF50";
            case "Perception et Cognition" -> "#2196F3";
            case "Système Nerveux et Physiologie" -> "#FF9800";
            default -> "#9E9E9E"; // gris
        };
    }

    private void afficherClassement() {
        classementBox.getChildren().clear();
    
        for (cogPursuit.Joueur j : partie.getListeJoueurs()) {
            javafx.scene.control.Label label = new javafx.scene.control.Label(j.getPseudo() + " : " + j.getScore() + " pts");
            label.setStyle("-fx-text-fill: white;");
            classementBox.getChildren().add(label);
        }
    }
    

    private void afficherPlateau() {
        plateau.getChildren().clear();
    
        int col = 0;
        int row = 0;
    
        partie.getTerrain();
        for (int i = 0; i < Parametre.tailleMap; i++) {
            cogPursuit.Case c = partie.getTerrain().getCase(i);
            javafx.scene.control.Label caseLabel = new javafx.scene.control.Label(" " + c.getTheme() + " ");
            caseLabel.setMinSize(100, 50);
            caseLabel.setStyle("-fx-background-color: " + getCouleurTheme(c.getTheme()) +
                               "; -fx-border-color: white; -fx-padding: 5; -fx-text-fill: white;");
    
            plateau.add(caseLabel, col, row);
    
            col++;
            if (col >= 10) {
                col = 0;
                row++;
            }
        }
    } 
    
    public void afficherPlateauGraphique() {
        afficherPlateau();
        afficherClassement();
    }
    

    /**
     * Initialise la partie solo (ajoute joueur + partie en BDD)
     */
    public void lancerPartieSolo(String pseudo) {
        ConnexionBdd bdd = new ConnexionBdd();
        int idPartie = bdd.creerPartieSolo(pseudo);
        if (idPartie != -1) {
            System.out.println("Partie créée pour " + pseudo + ", id partie : " + idPartie);
    
            this.partie = new CogPursuit(pseudo); 
            afficherPlateauGraphique(); 
        } else {
            System.out.println("Erreur lors de la création de la partie.");
        }
    }
    
    
}
