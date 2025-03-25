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
        
        this.diff = diffi;
        this.theme = theme;
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
        return this.theme;
    }

    public boolean verification(int selection) { // verifie si la selection (indice de reponse) est la bonne reponse
        if (selection == indiceSol) {
            System.out.println("Bonne Réponse!\n__\n");
            return true;
        }
        System.out.println("Mauvaise Réponse!\n__\n");
        return false;
    }

    public boolean getQuestion(int entreeJ, String theme) { // entreeJ represente la difficultée choisis

        switch (entreeJ) { // renvoie vrai apres avoir recuperer la question
            case 1: // facile
                // recuperer ici la toutes les donnes de la question en fonction du theme
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

    private String getNomDifficulte(int d) {
        return switch (d) {
            case 1 -> "Facile";
            case 2 -> "Moyenne";
            case 3 -> "Difficile";
            default -> "Inconnue";
        };
    }
    

    @Override
    public String toString() {

        if (diff < 1) {
            return "erreur question non initialisée";
        } else {
            String sortie = "Thème : " + this.theme + "\n";
            sortie += "Difficulté : " + getNomDifficulte(this.diff) + "\n";
            sortie += "Question : " + this.intituler + "\n";

            for (int i = 1; i <= reponses.size(); i++) {
                String temp = reponses.get(i);
                sortie += i + " : " + temp + "\n";
            }

            return sortie;
        }
    }

    

    public int getDiff() {
        return this.diff;
    }

}

