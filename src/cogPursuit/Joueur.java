/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

/**
 * Représente un joueur dans le jeu CogPursuit.
 * Chaque joueur possède un pseudo, un score et une position sur le terrain.
 *
 * @author ztsuk
 */
public class Joueur implements Parametre {

    private String pseudo = "";
    private int score = 0;
    private Case pos; // permet de connaitre sur quel case le joueur est

    /**
     * Constructeur de la classe Joueur.
     * Initialise un joueur avec un pseudo et le place à la case de départ.
     * 
     * @param p Le pseudo du joueur.
     * @param t Le terrain sur lequel il évolue.
     */
    public Joueur(String p, Terrain t) {
        this.pseudo = p;
        this.pos = t.getCase(0); // origine de la carte
    }

    /**
     * Constructeur alternatif de la classe Joueur.
     * Initialise un joueur avec un pseudo et un score défini.
     * 
     * @param p Le pseudo du joueur.
     * @param score Le score initial du joueur.
     */
    public Joueur(String p, int score) {
        this.pseudo = p;
        this.score = score; // origine de la carte
    }

    /**
     * Retourne le pseudo du joueur.
     * 
     * @return Le pseudo du joueur.
     */
    @Override
    public String toString() {
        return this.pseudo;
    }

    // score__

    /**
     * Ajoute des points au score du joueur.
     * 
     * @param n Nombre de points à ajouter.
     */
    public void addScore(int n) {
        this.score += n;
    }

    /**
     * Retourne le score actuel du joueur.
     * 
     * @return Le score du joueur.
     */
    public int getScore() {
        return this.score;
    }

    // position__

    /**
     * Modifie la position actuelle du joueur.
     * 
     * @param c La nouvelle case où se trouve le joueur.
     */
    public void setPos(Case c) {
        this.pos = c;
    }

    /**
     * Retourne la position actuelle du joueur sur le terrain.
     * 
     * @return L'indice de la case où se trouve le joueur.
     */
    public int getPos() {
        return this.pos.getNumCase();
    }

    /**
     * Vérifie si le joueur a atteint la fin du plateau.
     * 
     * @return true si le joueur est sur la dernière case, sinon false.
     */
    public boolean fin() {
        if (pos.getNumCase() + 1 == tailleMap)
            return true;
        else
            return false;
    }

    /**
     * Ajoute des points au joueur en fonction de la difficulté de la question.
     * 
     * @param bonneReponse Indique si la réponse est correcte (true) ou incorrecte (false).
     * @param difficulte Niveau de difficulté de la question (1 = Facile, 2 = Moyenne, 3 = Difficile).
     */
    public void ajouterScoreSelonDifficulte(boolean bonneReponse, int difficulte) {
        if (!bonneReponse) return; // Pas de points si mauvaise réponse
    
        switch (difficulte) {
            case 1: // Facile
                this.addScore(100);
                break;
            case 2: // Moyenne
                this.addScore(180);
                break;
            case 3: // Difficile
                this.addScore(250);
                break;
            default:
                System.out.println("Difficulté inconnue, aucun score ajouté.");
        }
    }

    // deplacement__

    /**
     * Déplace le joueur sur le terrain en fonction du résultat d'une question.
     * La direction dépend du score obtenu suite à la réponse :
     * - 1, 2 ou 3 cases en avant si la réponse est correcte.
     * - 0, -1 ou -2 cases en arrière si la réponse est incorrecte.
     * 
     * @param direction Le nombre de cases à avancer ou reculer.
     * @param t Le terrain sur lequel le joueur évolue.
     */
    public void deplacement(int direction, Terrain t) {

        int nextPos = this.pos.getNumCase() + direction; // donne l'indice de la prochaine case

        if (nextPos < tailleMap && nextPos >= 0) { // verifie que la prochaine case est dans le tableau

            this.pos.suppJoueur(this); // la case doit m'oublier
            t.getCase(nextPos).addJoueur(this);
            this.pos = t.getCase(nextPos);

        } else if (nextPos > tailleMap - 1) { // si on sort du tableau +

            this.pos.suppJoueur(this);
            t.getCase(tailleMap - 1).addJoueur(this);
            this.setPos(t.getCase(tailleMap - 1));

        } else {

            this.pos.suppJoueur(this);
            t.getCase(0).addJoueur(this);
            this.setPos(t.getCase(0));
        }

    }
}
