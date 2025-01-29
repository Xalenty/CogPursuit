/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

/**
 *
 * @author ztsuk
 */
public class Joueur implements Parametre{
    private String pseudo = "";
    private int score = 0;
    private int pos= -1; //permet de connaitre sur quel case le joueur est

    public Joueur(String p){
        this.pseudo = p;
        this.pos = 0; //origine de la carte
    }
    
    //score__
    public void addScore(int n){
         this.score += n;
    }
    
    
    //position__
    public void setPos(int idCase){
    
        this.pos = idCase;
    }
    
    public int getPos(){
        
        return this.pos;
    }
    
    //deplacement__
     public void deplacement(int direction, Terrain t){// direction correspond au resultat de la question {1 ,2 ,3, 0 ,-1 , -1}
        
        int nextPos = this.pos + direction; // donne l'indice de la prochaine case
        
        if(nextPos < tailleMap && nextPos >= 0){ // verifie que la prochaine case est dans le tableau
        t.getCase(this.pos).suppJoueur();
        t.getCase(nextPos).addJoueur();
        this.setPos(nextPos);        
        }
        
        else if(nextPos>tailleMap){ // si on sort du tableau +
            t.getCase(this.pos).suppJoueur();
            t.getCase(tailleMap-1).addJoueur();
            this.setPos(tailleMap-1);
        }
        
        else 
            t.getCase(this.pos).suppJoueur(); //si on sort du tableau -
            t.getCase(0).addJoueur();
            this.setPos(0);
    }
}
