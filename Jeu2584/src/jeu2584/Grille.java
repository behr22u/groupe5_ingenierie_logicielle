/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Louis
 */
    public class Grille implements Parametres {

    //hash set qui contient toutes les cases de la grille
    // ne fonctionne pas parce qu'il y a erreur dans la classe case, pas complète    
    private final HashSet<Case> grille;

    private int valMax = 0; 

    private boolean deplacement;

/**
 * Constructeur d'une grille, on lui donne un hashset qui contiendra les différentes cases
 */
    public Grille(){
        this.grille = new HashSet<>();
    }


    @Override
    public String toString(){

        int[][] tab = new int[TAILLE][TAILLE];

        for(Case c : this.grille){
            tab[c.getY()][c.getX()] = c.getValeur();
        }
        String tmp = "";

        for(int i = 0; i < tab.length; i++){
            tmp += Arrays.toString(tab[i]) + "\n";
        }
        return tmp;
    }

    /**
     * fonction victory appellée quand le joueur a fini le jeu
     * ecrit un message pur prevenir le joueur qu'il a gagné et quitte l'application
     */
    public void victory() {
        System.out.println("Bravo ! Vous avez atteint " + this.valMax);
        
        //on donne 0 en param à exit car l'application s'est bien déroulée
        System.exit(0);
    }

    /**
     * fonction gameOver appellée quand le joueur a perdu sa partie
     * ecrit un message pour prevenir le joueur qu'il a perdu et quitte l'application
     */
    public void gameOver() {
        System.out.println("La partie est finie. Votre score est " + this.valMax);
        
        //on donne 1 en param à exit car il y a eu un pb dans l'application --> le joueur a perdu
        System.exit(1);
    }





}
