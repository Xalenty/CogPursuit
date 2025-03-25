/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Représente une question du jeu avec son intitulé, ses réponses et son niveau de difficulté.
 * Une question appartient à un thème spécifique et a une réponse correcte.
 * 
 * Les données des questions sont récupérées depuis une base de données via ConnexionBdd.
 * 
 * @author ztsuk
 */
public class Question {

    Dictionary<Integer, String> reponses = new Hashtable<>();
    private String intituler = "";
    private int indiceSol = 0;
    private String theme = "";
    private int diff = 0; // difficulté de la question

    /**
     * Constructeur de la classe Question.
     * Récupère une question depuis la base de données en fonction du thème et du niveau de difficulté.
     * 
     * @param diffi Niveau de difficulté de la question (1 = Facile, 2 = Moyenne, 3 = Difficile).
     * @param theme Thème de la question.
     */
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

    /**
     * Retourne l'intitulé de la question.
     * 
     * @return L'intitulé de la question.
     */
    public String getIntituler() {
        return this.intituler;
    }

    /**
     * Retourne l'indice de la réponse correcte.
     * 
     * @return L'indice de la réponse correcte (1 à 4).
     */
    public int getIndiceSol() {
        return this.indiceSol;
    }

    /**
     * Retourne le thème de la question.
     * 
     * @return Le thème de la question.
     */
    public String getTheme() {
        return this.theme;
    }

    /**
     * Vérifie si la réponse donnée par le joueur est correcte.
     * 
     * @param selection L'indice de la réponse sélectionnée par le joueur.
     * @return true si la réponse est correcte, false sinon.
     */
    public boolean verification(int selection) {
        if (selection == indiceSol) {
            System.out.println("Bonne Réponse!\n__\n");
            return true;
        }
        System.out.println("Mauvaise Réponse!\n__\n");
        return false;
    }

    /**
     * Initialise une question en fonction de la difficulté choisie.
     * 
     * @param entreeJ Niveau de difficulté choisi (1 = Facile, 2 = Moyenne, 3 = Difficile).
     * @param theme Thème de la question.
     * @return true si la question est correctement initialisée, false sinon.
     */
    public boolean getQuestion(int entreeJ, String theme) {

        switch (entreeJ) {
            case 1: // Facile
                this.intituler = "facile"; 
                this.diff = 1;
                return true;
            case 2: // Moyenne
                this.intituler = "normal";
                this.diff = 2;
                return true;
            case 3: // Difficile
                this.intituler = "difficile";
                this.diff = 3;
                return true;
            default:
                return false; // Si le degré de difficulté est incorrect
        }
    }

    /**
     * Convertit une réponse sous forme de lettre (A, B, C, D) en un indice numérique (1, 2, 3, 4).
     * 
     * @param instring Lettre correspondant à la réponse correcte.
     * @return L'indice numérique de la réponse correcte.
     */
    private int convertisseurIndice(String instring) {
        int inint = 0;
        
        switch (instring) {
            case "A":
                inint = 1;
                break;
            case "B":
                inint = 2;
                break;
            case "C":
                inint = 3;
                break;
            case "D":
                inint = 4;
                break;
            default:
                throw new AssertionError();
        }
        
        return inint;
    }

    /**
     * Retourne le nom correspondant à un niveau de difficulté.
     * 
     * @param d Niveau de difficulté (1, 2 ou 3).
     * @return Le nom de la difficulté sous forme de texte.
     */
    private String getNomDifficulte(int d) {
        return switch (d) {
            case 1 -> "Facile";
            case 2 -> "Moyenne";
            case 3 -> "Difficile";
            default -> "Inconnue";
        };
    }

    /**
     * Retourne une représentation textuelle de la question, incluant son thème, sa difficulté et les réponses possibles.
     * 
     * @return Une chaîne de caractères décrivant la question et ses réponses.
     */
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

    /**
     * Retourne le niveau de difficulté de la question.
     * 
     * @return Le niveau de difficulté de la question.
     */
    public int getDiff() {
        return this.diff;
    }

}
