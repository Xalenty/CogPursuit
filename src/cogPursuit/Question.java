/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

/**
 *
 * @author ztsuk
 */
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Question {

    Dictionary<Integer, String> reponses = new Hashtable<>();
    private String intituler = "";
    private int indiceSol = 0;
    private String theme = "";
    private int diff = 0; // difficulter

    public Question(int diffi, String theme) {
        
        ConnexionBdd con = new ConnexionBdd();
        ArrayList<String> qDetails = con.recuperationQuestion(diffi, theme);
        
        diff = diffi;
        theme = theme;
        intituler = qDetails.get(1);
        reponses.put(1, qDetails.get(2));
        reponses.put(2, qDetails.get(3));
        reponses.put(3, qDetails.get(4));
        reponses.put(4, qDetails.get(5));
        
        indiceSol = convertisseurIndice(qDetails.get(6));
    }

    public String getIntituler() { // pour afficher l'intituler

        return this.intituler;
    }

    public String getTheme() { // pour afficher le theme

        return this.theme;
    }

    public boolean verification(int selection) { // verifie si la selection (indice de reponse) est la bonne reponse
        if (selection == indiceSol) {
            System.out.println("Bonne Réponse!\n__\n");
            return true;
        } else {
            System.out.println("Mauvaise Réponse!\n__\n");
            return false;
        }
    }  
    
    private int convertisseurIndice(String instring){
        int inint = 0;
        
        switch (instring) {
            case "A":
                inint=1;
                break;
            case "B":
                inint=2;
                break;
            case "C":
                inint=3;
                break;
            case "D":
                inint=4;
                break;
            default:
                throw new AssertionError();
        }
        
        return inint;
    }

    @Override
    public String toString() {

        if (diff < 1) {
            return "erreur question non initialisée";
        } else {
            String sortie = "Theme : " + this.theme + "\n" + this.intituler + "\n";
            String temp = "";
            for (int i = 1; i < reponses.size() + 1; i++) {
                temp = reponses.get(i);
                sortie += i + " : " + temp + "\n";
            }

            return sortie;
        }
    }

    public int getDiff() {
        return this.diff;
    }

}

