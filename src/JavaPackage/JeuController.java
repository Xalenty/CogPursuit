package JavaPackage;

import cogPursuit.CogPursuit;
import cogPursuit.ConnexionBdd;
import cogPursuit.Parametre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    @FXML private VBox zoneTour;
    @FXML private Label labelTour;
    @FXML private Label labelQuestion;
    @FXML private VBox boxReponses;
    @FXML private Label labelFeedback;
    @FXML private HBox boxDifficulte;
    @FXML private HBox boxFinPartie;

    private int numeroTour = 1;
    private cogPursuit.Question questionActuelle;
    private cogPursuit.Joueur joueurActuel;
    private int idPartieActuelle;





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

    private void demarrerTour() {
        joueurActuel = partie.getListeJoueurs().get(0);
        labelTour.setText("Tour " + numeroTour + " - " + joueurActuel.getPseudo());
    
        labelFeedback.setText(""); // Vide le feedback
    
        // Affiche seulement la difficulté
        boxDifficulte.setVisible(true);
        labelQuestion.setVisible(false);
        boxReponses.setVisible(false);
    }
    

    private void chargerQuestion(int difficulte) {
        cogPursuit.Case caseActuelle = partie.getTerrain().getCase(joueurActuel.getPos());
        questionActuelle = new cogPursuit.Question(difficulte, caseActuelle.getTheme());
    
        labelQuestion.setText(questionActuelle.getIntituler());
    
        boxReponses.getChildren().clear();
        ArrayList<String> reponses = questionActuelle.getReponses();
    
        for (int i = 0; i < reponses.size(); i++) {
            int index = i + 1;
            Button btn = new Button(index + " - " + reponses.get(i));
            btn.setOnAction(e -> verifierReponse(index));
            boxReponses.getChildren().add(btn);
        }
    
        // Mise à jour affichage
        boxDifficulte.setVisible(false);
        labelQuestion.setVisible(true);
        boxReponses.setVisible(true);
    }
    

    private void verifierReponse(int selection) {
        boolean bonne = questionActuelle.verification(selection);
        joueurActuel.ajouterScoreSelonDifficulte(bonne, questionActuelle.getDiff());
    
        int deplacement = bonne ? questionActuelle.getDiff() : 1 - questionActuelle.getDiff();
        joueurActuel.deplacement(deplacement, partie.getTerrain());
    
        // Affiche le feedback
        if (bonne) {
            labelFeedback.setText("🎉 Bien joué, c'était la bonne réponse !");
            labelFeedback.setStyle("-fx-text-fill: #4CAF50;");
        } else {
            labelFeedback.setText("❌ Mauvaise réponse !");
            labelFeedback.setStyle("-fx-text-fill: #FF5252;");
        }
    
        afficherPlateau();
        afficherClassement();
    
        // Cacher question + réponses
        labelQuestion.setVisible(false);
        boxReponses.setVisible(false);
    
        // 👉 Vérifie si le joueur a gagné
        if (joueurActuel.fin()) {
            labelTour.setText("🏁 " + joueurActuel.getPseudo() + " a gagné la partie !");
            labelFeedback.setText("🎉 Félicitations " + joueurActuel.getPseudo() + " !");
            boxReponses.setVisible(false);
            labelQuestion.setVisible(false);
    
            // Désactive les boutons de difficulté
            boxDifficulte.getChildren().stream()
                .filter(node -> node instanceof Button)
                .forEach(btn -> btn.setDisable(true));

    
            // Affiche les boutons de fin
            boxFinPartie.setVisible(true);
            boxFinPartie.setManaged(true);
            
            ConnexionBdd bdd = new ConnexionBdd();
            bdd.terminerPartie(idPartieActuelle);
            bdd.terminerPartie(idPartieActuelle); // Marque comme terminée

            bdd.mettreAJourScore(
                idPartieActuelle,
                joueurActuel.getPseudo(),
                joueurActuel.getScore(),
                joueurActuel.getPos()
            );


            return;
        }
    
        // ⏳ Pause avant le prochain tour (si pas de victoire)
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(e -> {
            numeroTour++;
            demarrerTour();
        });
        pause.play();
    }
    

    @FXML
    private void rejouerPartie() {
        // Réinitialise la partie
        String pseudo = joueurActuel.getPseudo();
        this.partie = new CogPursuit(pseudo);
        this.joueurActuel = this.partie.getListeJoueurs().get(0);
        this.numeroTour = 1;

        boxFinPartie.setVisible(false);
        boxFinPartie.setManaged(false);

        // Réactive les boutons de difficulté
        zoneTour.getChildren().stream()
            .filter(node -> node instanceof HBox)
            .flatMap(node -> ((HBox) node).getChildren().stream())
            .filter(node -> node instanceof Button)
            .forEach(btn -> btn.setDisable(false));

        afficherPlateauGraphique();
        demarrerTour();
    }

    @FXML
    private void retourMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1200, 900);
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

            // 👇 Récupère la fenêtre actuelle et change la scène
            Stage stage = (Stage) origine.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void choisirFacile() {
        chargerQuestion(1);
    }

    @FXML
    private void choisirMoyen() {
        chargerQuestion(2);
    }

    @FXML
    private void choisirDifficile() {
        chargerQuestion(3);
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
    
        for (int i = 0; i < Parametre.tailleMap; i++) {
            cogPursuit.Case c = partie.getTerrain().getCase(i);
            VBox caseBox = new VBox();
            caseBox.setMinSize(100, 50);
            caseBox.setStyle("-fx-background-color: " + getCouleurTheme(c.getTheme()) +
                             "; -fx-border-color: white; -fx-padding: 5; -fx-alignment: center;");
    
            Label label = new Label(c.getTheme());
            label.setStyle("-fx-text-fill: white;");
    
            caseBox.getChildren().add(label);
    
            // Ajoute le pion si le joueur est sur cette case
            if (joueurActuel != null && joueurActuel.getPos() == i) {
                Label pion = new Label("🟢"); // Icône de pion
                pion.setStyle("-fx-font-size: 18px;");
                caseBox.getChildren().add(pion);
            }
    
            plateau.add(caseBox, col, row);
    
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
        this.idPartieActuelle = idPartie;
    
        if (idPartie != -1) {
            System.out.println("Partie créée pour " + pseudo + ", id partie : " + idPartie);
    
            // 1. Crée la partie
            this.partie = new CogPursuit(pseudo);
    
            // 2. Initialise le joueur actuel
            this.joueurActuel = this.partie.getListeJoueurs().get(0);
    
            // 3. Affiche le plateau et classement
            afficherPlateauGraphique();
    
            // 4. Démarre le tour
            demarrerTour();
        } else {
            System.out.println("Erreur lors de la création de la partie.");
        }
    }
    
    
    
}
