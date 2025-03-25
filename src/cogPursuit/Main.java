/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

/**
 * Classe principale permettant de lancer l'application CogPursuit.
 * Elle initialise la connexion à la base de données, récupère une question
 * et démarre le jeu.
 * 
 * @author ztsuk
 */
public class Main {
    
    /**
     * Point d'entrée principal de l'application.
     * 
     * @param args Arguments de la ligne de commande (non utilisés ici)
     */
    public static void main(String[] args) {
        // Initialisation de la connexion à la base de données
        ConnexionBdd ddb = new ConnexionBdd();
        
        // Récupération d'une question de la base de données
        System.out.println(ddb.recuperationQuestion(1, "neurosciences"));
        
        // Initialisation et démarrage du jeu
        CogPursuit jeu = new CogPursuit();
        jeu.start();
        jeu.partie();
    }
}

