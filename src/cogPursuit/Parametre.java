/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cogPursuit;

/**
 * Interface contenant les paramètres globaux du jeu.
 * Définit la taille du terrain, le nombre maximum de joueurs et la liste des thèmes.
 * 
 * @author ztsuk
 */
public interface Parametre {
    
    /**
     * Taille du terrain (nombre total de cases).
     */
    int tailleMap = 30;

    /**
     * Nombre maximum de joueurs autorisés dans la partie.
     */
    int maxJoueur = 4;

    /**
     * Liste des thèmes des questions du jeu.
     */
    String[] listeTheme = {"Neurosciences", "Perception et Cognition", "Système Nerveux et Physiologie"};

}
