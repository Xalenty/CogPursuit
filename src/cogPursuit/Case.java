/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;
import java.util.ArrayList;
/**
 *
 * @author ztsuk
 */
public class Case {
   
    private int numCase = -1; // id de la case 
    private int nbJoueur = 0; //nombre de joueur sur la case
    public ArrayList<Joueur> listJ = new ArrayList<Joueur>();

    public Case(int i) {
        numCase = i;
    }

    public int getNumCase() { // permet de recuperer "l'id" de la case
        return this.numCase;
    }
    
    public void addJoueur(Joueur j){ // ajoute a joueur dans la case 
        this.listJ.add(j);
        this.nbJoueur = listJ.size();
    }
    public void suppJoueur(Joueur j){ // retire 1 joueur de la case si il y en a
        this.listJ.remove(j);
        this.nbJoueur = listJ.size();

    }
    
    @Override
    public String toString(){
        if(numCase==0 && nbJoueur==0) return "[D]";
        else if (nbJoueur>0 && numCase==0){ //si il y a des joueurs su la premiere case
            
            String res = "[D:";
            
            for(int i =0; i<nbJoueur;i++){
                res+="X";
            }
            res +="]";
            return res;
        }
        else if (nbJoueur>0){ //si il y a des joueurs sur une case
            
            String res = "[";
            
            for(int i =0; i<nbJoueur;i++){
                res+="X";
            }
            res +="]";
            return res;
        }
        
        else return "[_]"; //si il ni en à pas
    }
}
