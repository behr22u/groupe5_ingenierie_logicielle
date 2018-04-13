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
    
    
    public Joueur(){
        this.grilleActuelle = new Grille();
        this.grilleTampon = null;
        this.nbUndo = 5;
        this.scoreMax = 0;
        this.score = 0;
        this.deplacement = 0;
    }
    public Joueur(Grille gA){
        this.grilleActuelle = gA;
        this.grilleTampon = null;
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

    public int getDeplacement() {
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
    public void setScore(int scorebis){
        this.score = this.score + scorebis;
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
    @Override
    public Joueur clone(){
        
        Joueur j = new Joueur();
        j.setDepacement(this.deplacement);
        j.setNbUndo(this.nbUndo);
        j.setScore(this.score);
        j.setScoreMax(this.score);
        j.setGrilleActuelle((Grille) this.grilleActuelle.clone());
        if (this.grilleTampon != null){
            j.setGrilleTampon((Grille) this.grilleTampon.clone());
        }
        return j;
        
    }

    public void jouer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean jouer(int direction){
        this.grilleTampon = (Grille) this.grilleActuelle.clone();
        boolean b = this.grilleActuelle.lanceurDeplacerCases(direction);
        if (b){
            return true;
        }else{
            this.grilleTampon = null;
            return false;
        }
        
    }
    
    /**
     * 
     * @return 
     */
    public boolean undo(){
        if (this.nbUndo > 0 && this.grilleTampon != null){
            System.out.println("On rentre dans le undo");
            this.grilleActuelle = (Grille) this.grilleTampon.clone();
            System.out.println("Après le clone");
            System.out.println(this.grilleActuelle);
            this.nbUndo = this.nbUndo - 1;
            System.out.println("Après le minus undo");
            this.grilleTampon = null;
            System.out.println("true");
            return true;
        }else{
            System.out.println("false");
            return false;
        }
    }
    
    /**
     * 
     * @return 
     */
    public boolean undoPossible(){
        if (this.nbUndo > 0 && this.grilleTampon != null){
            return true;
        }else{
            return false;
        }
    }
    
}
