/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

/**
 *
 * @author ztsuk
 */
public class Question {

    private String intituler = "";
    private String[] mReponses = new String[4];
    private int indiceSolution = -1;
    private int diff = 0; // difficulté

    public Question() {
        mReponses[0] = "oui";
        mReponses[1] = "non";
        mReponses[2] = "non";
        mReponses[3] = "non";

        indiceSolution = 0;
    }

    public String getIntituler() { // pour afficher l'intituler 

        return this.intituler;
    }

    public boolean verification(int selection) { //verifie si la selection (indice de reponse) est la bonne reponse

        if ((selection - 1) == indiceSolution) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getQestion(int entreeJ) { //entreeJ represente la difficultée choisis

        switch (entreeJ) { // renvoie vrai apres avoir recuperer la question
            case 1: //facile
                
                this.intituler = "facile"; // recuperer l'intituler de la bdd
                this.diff=1;
                //charger les reponses 
                return true;
            case 2: //normal
                this.intituler = "normal";
                this.diff=2;

                return true;
            case 3: // difficile
                this.intituler = "difficile";
                this.diff=3;

                return true;
            default:
                return false; // si le degré de difficulté est incorrecte
        }
    
    }
    
    @Override
    public String toString(){
        if(diff <1) return "erreur question non initialisée";
        
        else return this.intituler+" ?\n1:"+mReponses[0]+" 2:"+mReponses[1] +" \n3:"+mReponses[2]+" 4:"+mReponses[3];
    }

    public int getDiff() {
       return this.diff;
    }
    
}
