/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

/**
 *
 * @author Louis
 */
public class Case implements Parametres{
    
    //location en abscisse de la case
    private int x;
    
    //location en ordonnee de la case
    private int y;
    
    //valeur de la case
    private int val;
    
    //une case se trouve dans un objet grille
    //private Grille grille;
    

    /**
     * Constructeur d'une case, prend en parametre l'abscisse, l'ordonnee et la valeur que cette case aura
     * @param abscisse
     * @param ordonnee
     * @param valeur 
     */
    public Case(int abscisse, int ordonnee, int valeur){
        this.x = abscisse;
        this.y = ordonnee;
        this.val = valeur;
    }
    
    
    
    @Override
    public  boolean equals(Object obj){
        
        //on teste si l'objet rentré en parametre est bien une instance de la classe Case
        //si c'est le cas on teste si les coordonnees de la case sont égales aux coordonnees de l'objet parametre, on retourne le resultat
        //si ca n'est pas le cas on retourne false
       if(obj instanceof Case){
           Case c = (Case) obj;
           return(this.x == c.x && this.y == c.y);
       }else{
           return false;
       }
    }
    
 
    
    
    
    
    
    public int getX(){
        return this.x;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public void setY(){
        this.y = y;
    }
    
    public int getValeur(){
        return this.val;
    }
    
    public void setValeur(int v){
        this.val = v;
    }
      
    
    /**
     * permet d'initialiser la grille
     * @param g 
     */
    public void setGrille(Grille g){
        this.grille = g;
    }
    
    
}
