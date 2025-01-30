/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cogPursuit;

/**
 *
 * @author Utilisateur
 */
public class CogPursuit implements Parametre{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Terrain terrain = new Terrain();
        Joueur test = new Joueur("Dupont");
        initSolo(test, terrain);
        System.out.println(terrain);
        
        
        System.out.println("Le jeu commence !");
       /* while(test.getPos()<tailleMap){
            tour(test,terrain);
        }*/
    }   
  
    public static void initSolo(Joueur j, Terrain t){ // initialise 1 seul joueur
        j.deplacement(-1, t);// initialisera le joueur sur la premiere Case du terrain
    }
    
    public static void tour(Joueur j, Terrain t){
        int entreeJ = 0;
        System.out.println("choisir une difficulter.\n Facile : 1\n Normal : 2\n Difficile : 3");
        
        
        /*switch(entreeJ) 
                case 1: */
    
    }
}
