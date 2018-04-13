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
    private int vs; // 4 possibilités de jeu voir dans parametres
    private int valeurMax; // valeur max de la partie
    
    
    
    public Partie(){
        this.joueurs= new Joueur[NOMBREDEJOUEURS];
        this.vs = VSDEFAULT;
    }
    public Partie(Joueur j1, Joueur j2, int vs){
        this.joueurs[0] = j1;
        this.joueurs[1] = j2;
        if (vs >=0 || vs <= 3){// si la valeur rentrée dans le vs est correcte on l'assigne 
            this.vs = vs;  
        }else{// sinon on assigne à vs la valeur par défaut
            this.vs = VSDEFAULT;
        }
        
    }
    public void setValeurMax(){
        int vJ1 = joueurs[0].getGrilleActuelle().getValeurMax();
        int vJ2 = joueurs[1].getGrilleActuelle().getValeurMax();
        if ( vJ1> vJ2){
            this.valeurMax = vJ1;
        }else{
            this.valeurMax = vJ2;
        }
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(Joueur[] joueurs) {
        this.joueurs = joueurs;
    }

    public void setJoueurs(Joueur j1, Joueur j2){
        this.joueurs[0] = j1;
        this.joueurs[1] = j2;
    }
    
    public HashSet<Case> getHashGrille(int i){
        return joueurs[i].getGrilleActuelle().getGrille();
    }

    public int getVs() {
        return vs;
    }

    public void setVs(int vs) {
        this.vs = vs;
    }

    
    /// renvoie la grille de l'indice passé en paramètre, et si cette grille est trop grande renvoie une nouvelle grille vide
    public Grille getG(int i) {
        if (i<NOMBREDEJOUEURS){
            return this.joueurs[i].grilleActuelle;
        }
        return null;
    }
    public int getValeurMax(){
        return this.getValeurMax();
    }
    
    public Joueur getJ(int i){
        if (i<NOMBREDEJOUEURS){
            return this.joueurs[i];
        }
        return null;
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
