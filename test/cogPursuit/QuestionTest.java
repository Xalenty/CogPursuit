package cogPursuit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    private Question questionFacile;
    private Question questionMoyenne;
    private Question questionDifficile;
    private ConnexionBdd mockBdd;  // On va utiliser un mock de la connexion à la BDD

    @BeforeEach
    public void setUp() {
        // Simuler une connexion à la base de données et récupérer des questions.
        mockBdd = new ConnexionBdd();
        questionFacile = new Question(1, "Neurosciences");
        questionMoyenne = new Question(2, "Neurosciences");
        questionDifficile = new Question(3, "Neurosciences");
    }

    @Test
    public void testGetIntituler() {
        // Vérifie si l'intitulé de la question est récupéré correctement.
        assertNotNull(questionFacile.getIntituler(), "L'intitulé de la question ne doit pas être nul");
    }

    @Test
    public void testVerificationBonneReponse() {
        int bonneReponse = questionFacile.getIndiceSol();  // utilise la vraie bonne réponse
        boolean result = questionFacile.verification(bonneReponse);
        assertTrue(result, "La réponse devrait être correcte.");
    }


    @Test
    public void testVerificationMauvaiseReponse() {
        int bonneReponse = questionFacile.getIndiceSol();
        int mauvaiseReponse = (bonneReponse % 4) + 1;  // ex : si 1, devient 2 (toujours différent)

        if (mauvaiseReponse == bonneReponse) {
            mauvaiseReponse = (mauvaiseReponse % 4) + 1;  // pour forcer la différence
        }

        boolean result = questionFacile.verification(mauvaiseReponse);
        assertFalse(result, "La réponse devrait être incorrecte.");
    }


    @Test
    public void testGetDiff() {
        // Vérifie si la difficulté est correctement définie lors de la création de la question.
        assertEquals(1, questionFacile.getDiff(), "La difficulté de la question devrait être '1' pour une question facile");
        assertEquals(2, questionMoyenne.getDiff(), "La difficulté de la question devrait être '2' pour une question moyenne");
        assertEquals(3, questionDifficile.getDiff(), "La difficulté de la question devrait être '3' pour une question difficile");
    }

    @Test
    public void testToString() {
        // Vérifie la représentation textuelle de la question.
        String result = questionFacile.toString();
        System.out.println(result);  // Affiche le résultat pour vérification manuelle
        assertTrue(result.contains("Thème"), "La chaîne retournée doit contenir le thème.");
        assertTrue(result.contains("Facile"), "La chaîne retournée doit contenir le niveau de difficulté.");
    }

    @Test
    public void testGetTheme() {
        // Vérifie que le thème de la question est correctement récupéré.
        String theme = questionFacile.getTheme();
        assertEquals("Neurosciences", theme, "Le thème de la question devrait être 'Neurosciences'");
    }
}
