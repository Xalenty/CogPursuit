package cogPursuit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParametreTest {

    @Test
    public void testParametreConstants() {
        // Vérifie les constantes de l'interface Parametre
        assertEquals(30, Parametre.tailleMap, "La taille de la carte devrait être 30");
        assertEquals(4, Parametre.maxJoueur, "Le nombre maximal de joueurs devrait être 4");
        assertArrayEquals(
            new String[]{"Neurosciences", "Perception et Cognition", "Système Nerveux et Physiologie"},
            Parametre.listeTheme,
            "La liste des thèmes devrait correspondre aux thèmes définis"
        );
    }
}
