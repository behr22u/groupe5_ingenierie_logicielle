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
    
    private Grille GrilleActuelle;
    
    private Grille GrilleTampon;
    
    private int scoreMax;
    
    private int nbUndo;
    
    
    public Joueur(){
        this.GrilleActuelle = new Grille();
        this.GrilleTampon = new Grille();
        this.nbUndo = 5;
        this.scoreMax = 0;
    }
    
    /* // on attend la metode de theo
    public void Undo(){
        
    }
    */
    
    
    public void setGrilleA(Grille g){
        this.GrilleActuelle = g;
    }
    
    
    public void setGrilleT(Grille gi){
        this.GrilleTampon = gi;
    }
    
}
