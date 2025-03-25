/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cogPursuit;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principale du jeu CogPursuit.
 * Gère l'initialisation du jeu, la gestion des joueurs et des tours.
 * Implémente l'interface Parametre.
 * 
 * Le jeu consiste à répondre à des questions de différentes difficultés
 * pour avancer sur un plateau. Le premier joueur à atteindre la fin gagne.
 * 
 * @author Utilisateur
 */
public class CogPursuit implements Parametre {

    /** Terrain de jeu. */
    Terrain terrain;
    
    /** Liste des joueurs participant au jeu. */
    ArrayList<Joueur> listeJ = new ArrayList<Joueur>();
    
    /** Liste des questions posées pendant la partie. */
    ArrayList<Question> listeQ = new ArrayList<Question>();

    /**
     * Constructeur de la classe CogPursuit.
     * Initialise le terrain et affiche un message de bienvenue.
     */
    public CogPursuit() {
        terrain = new Terrain();
        System.out.println("Bienvenue sur CogPursuit!");
    }

    /**
     * Démarre la configuration du jeu en demandant le nombre de joueurs
     * et en les initialisant.
     */
    public void start() {
        Scanner scan = new Scanner(System.in);
        boolean initialiser = false;
        
        while (!initialiser) {
            System.out.println("Entrez le nombre de joueurs (4 Max):");
            int entreeJ = scan.nextInt();
            
            if (entreeJ > maxJoueur || entreeJ < 1) {
                System.out.println("Nombre de joueurs incorrect.");
            } else {
                initJoueurs(entreeJ);
                initialiser = true;
            }
        }
    }

   /*
   public boolean setjoueur(int x){ // demande et initialise le/s  i joueur/s
            // demande du nb joueur

            if (x > maxJoueur || x < 1) {
                System.out.println("Nombres de joueurs incorrect.");
            return true;

            } else {
                initJoueurs(x);
                return true;
            }
   }
     */

    /**
     * Gère le déroulement d'une partie jusqu'à la victoire d'un joueur.
     */
    public void partie() {
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
     * Initialise les joueurs en demandant leurs noms et les place sur le terrain.
     * 
     * @param nbJoueur Nombre de joueurs à initialiser
     */
    private void initJoueurs(int nbJoueur) {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < nbJoueur; i++) {
            System.out.println("Entrez le nom du joueur num " + (i + 1) + " : ");
            String nom = scan.next();
            listeJ.add(new Joueur(nom, terrain));
        }
        for (int i = 0; i < nbJoueur; i++) {
            listeJ.get(i).deplacement(-1, terrain);
        }
    }

    /**
     * Gère le tour d'un joueur en lui posant une question et en mettant à jour son état.
     * 
     * @param j Joueur en train de jouer
     * @param t Terrain sur lequel le joueur évolue
     */
    public void tour(Joueur j, Terrain t) {
        int entreeJ = 0;
        Scanner scan = new Scanner(System.in);
        Case temp = t.getCase(j.getPos());

        // Sélection de la difficulté de la question
        boolean selection = false;
        while (!selection) {
            System.out.println("Choisir une difficulté.\n Facile : 1\n Normal : 2\n Difficile : 3\n");
            if (scan.hasNextInt()) {
                entreeJ = scan.nextInt();
                if (entreeJ > 3 || entreeJ < 1) {
                    System.out.println("Degré de difficulté incorrect.");
                } else {
                    selection = true;
                }
            } else {
                System.out.println("Type de difficulté incorrect.");
                scan.next(); // Pour éviter une boucle infinie
            }
        }

        // Création de la question et ajout à la liste des questions posées
        Question q = new Question(entreeJ, temp.getTheme());
        listeQ.add(q);
        
        // Sélection de la réponse
        selection = false;
        while (!selection) {
            System.out.println(q);
            if (scan.hasNextInt()) {
                entreeJ = scan.nextInt();
                if (q.verification(entreeJ)) {
                    j.addScore(q.getDiff());
                    j.deplacement(q.getDiff(), t);
                    return;
                } else {
                    j.deplacement(1 - q.getDiff(), t);
                    return;
                }
            } else {
                System.out.println("Type de réponse incorrecte.");
                scan.next(); // Pour éviter une boucle infinie
            }
        }
    }
}
