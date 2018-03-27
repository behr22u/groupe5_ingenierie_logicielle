/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Louis
 */
public class Joueur implements Parametres {
    
    protected Grille grilleActuelle;
    
    protected Grille grilleTampon;
    
    protected int scoreMax;
    
    protected int nbUndo;
    
    
    public Joueur( Grille grille ){
        this.grilleActuelle = grille;
        this.grilleTampon = new Grille();
        this.nbUndo = 5;
        this.scoreMax = 0;
    }
    
    /* // on attend la metode de theo
    public void Undo(){
        
    }
    */
    
    
    public void setGrilleA(Grille g){
        this.grilleActuelle = g;
    }
    
    
    public void setGrilleT(Grille gi){
        this.grilleTampon = gi;
    }
    
}
