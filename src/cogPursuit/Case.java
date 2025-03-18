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
    private ArrayList<Joueur> listJ = new ArrayList<Joueur>();

    public Case(int i) {
        numCase = i;
    }

    public int getNumCase() { // permet de recuperer "l'id" de la case
        return this.numCase;
    }
    
    public void addJoueur(Joueur j){ // ajoute a joueur dans la case 
        this.listJ.add(j);
    }
    public void suppJoueur(Joueur j){ // retire 1 joueur de la case si il y en a
        this.listJ.remove(j);

    }
    
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
