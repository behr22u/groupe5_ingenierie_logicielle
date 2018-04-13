/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Random;

/**
 *
 * @author leath
 */
public class RandomPlayer extends Joueur implements Parametres {

    public RandomPlayer() {
        super();
    }
    public RandomPlayer(Grille g){
        super(g);
    }

    @Override
    public void jouer() {
        boolean deplace = false;
        while (!deplace && !this.grilleActuelle.partieFinie()){
            int direction = 0;
            while (direction == 0) {
                Random ra = new Random();
                direction = ra.nextInt(DROITE - GAUCHE + 1) + GAUCHE;
            }
            deplace = super.jouer(direction);
            System.out.println(deplace);
        }
        boolean b = super.grilleActuelle.nouvelleCase();
        if (!b) super.grilleActuelle.gameOver();
    }

}
