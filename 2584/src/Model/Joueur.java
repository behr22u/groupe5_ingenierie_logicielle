
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
    protected int scoreMax; // valeur de la plus grande grille
    protected int score; // valeur du score qui change à chaque fusion
    protected int nbUndo;
    protected int deplacement;

    /**
     * Constructeur d'un joueur.
     */
    public Joueur() {
        this.grilleActuelle = new Grille();
        this.grilleTampon = null;
        this.nbUndo = 5;
        this.scoreMax = 0;
        this.score = 0;
        this.deplacement = 0;
    }

    /**
     * Constructeur d'un joueur en passant la grille en paramètre
     *
     * @param gA la grille actuelle du joueur
     */
    public Joueur(Grille gA) {
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

    public void setDeplacement(int nbDepacement) {
        this.deplacement = nbDepacement;
    }

    public void setGrilleActuelle(Grille g) {
        this.grilleActuelle = g;
    }

    public void setGrilleTampon(Grille g) {
        this.grilleTampon = g;
    }

    public void setScoreMax(int score) {
        this.scoreMax = score;
    }
    public void setScoreMax() {
        this.scoreMax = grilleActuelle.getValeurMax();
    }

    public void setScore(int scorebis) {
        this.score = this.score + scorebis;
    }

    public Grille getGrilleActuelle() {
        return this.grilleActuelle;
    }

    public Grille getGrilleTampon() {
        return this.grilleTampon;
    }

    public int getScore() {
        return this.score;
    }

    public int getScoreMax() {
        return this.scoreMax;
    }

    public void addDeplacement() {
        this.deplacement++;
    }

    @Override
    public Joueur clone() {

        Joueur j = new Joueur();
        j.setDeplacement(this.deplacement);
        j.setNbUndo(this.nbUndo);
        j.setScore(this.score);
        j.setScoreMax(this.score);
        j.setGrilleActuelle((Grille) this.grilleActuelle.clone());
        if (this.grilleTampon != null) {
            j.setGrilleTampon((Grille) this.grilleTampon.clone());
        }
        return j;

    }

    public void jouer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * joue dans la direction passée en paramètre.
     *
     * @param direction direction dans laquelle on joue
     * @return true dès que le déplacement est effectué
     */
    public boolean jouer(int direction) {
        this.grilleTampon = (Grille) this.grilleActuelle.clone();
        boolean b = this.grilleActuelle.lanceurDeplacerCases(direction);
        if (b) {
            return true;
        } else {
            this.grilleTampon = null;
            return false;
        }

    }

    /**
     * Methode undo qui permet à l'aide de deux variables de récupérer la grille
     * du coup précédent
     *
     * @return true dès que le undo est effectué.
     */
    public boolean undo() {

        if (this.nbUndo > 0 && this.grilleTampon != null) {
            this.grilleActuelle = (Grille) this.grilleTampon.clone();
            this.nbUndo = this.nbUndo - 1;
            this.grilleTampon = null;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Détermine la possibilité d'un undo
     *
     * @return le booléen correspondant au résultat
     */
    public boolean undoPossible() {
        if (this.nbUndo > 0 && this.grilleTampon != null) {
            return true;
        } else {
            return false;
        }
    }

}