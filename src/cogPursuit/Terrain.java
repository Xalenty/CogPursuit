/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

public class Terrain implements Parametre {

    private Case[] map = new Case[tailleMap];

    public Terrain() {
        int indiceTheme = 0;
        int decoupe = 10; //defini manuellement le nombre de question sur un meme theme ici 10 par theme
        int j = 0;

        for (int i = 0; i < tailleMap; i++) {
            if ((i+1)% decoupe == 0) {
                
                this.map[i] = new Case(i, listeTheme[indiceTheme]);
                indiceTheme++;
            }
            else if((i+1)% decoupe != 0){
                this.map[i] = new Case(i, listeTheme[indiceTheme]);
            }
             else {
                System.out.println("TailleMap trop petite pour le nb de question par theme.");
            }
        }
    }

    public String afficherThemes() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tailleMap; i++) {
            sb.append("Case ").append(i).append(" → ").append(map[i].getTheme()).append("\n");
        }
        return sb.toString();
    }
    
    

    public Case getCase(int indice) {
        if (indice >= 0 && indice < tailleMap) {
            return this.map[indice];
        }
        return null;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tailleMap; i++) {
            sb.append(map[i].toString());

            // Saut de ligne toutes les 10 cases pour une meilleure lisibilité
            if ((i + 1) % 10 == 0) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

}
