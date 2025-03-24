package cogPursuit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CaseTest {

    private Case case1;
    private Joueur joueur1;
    private Joueur joueur2;

    @BeforeEach
public void setUp() {
    case1 = new Case(1, "Neurosciences");
    Terrain terrain = new Terrain(); // terrain fictif
    joueur1 = new Joueur("Alice", terrain);
    joueur2 = new Joueur("Bob", terrain);
}


    @Test
    public void testGetNumCase() {
        // Vérifie que le numéro de la case est bien celui attendu
        assertEquals(1, case1.getNumCase(), "Le numéro de la case devrait être 1");
    }

    @Test
    public void testGetTheme() {
        // Vérifie que le thème est bien stocké
        assertEquals("Neurosciences", case1.getTheme(), "Le thème de la case devrait être 'Neurosciences'");
    }

    @Test
    public void testAddJoueur() {
        // Ajoute un joueur et vérifie qu'il est bien présent dans le toString
        case1.addJoueur(joueur1);
        String result = case1.toString();
        assertTrue(result.contains("Alice"), "Le joueur 'Alice' devrait apparaître dans la case");
    }

    @Test
    public void testSuppJoueur() {
        // Ajoute deux joueurs, puis en supprime un
        case1.addJoueur(joueur1);
        case1.addJoueur(joueur2);
        case1.suppJoueur(joueur1);
        String result = case1.toString();
        assertFalse(result.contains("Alice"), "Le joueur 'Alice' ne devrait plus apparaître");
        assertTrue(result.contains("Bob"), "Le joueur 'Bob' devrait toujours être présent");
    }

    @Test
    public void testToStringVide() {
        // Vérifie que le toString retourne "[_]" si la case est vide
        assertEquals("[_]", case1.toString(), "Une case vide doit afficher [_]");
    }

    @Test
    public void testToStringCaseDepart() {
        // Vérifie l'affichage de la case de départ sans joueur
        Case caseDepart = new Case(0, "Départ");
        assertEquals("[D]", caseDepart.toString(), "La case de départ vide doit afficher [D]");

        // Ajoute un joueur sur la case de départ
        caseDepart.addJoueur(joueur1);
        String result = caseDepart.toString();
        assertTrue(result.startsWith("[D:"), "La case de départ avec joueurs doit commencer par [D:");
        assertTrue(result.contains("Alice"), "Le joueur 'Alice' doit apparaître sur la case de départ");
    }
}
