/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cogPursuit;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Utilisateur
 */
public class CogPursuit implements Parametre {

    Terrain terrain;
    // liste des joueurs
    ArrayList<Joueur> listeJ = new ArrayList<Joueur>();    
    ArrayList<Question> listeQ = new ArrayList<Question>();


    /**
     * @param args the command line arguments
     */
    public CogPursuit() {
        terrain = new Terrain();

        System.out.println("Bienvenue sur CogPursuit!");
        Scanner scan = new Scanner(System.in);

        // faire boucler la demande si enteeJ incorrect
        boolean initialiser = false;
        while (!initialiser) {
            // demande du nb joueur
            System.out.println("Entrez le nombre de joueurs (4 Max):");
            int entreeJ = scan.nextInt();

            if (entreeJ > maxJoueur || entreeJ < 1) {
                System.out.println("Nombres de joueurs incorrect.");
            } else {
                initJoueurs(entreeJ);
                initialiser = true;
            }
        }
    
    }

    /**
     * @param args the command line arguments
     */
    public void start() {
        int cTour = 0;
        System.out.println(this.terrain);

        System.out.println("Le jeu commence !");

        boolean fin = false;
        while (!fin) {
            cTour++;
            for (int i = 0; i < this.listeJ.size(); i++) {

                System.out.println(this.listeJ.get(i) + " à toi de jouer!" + "\nTour : " + cTour);
                tour(this.listeJ.get(i), terrain);
                System.out.println(terrain);

                if (this.listeJ.get(i).fin()) {
                    System.out.println(this.listeJ.get(i) + " a Gagné !");
                    return;
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    private void initJoueurs(int nbJoueur) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < nbJoueur; i++) {
            System.out.println("Entrez le nom du joueurs num " + (i + 1) + " : ");
            String nom = scan.next();

            listeJ.add(new Joueur(nom, terrain));
        }
        for (int i = 0; i < nbJoueur; i++) {// initialisera les joueurs sur la premiere Case du terrain
            listeJ.get(i).deplacement(-1, terrain);
        }
    }

    /**
     * @param args the command line arguments
     */
    public void tour(Joueur j, Terrain t) {
        int entreeJ = 0;
        Scanner scan = new Scanner(System.in);
        Question q = new Question();
        // selection difficulté
        boolean selection = false;
        
        while (!selection) {
            System.out.println("choisir une difficulter.\n Facile : 1\n Normal : 2\n Difficile : 3\n");
            if (scan.hasNextInt()) {
                entreeJ = scan.nextInt();
                if (entreeJ > 3 || entreeJ < 1)
                    System.out.println("degré de difficulté incorrecte.");
                
                else {
                    Case temp =t.getCase(j.getPos()) ; 
                    q.getQestion(entreeJ,temp.getTheme());
                    listeQ.add(q);
                    selection = true;
                }

            } else {
                System.out.println("Type de difficultée incorrecte.");
            }
        }

        // selection reponse
        selection = false;
        while (!selection) {
            System.out.println(q);

            if (scan.hasNextInt()) {
                entreeJ = scan.nextInt();

                if (q.verification(entreeJ)) {

                    j.addScore(q.getDiff());// mettre a jour le score
                    j.deplacement(q.getDiff(), t); // +1 si facile, +2 si normal, +3 si difficile
                    

                    return;

                } else {
                    j.deplacement(1 - q.getDiff(), t);

                    return;
                }

            } else {
                System.out.println("Type de réponse incorrecte.");
                scan.next(); // pour éviter une boucle infinie
            }
        }

    }
}
