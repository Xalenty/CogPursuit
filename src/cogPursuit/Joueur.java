/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

/**
 *
 * @author ztsuk
 */
public class Joueur implements Parametre {

    private String pseudo = "";
    private int score = 0;
    private Case pos; // permet de connaitre sur quel case le joueur est

    public Joueur(String p, Terrain t) {
        this.pseudo = p;
        this.pos = t.getCase(0); // origine de la carte
    }

    @Override
    public String toString() {
        return this.pseudo;
    }

    // score__
    public void addScore(int n) {
        this.score += n;
    }

    public int getScore() {
        return this.score;
    }

    // position__
    public void setPos(Case c) {

        this.pos = c;
    }

    public int getPos() {

        return this.pos.getNumCase();
    }

    public boolean fin() {
        if (pos.getNumCase() + 1 == tailleMap)
            return true;
        else
            return false;
    }

    // deplacement__
    public void deplacement(int direction, Terrain t) {// direction correspond au resultat de la question {1 ,2 ,3, 0
                                                       // ,-1 , -1}

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
