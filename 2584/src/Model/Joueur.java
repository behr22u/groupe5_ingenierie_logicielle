/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author theod
 */
public class Joueur {
    private Grille grilleActu;
    private Grille grilleSauv;
    private int nbUndo = 5;
    
    public void undo(){
        if (nbUndo>0){
            this.grilleActu=this.grilleSauv;
            nbUndo-=1;
        }
    }
    
}
