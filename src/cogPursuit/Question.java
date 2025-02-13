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
    
    public Question() { 
        mReponses[0] = "oui";
        mReponses[1] = "non";
        mReponses[2] = "non";
        mReponses[3] = "non";

        indiceSolution = 0; // car 
    }

    public String getIntituler() { // pour afficher l'intituler 
        
        return this.intituler;
    }

    public boolean verification(int selection) { //verifie si la selection (indice de reponse) est la bonne reponse

        if ((selection-1) == indiceSolution) {
            return true;
        }
        else return false;
    }
}
