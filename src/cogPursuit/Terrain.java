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
    

    public Case getCase(int indice) {
        return this.map[indice];
    }

    @Override
    public String toString() {
        for (int i = 0; i < tailleMap; i++) {
            if (i != 0 && (i + 1) % 10 == 0) {
                System.out.println(map[i].toString());
            } else {
                System.out.print(map[i].toString());
            }
        }
        return "";
    }
}
