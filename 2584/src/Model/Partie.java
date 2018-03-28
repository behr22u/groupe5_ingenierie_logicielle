/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;



/**
 *
 * @author leath
 */
public class Partie implements Parametres{
    private Grille grilles[];
    Partie(){
        Grille grilles[] = new Grille[NOMBREDEJOUEURS];
    }

    public Grille[] getGrilles() {
        return grilles;
    }

    public void setGrilles(Grille[] grilles) {
        this.grilles = grilles;
    }
    /// renvoie la grille de l'indice passé en paramètre, et si cette grille est trop grande renvoie une nouvelle grille vide
    public Grille getG(int i) {
        if (i<NOMBREDEJOUEURS){
            return grilles[i];
        }
        return new Grille();
    }
}
