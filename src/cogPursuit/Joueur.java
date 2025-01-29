/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

/**
 *
 * @author ztsuk
 */
public class Joueur {
    private String pseudo = "";
    private int score = 0;
    private int pos=-1; //permet de connaitre sur quel case le joueur est

    public Joueur(String p){
        this.pseudo = p;
    
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
}
