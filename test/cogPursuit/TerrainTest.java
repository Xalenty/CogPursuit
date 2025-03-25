package cogPursuit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TerrainTest {

    private Terrain terrain;

    @BeforeEach
    public void setUp() {
        // Initialise un terrain avant chaque test
        terrain = new Terrain();
    }

    @Test
    public void testGetCase() {
        // Test pour vérifier que l'on récupère bien la case à un indice spécifique
        Case case1 = terrain.getCase(0);  // Récupère la première case
        assertNotNull(case1, "La première case ne doit pas être nulle");
        assertEquals(0, case1.getNumCase(), "La première case devrait être la case 0");
    }

    @Test
    public void testToString() {
        // Test pour vérifier la méthode toString() du terrain
        String result = terrain.toString();
        System.out.println(result);  // Affiche le résultat pour vérification manuelle
        assertTrue(result.contains("Neurosciences"), "Le terrain devrait afficher des cases avec des thèmes");
    }

    @Test
    public void testCaseInitialisation() {
        // Vérifie que les cases sont initialisées avec les bons thèmes
        Case case1 = terrain.getCase(0);
        assertNotNull(case1, "La première case ne doit pas être nulle");
        assertEquals("Neurosciences", case1.getTheme(), "Le thème de la première case devrait être 'Neurosciences'");

        Case case2 = terrain.getCase(9);  // Exemple avec la 10ème case
        assertNotNull(case2, "La dixième case ne doit pas être nulle");
        assertEquals("Perception et Cognition", case2.getTheme(), "Le thème de la dixième case devrait être 'Perception et Cognition'");
    }

    @Test
    public void testCaseThemeCyclique() {
        // Vérifie que les thèmes sont cycliques
        Case case1 = terrain.getCase(0);
        Case case11 = terrain.getCase(10);
        Case case21 = terrain.getCase(20);

        assertEquals(case1.getTheme(), "Neurosciences", "Le thème de la première case devrait être 'Neurosciences'");
        assertEquals(case11.getTheme(), "Perception et Cognition", "Le thème de la 11ème case devrait être 'Perception et Cognition'");
        assertEquals(case21.getTheme(), "Système Nerveux et Physiologie", "Le thème de la 21ème case devrait être 'Système Nerveux et Physiologie'");
    }

    @Test
    public void testGetCaseOutOfBounds() {
        // Test pour vérifier la gestion des indices hors limites
        Case caseInvalid = terrain.getCase(100);  // Un indice qui dépasse la taille du tableau
        assertNull(caseInvalid, "La case demandée ne doit pas exister si l'indice est hors limites");
    }
}
