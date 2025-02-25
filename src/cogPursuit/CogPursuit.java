/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cogPursuit;

import java.util.Scanner;

/**
 *
 * @author Utilisateur
 */
public class CogPursuit implements Parametre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Terrain terrain = new Terrain();
        int cTour = 0; 
        Joueur test = new Joueur("Dupont",terrain);
        initSolo(test, terrain);
        System.out.println(terrain);

        System.out.println("Le jeu commence !");

        while (test.getPos() < tailleMap) {
            cTour++;
            System.out.println("Tour : "+ cTour);
            tour(test, terrain);
            System.out.println(terrain);
            
        }
    }

    public static void initSolo(Joueur j, Terrain t) { // initialise 1 seul joueur
        j.deplacement(-1, t);// initialisera le joueur sur la premiere Case du terrain
    }

    public static void tour(Joueur j, Terrain t) {
        int entreeJ = 0;
        Question q = new Question();

        Scanner scan = new Scanner(System.in);
        System.out.println("choisir une difficulter.\n Facile : 1\n Normal : 2\n Difficile : 3\n");
        entreeJ = scan.nextInt();
        q.getQestion(entreeJ);
        System.out.println(q);
        entreeJ = scan.nextInt();

        if (q.verification(entreeJ)) {
            System.out.println(t.getCase(0).listJ.size());

            j.addScore(q.getDiff());//mettre a jour le score
            j.deplacement(q.getDiff(), t); //+1 si facile, +2 si normal, +3 si difficile
            System.out.println(j.getPos());
            System.out.println(t.getCase(0).listJ.size());
            return;
        } else {
            j.deplacement(1 - q.getDiff(), t);
            return;
        }

    }
}
