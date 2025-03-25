package cogPursuit;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test JUnit 5 pour tester les principales méthodes de ConnexionBdd.
 * Ces tests interagissent avec la base de données réelle.
 */
public class ConnexionBddTest {

    ConnexionBdd bdd = new ConnexionBdd();

    /**
     * Teste l'ajout d'un joueur dans la base de données.
     * Le pseudo est unique à chaque test pour éviter les doublons.
     * Le test vérifie simplement qu'aucune exception n'est levée.
     */
    @Test
    public void testAddJoueurBDD() {
        String pseudoTest = "TestJoueur_" + System.currentTimeMillis(); // Pseudo unique
        assertDoesNotThrow(() -> bdd.addJoueurBDD(pseudoTest), "L'ajout d'un joueur ne doit pas échouer");
        bdd.delJoueurBDD(pseudoTest);  // Nettoyage après le test
    }

    /**
     * Teste la récupération d'une question en base de données avec difficulté facile.
     * Le thème "Neurosciences" doit exister dans la base.
     * Vérifie que la question récupérée contient au moins 6 éléments (énoncé + choix).
     */
    @Test
    public void testRecuperationQuestion() {
        ArrayList<String> question = bdd.recuperationQuestion(1, "Neurosciences");
        assertNotNull(question, "La liste de question ne doit pas être nulle");
        assertTrue(question.size() >= 6, "La question récupérée doit contenir au moins l'énoncé et les choix");
    }

    /**
     * Teste la méthode qui récupère le classement des joueurs avec leur score total.
     * Vérifie que la liste retournée n'est pas vide.
     * Affiche le classement pour vérification manuelle dans la console.
     */
    @Test
    public void testGetClassementJoueursBDD() {
        ArrayList<String> classement = bdd.getClassementJoueursBDD();
        System.out.println("Classement des joueurs :" + classement);
        assertNotNull(classement, "La liste des classements ne doit pas être nulle");
        assertTrue(classement.size() >= 1, "Il devrait y avoir au moins un joueur dans le classement");
        classement.forEach(System.out::println); // Affichage dans la console
    }    
}
