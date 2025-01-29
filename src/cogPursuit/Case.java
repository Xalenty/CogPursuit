/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

/**
 *
 * @author ztsuk
 */
public class Case {
   
    private int numCase = -1; // id de la case 
    private int nbJoueur = 0; //nombre de joueur sur la case

    public Case(int i) {
        numCase = i;
    }

    public int getNumCase() { // permet de recuperer "l'id" de la case
        return this.numCase;
    }
    
    
    public void addJoueur(){ // ajoute a joueur dans la case 
        
        this.nbJoueur +=1;
    }
    public void suppJoueur(){ // retire 1 joueur de la case
        
        this.nbJoueur -=1;
    }
    
    @Override
    public String toString(){
        
        if (nbJoueur>0){ //si il y a des joueurs
            
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
