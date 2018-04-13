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
   
    //tableau de joueurs qui contiendra les deux joueurs de la partie
    private Joueur[] joueurs;
    //4 possibilité de jeu, voir dans paramètres pour plus de détails
    private int vs;
    //valeur max de la partie
    private int valeurMax; 
    
    /**
     * Constructeur d'une partie.
     */
    public Partie(){
        this.joueurs= new Joueur[NOMBREDEJOUEURS];
        this.vs = VSDEFAULT;
        this.valeurMax = 0;
    }
    
    
    /**
     * Constructeur d'une partie en passant ses attributs en paramètre.
     *
     * @param j1 joueur numéro 1
     * @param j2 joueur numéro 2
     * @param vs type d'adversaire affronté @see Parametres
     */
    public Partie(Joueur j1, Joueur j2, int vs){
        this.joueurs[0] = j1;
        this.joueurs[1] = j2;
        if (vs >=0 || vs <= 3){// si la valeur rentrée dans le vs est correcte on l'assigne 
            this.vs = vs;  
        }else{// sinon on assigne à vs la valeur par défaut
            this.vs = VSDEFAULT;
        }
        this.valeurMax = 0;
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

    
    /**
     * renvoie la grille de l'indice passé en paramètre, et si cette grille est
     * trop grande renvoie une nouvelle grille vide
     *
     * @param i l'indice de la grille à obtenir
     * @return la grille obtenue
     */
    public Grille getG(int i) {
        if (i<NOMBREDEJOUEURS){
            return this.joueurs[i].getGrilleActuelle();
        }
        return null;
    }
    public int getValeurMax(){
        return this.valeurMax;
    }
    
    public Joueur getJ(int i){
        if (i<NOMBREDEJOUEURS){
            return this.joueurs[i];
        }
        return null;
    }
    
    /**
     * Ajoute la grille passé en parmètre dans le tableau de grilles de la
     * partie
     *
     * @param i l'indice auquel on ajoute la grille
     * @param g la grille à ajouter
     * @return
     */
    public boolean setG(int i, Grille g){
        if (i<NOMBREDEJOUEURS){
            System.out.println(g);
            System.out.println("Dans le setG");
            this.joueurs[i].setGrilleActuelle(g);
            return true;
            
        }
        return false;
    }
    /**
     * Ajoute les grilles passées en paramètres aux indices 0 et1 dans le
     * tableau de grilles
     *
     * @param g1 la première grille
     * @param g2 la deuxième grille
     * @return
     */
    public boolean setG(Grille g1, Grille g2){
        if (g1 != null && g2 != null){
            this.joueurs[0].setGrilleActuelle(g1);
            this.joueurs[1].setGrilleActuelle(g2);
            return true;
        }
        return false;
        
    }
}
