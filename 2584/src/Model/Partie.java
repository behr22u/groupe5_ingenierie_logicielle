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
    private Joueur[] joueurs;
    
    
    
    public Partie(){
        this.joueurs= new Joueur[NOMBREDEJOUEURS];
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    
    
    public HashSet<Case> getHashGrille(int i){
        return joueurs[i].getGrilleActuelle().getGrille();
    }

    
    /// renvoie la grille de l'indice passé en paramètre, et si cette grille est trop grande renvoie une nouvelle grille vide
    public Grille getG(int i) {
        if (i<NOMBREDEJOUEURS){
            return this.joueurs[i].grilleActuelle;
        }
        return new Grille();
    }
    
    //Ajoute la grille passé en parmètre dans le tableau de grilles de la partie
    public boolean setG(int i, Grille g){
        if (i<NOMBREDEJOUEURS){
            System.out.println(g);
            System.out.println("Dans le setG");
            this.joueurs[i].setGrilleActuelle(g);
            return true;
            
        }
        return false;
    }
    //Ajoute les grilles passées en paramètres aux indices 0 et1 dans le tableau de grilles
    public boolean setG(Grille g1, Grille g2){
        if (g1 != null && g2 != null){
            this.joueurs[0].setGrilleActuelle(g1);
            this.joueurs[1].setGrilleActuelle(g2);
            return true;
        }
        return false;
        
    }
}
