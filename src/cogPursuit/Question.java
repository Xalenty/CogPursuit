/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

/**
 *
 * @author ztsuk
 */
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Question {

    Dictionary<Integer, String> reponses = new Hashtable<>();
    private String intituler = "";
    private int indiceSol = 0;
    private String theme="";

    private int diff = 0; // difficulter

    public Question(String t) {
        theme = t;
        intituler = "c'est dur?";
        //repartition du theme
        reponses.put(1, "Oui");
        reponses.put(2, "Non");
        reponses.put(3, "Non");
        reponses.put(4, "Non");
        indiceSol = 1;

    }

    public String getIntituler() { // pour afficher l'intituler

        return this.intituler;
    }

    public String getTheme() { // pour afficher l'intituler

        return "";//this.theme;
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

    public boolean getQestion(int entreeJ,String theme) { // entreeJ represente la difficultée choisis

        switch (entreeJ) { // renvoie vrai apres avoir recuperer la question
            case 1: // facile
                //recuperer ici la toutes les donnes de la question en fonction du theme
                this.intituler = "facile"; // recuperer l'intituler de la bdd
                this.diff = 1;
                // charger les reponses
                return true;
            case 2: // normal
                this.intituler = "normal";
                this.diff = 2;

                return true;
            case 3: // difficile
                this.intituler = "difficile";
                this.diff = 3;

                return true;
            default:
                return false; // si le degré de difficulté est incorrecte
        }

    }

    @Override
    public String toString() {

        if (diff < 1) {
            return "erreur question non initialisée";
        } else {
            String sortie ="Theme : "+this.theme+"\n"+ this.intituler +"\n";
            String temp ="";
            for (int i = 1; i < reponses.size()+1; i++) {
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
