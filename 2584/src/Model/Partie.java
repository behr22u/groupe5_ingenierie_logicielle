/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.HashSet;



/**
 *
 * @author leath
 */
public class Partie implements Parametres{
    private Grille grilles[];
    
    public Partie(){
        Grille grilles[] = new Grille[NOMBREDEJOUEURS];
    }

    public Grille[] getGrilles() {
        return grilles;
    }
    public HashSet<Case> getHashGrille(int i){
        return grilles[i].getGrille();
    }

    public void setGrilles(Grille[] grilles) {
        this.grilles = grilles;
    }
    /// renvoie la grille de l'indice passé en paramètre, et si cette grille est trop grande renvoie une nouvelle grille vide
    public Grille getG(int i) {
        if (i<NOMBREDEJOUEURS){
            return grilles[i];
        }
        return new Grille();
    }
    
    //Ajoute la grille passé en parmètre dans le tableau de grilles de la partie
    public boolean setG(int i, Grille g){
        if (i<NOMBREDEJOUEURS){
            System.out.println(g);
            System.out.println("Dans le setG");
            this.grilles[i] = g;
            return true;
            
        }
        return false;
    }
    //Ajoute les grilles passées en paramètres aux indices 0 et1 dans le tableau de grilles
    public boolean setG(Grille g1, Grille g2){
        if (g1 != null && g2 != null){
            grilles[0] = g1;
            grilles[1] = g2;
            return true;
        }
        return false;
        
    }
}
