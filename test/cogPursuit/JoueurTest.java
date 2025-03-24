package cogPursuit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JoueurTest {

    private Joueur joueur1;
    private Joueur joueur2;
    private Terrain terrain;
    private Case case1;
    private Case case2;

    @BeforeEach
    public void setUp() {
        // Création d'un terrain et de deux joueurs pour chaque test
        terrain = new Terrain();  // Terrain fictif
        case1 = new Case(0, "Départ");
        case2 = new Case(1, "Cognition");

        joueur1 = new Joueur("Alice", terrain);
        joueur2 = new Joueur("Bob", terrain);
    }

    @Test
    public void testAddScore() {
        // Test pour vérifier si l'ajout de score fonctionne correctement
        joueur1.addScore(10);
        assertEquals(10, joueur1.getScore(), "Le score du joueur devrait être 10");
    }

    @Test
    public void testGetPos() {
        // Test pour vérifier la position initiale du joueur
        assertEquals(0, joueur1.getPos(), "Le joueur devrait commencer à la case 0");
    }

    @Test
    public void testSetPos() {
        // Test pour vérifier que la position du joueur peut être modifiée
        joueur1.setPos(case2);
        assertEquals(1, joueur1.getPos(), "La position du joueur devrait être mise à jour à 1");
    }

    @Test
    public void testDeplacement() {
        // Test pour vérifier que le déplacement du joueur fonctionne correctement
        joueur1.deplacement(1, terrain);  // Le joueur se déplace de 1
        assertEquals(1, joueur1.getPos(), "Le joueur devrait se déplacer à la case 1");

        // Test de déplacement hors des limites (retour à la case 0)
        joueur1.deplacement(-2, terrain);  // Déplacement inverse au-delà de la première case
        assertEquals(0, joueur1.getPos(), "Le joueur devrait revenir à la case 0");
    }

    @Test
    public void testToString() {
        // Test pour vérifier que la méthode toString renvoie le pseudo du joueur
        assertEquals("Alice", joueur1.toString(), "La méthode toString devrait renvoyer le pseudo du joueur");
    }
}
