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

    public void jouer() {
        int direction = BAS;
        while (direction != 0) {
            Random ra = new Random();
            direction = ra.nextInt(DROITE - GAUCHE + 1) + GAUCHE;
        }
        boolean deplace = super.grilleActuelle.lanceurDeplacerCases(direction);
    }

}
