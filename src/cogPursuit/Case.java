/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

import java.util.ArrayList;

/**
 * Représente une case sur le plateau de jeu CogPursuit.
 * Chaque case possède un identifiant unique, un thème et une liste de joueurs présents.
 */
public class Case {
   
    /** Identifiant unique de la case. */
    private int numCase = -1; // id de la case 

    /** Liste des joueurs actuellement sur la case. */
    private ArrayList<Joueur> listJ = new ArrayList<Joueur>();

    /** Thème associé à la case. */
    private String theme="";

    /**
     * Constructeur de la classe Case.
     * 
     * @param i Identifiant unique de la case.
     * @param t Thème de la case.
     */
    public Case(int i, String t) {
        this.numCase = i;
        this.theme = t;
    }

    /**
     * Retourne le thème de la case.
     * 
     * @return Le thème sous forme de chaîne de caractères.
     */
    public String getTheme() { // permet de recuperer "l'id" de la case
        return this.theme;
    }

    /**
     * Retourne l'identifiant de la case.
     * 
     * @return L'identifiant de la case.
     */
    public int getNumCase() { // permet de recuperer "l'id" de la case
        return this.numCase;
    }

    /**
     * Ajoute un joueur à la case.
     * 
     * @param j Le joueur à ajouter.
     */
    public void addJoueur(Joueur j){ // ajoute a joueur dans la case 
        this.listJ.add(j);
    }

    /**
     * Supprime un joueur de la case, s'il est présent.
     * 
     * @param j Le joueur à retirer.
     */
    public void suppJoueur(Joueur j){ // retire 1 joueur de la case si il y en a
        this.listJ.remove(j);
    }

    /**
     * Retourne une représentation textuelle de la case.
     * 
     * @return Une chaîne représentant l'état de la case :
     *         - "[D]" si c'est la case de départ et qu'elle est vide.
     *         - "[D: joueur1. joueur2. ...]" si des joueurs sont sur la première case.
     *         - "[joueur1. joueur2. ...]" si des joueurs sont présents sur une autre case.
     *         - "[_]" si la case est vide.
     */
    @Override
    public String toString(){
        if(numCase==0 && this.listJ.size()==0) return "[D]";
        else if (this.listJ.size()>0 && numCase==0){ //si il y a des joueurs su la premiere case
            
            String res = "[D:";
            
            for(int i =0; i<this.listJ.size();i++){
                res+=listJ.get(i) + ". ";
            }
            res +="]";
            return res;
        }
        else if (this.listJ.size()>0){ //si il y a des joueurs sur une case
            
            String res = "[";
            
            for(int i =0; i<this.listJ.size();i++){
                res+=listJ.get(i) + ". ";
            }
            res +="]";
            return res;
        }
        
        else return "[_]"; //si il ni en à pas
    }
}
