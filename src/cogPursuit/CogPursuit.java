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
        
        System.out.println(terrain);
        
        terrain.getCase(0).addJoueur();
        terrain.getCase(0).addJoueur();


       test.setPos(0);
       terrain.getCase(0).suppJoueur();

        System.out.println(terrain);
    
    }   
    
    public static void deplacement(int direction, Joueur j, Terrain t){// direction correspond au resultat de la question {1 ,2 ,3, 0 ,-1 , -1}
        
        int nextPos = j.getPos() + direction; // donne l'indice de la prochaine case
        
        if(nextPos < tailleMap && nextPos >= 0){ // verifie que la prochaine case est dans le tableau
        t.getCase(j.getPos()).suppJoueur();
        t.getCase(nextPos).addJoueur();
        j.setPos(nextPos);        
        }
        
        else if(nextPos>tailleMap){ // si on sort 
            t.getCase(j.getPos()).suppJoueur();
            t.getCase(tailleMap-1).addJoueur();
            j.setPos(tailleMap-1);
        }
        
        else 
            t.getCase(j.getPos()).suppJoueur();
            t.getCase(0).addJoueur();
            j.setPos(0);
    }
    
}
