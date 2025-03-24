package cogPursuit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ConnexionBddTest {

    @Test
    public void testRecuperationQuestion() {
        ConnexionBdd bdd = new ConnexionBdd();
        ArrayList<String> result = bdd.recuperationQuestion(1, "Neurosciences"); 

        // Vérifie que la liste n'est pas vide
        assertFalse(result.isEmpty(), "La récupération de question ne devrait pas renvoyer une liste vide");
        // Optionnel : on peut tester qu'elle contient au moins 6-8 éléments attendus
        assertTrue(result.size() >= 6, "La question récupérée devrait contenir plusieurs champs");
    }

    @Test
    public void testGetJoueurBDD() {
        ConnexionBdd bdd = new ConnexionBdd();
        ArrayList<String> joueurs = bdd.getJoueurBDD();

        assertNotNull(joueurs, "La liste des joueurs ne doit pas être nulle");
        joueurs.forEach(System.out::println);
    }

    @Test
    public void testAddPointVictory() {
        ConnexionBdd bdd = new ConnexionBdd();
        // Remplacer par un pseudo existant dans la BDD
        String pseudo = "testuser";

        // Appel de la méthode (pas de retour à vérifier)
        assertDoesNotThrow(() -> bdd.addPointVictory(pseudo), "L'ajout d'un point de victoire ne doit pas échouer");
    }
}
