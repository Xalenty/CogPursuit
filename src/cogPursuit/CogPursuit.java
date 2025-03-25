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

    public Terrain getTerrain() {
        return this.terrain;
    }
    
    public ArrayList<Joueur> getListeJoueurs() {
        return this.listeJ;
    }
    

    /**
     * @param args the command line arguments
     */
    public CogPursuit() {
        terrain = new Terrain();
        System.out.println("Bienvenue sur CogPursuit!");
    }

    public CogPursuit(String pseudo) {
        this.terrain = new Terrain();
        this.listeJ = new ArrayList<>();
        this.listeQ = new ArrayList<>();
    
        Joueur joueur = new Joueur(pseudo, terrain);
        joueur.deplacement(-1, terrain);
        this.listeJ.add(joueur);
    
        System.out.println("Bienvenue " + pseudo + " sur CogPursuit !");
    }
    

    public void start() { // demande et initialise le/s joueur/s
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
     * @param args the command line arguments
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
                    System.out.println("\n " + this.listeJ.get(i) + " a Gagné !\n");
                
                    System.out.println("Classement final :");
                    for (Joueur joueur : listeJ) {
                        System.out.println("- " + joueur + " : " + joueur.getScore() + " points");
                    }
                
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
        Case temp = t.getCase(j.getPos());
    
        // sélection de la difficulté
        boolean selection = false;
    
        while (!selection) {
            System.out.println("Choisir une difficulté :\n 1 - Facile\n 2 - Moyenne\n 3 - Difficile");
            if (scan.hasNextInt()) {
                entreeJ = scan.nextInt();
                if (entreeJ >= 1 && entreeJ <= 3) {
                    selection = true;
                } else {
                    System.out.println("Degré de difficulté incorrect.");
                }
            } else {
                System.out.println("Type de difficulté incorrect.");
                scan.next(); // évite la boucle infinie
            }
        }
    
        // Création de la question
        Question q = new Question(entreeJ, temp.getTheme());
        listeQ.add(q);
    
        // sélection de la réponse
        selection = false;
        while (!selection) {
            System.out.println(q);
    
            if (scan.hasNextInt()) {
                entreeJ = scan.nextInt();
    
                boolean bonneReponse = q.verification(entreeJ);
    
                // Ajout du score via la méthode personnalisée
                j.ajouterScoreSelonDifficulte(bonneReponse, q.getDiff());
    
                // Déplacement
                if (bonneReponse) {
                    j.deplacement(q.getDiff(), t);
                    System.out.println("Bonne réponse !");
                } else {
                    j.deplacement(1 - q.getDiff(), t);
                    System.out.println("Mauvaise réponse !");
                }
    
                // Affichage du score actuel
                System.out.println("Score de " + j + " : " + j.getScore() + " points\n");
    
                return;
    
            } else {
                System.out.println("Type de réponse incorrecte.");
                scan.next();
            }
        }
    }    
}
