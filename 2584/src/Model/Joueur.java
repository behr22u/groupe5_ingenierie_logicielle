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
    protected int score;
    protected int nbUndo;
    protected int deplacement;
    
    
    public Joueur( ){
        this.grilleActuelle = new Grille();
        this.grilleTampon = new Grille();
        this.nbUndo = 5;
        this.scoreMax = 0;
        this.score = 0;
        this.deplacement = 0;
    }
    
    /* // on attend la metode de theo
    public void Undo(){
    }
     */
    public int getNbUndo() {
        return nbUndo;
    }

    public int getDepacement() {
        return deplacement;
    }

    public void setNbUndo(int nbUndo) {
        this.nbUndo = nbUndo;
    }

    public void setDepacement(int nbDepacement) {
        this.deplacement = nbDepacement;
    }
    
    
    public void setGrilleActuelle(Grille g){
        this.grilleActuelle = g;
    }
    public void setGrilleTampon(Grille g){
        this.grilleTampon = g;
    }
    public void setScoreMax(int score){
        this.scoreMax = score;
    }
    public void setScore(int score){
        this.score = score;
    }
    public Grille getGrilleActuelle(){
        return this.grilleActuelle;
    }
    public Grille getGrilleTampon(){
        return this.grilleTampon;
    }
    public int getScore(){
        return this.score;
    }
    public int getScoreMax(){
        return this.scoreMax;
    }
    public void addDeplacement(){
        this.deplacement ++;
    }
}
