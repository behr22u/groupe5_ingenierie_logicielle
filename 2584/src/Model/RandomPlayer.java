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
        System.out.println("iciiiiiiiiiii");
        boolean deplace = false;
        while (!deplace){
            int direction = 0;
            while (direction == 0) {
                Random ra = new Random();
                direction = ra.nextInt(DROITE - GAUCHE + 1) + GAUCHE;
                System.out.println("la direction aléatoire : " + direction); 
            }
            deplace = super.grilleActuelle.lanceurDeplacerCases(direction);
            System.out.println(deplace);
        }
        boolean b = super.grilleActuelle.nouvelleCase();
        if (!b) super.grilleActuelle.gameOver();
    }

}
