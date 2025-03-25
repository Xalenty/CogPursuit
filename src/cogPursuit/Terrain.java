/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

/**
 * Représente le terrain du jeu sous forme d'un tableau de cases.
 * Chaque case est associée à un thème, et la disposition des thèmes est
 * déterminée par la construction du terrain.
 * 
 * @author ztsuk
 */
public class Terrain implements Parametre {

    private Case[] map = new Case[tailleMap];

    /**
     * Constructeur de la classe Terrain.
     * Initialise le terrain en créant des cases et en leur attribuant des thèmes.
     * La répartition des thèmes est basée sur une découpe prédéfinie.
     */
    public Terrain() {
        int indiceTheme = 0;
        int decoupe = 10; // Définit manuellement le nombre de questions par thème (ici, 10 par thème)
        int j = 0;

        for (int i = 0; i < tailleMap; i++) {
            if ((i + 1) % decoupe == 0) {
                this.map[i] = new Case(i, listeTheme[indiceTheme]);
                indiceTheme++;
            } else if ((i + 1) % decoupe != 0) {
                this.map[i] = new Case(i, listeTheme[indiceTheme]);
            } else {
                System.out.println("TailleMap trop petite pour le nombre de questions par thème.");
            }
        }
    }

    /**
     * Affiche les thèmes de chaque case du terrain.
     * 
     * @return Une chaîne de caractères listant les cases et leurs thèmes.
     */
    public String afficherThemes() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tailleMap; i++) {
            sb.append("Case ").append(i).append(" → ").append(map[i].getTheme()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Retourne la case située à un indice donné.
     * 
     * @param indice L'indice de la case dans le tableau.
     * @return La case correspondant à l'indice ou null si l'indice est invalide.
     */
    public Case getCase(int indice) {
        if (indice >= 0 && indice < tailleMap) {
            return this.map[indice];
        }
        return null;
    }

    /**
     * Retourne une représentation textuelle du terrain,
     * avec un saut de ligne toutes les 10 cases pour améliorer la lisibilité.
     * 
     * @return Une chaîne de caractères représentant le terrain.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tailleMap; i++) {
            sb.append(map[i].toString());

            // Saut de ligne toutes les 10 cases pour une meilleure lisibilité
            if ((i + 1) % 10 == 0) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

}
