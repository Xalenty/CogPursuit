/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

public class Terrain implements Parametre {

    private Case[] map = new Case[tailleMap];

    public Terrain() {
        for (int i = 0; i < tailleMap; i++) {

            this.map[i] = new Case(i);
        }
    }
    public Case getCase(int indice){
        return this.map[indice];
    }

    @Override
    public String toString() {
        for (int i = 0; i < tailleMap; i++) {
            if (i!=0 && (i+1)% 10 == 0)
                System.out.println(map[i].toString());
            else
                System.out.print(map[i].toString());
        }
        return "";
    }
}
