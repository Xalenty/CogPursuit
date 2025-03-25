package cogPursuit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public void testToStringFormat() {
        String result = terrain.toString();
        // Vérifie que la chaîne contient des représentations de cases
        assertTrue(result.contains("["), "La représentation du terrain devrait contenir des crochets '[' pour les cases");
    }

    @Test
    public void testAfficherThemes() {
        String result = terrain.afficherThemes();
        System.out.println(result);
        assertTrue(result.contains("Neurosciences"), "La méthode afficherThemes() devrait contenir le thème 'Neurosciences'");
    }



    @Test
    public void testCaseInitialisation() {
        // Vérifie que les premières cases sont bien initialisées avec un thème valide
        Case case1 = terrain.getCase(0);
        Case case10 = terrain.getCase(10);

        assertNotNull(case1, "La première case ne doit pas être nulle");
        assertNotNull(case10, "La dixième case ne doit pas être nulle");

        // Le thème doit appartenir à la liste des thèmes connus
        List<String> themes = Arrays.asList(Parametre.listeTheme);
        assertTrue(themes.contains(case1.getTheme()), "Le thème de la case 0 doit être un thème connu");
        assertTrue(themes.contains(case10.getTheme()), "Le thème de la case 10 doit être un thème connu");
    }


    @Test
    public void testThemesSontBienDistribues() {
        Set<String> themesTrouves = new HashSet<>();

        for (int i = 0; i < Parametre.tailleMap; i++) {
            themesTrouves.add(terrain.getCase(i).getTheme());
        }

        // Vérifie qu'au moins 2 (voire 3) thèmes différents ont été utilisés
        assertTrue(themesTrouves.size() >= 2, "Il devrait y avoir au moins 2 thèmes différents sur le plateau");
    }


    @Test
    public void testGetCaseOutOfBounds() {
        // Test pour vérifier la gestion des indices hors limites
        Case caseInvalid = terrain.getCase(100);  // Un indice qui dépasse la taille du tableau
        assertNull(caseInvalid, "La case demandée ne doit pas exister si l'indice est hors limites");
    }
}
