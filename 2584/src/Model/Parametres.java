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
public interface Parametres {
    static final int HAUT = 1;
    static final int DROITE = 2;
    static final int BAS = -1;
    static final int GAUCHE = -2;
    static final int TAILLE = 4;
    static final int OBJECTIF = 2584;
    static final int NOMBREDEJOUEURS = 2;
    
    // prend en paramètre une chaine de caratère (touche du clavier) renvoire le int correspondant à la dirrection
    static public int convertDirection(String touche){
        if(touche.compareTo("q") == 0 || touche.compareTo("k") == 0){
            return GAUCHE;
        }else if(touche.compareTo("s") == 0 || touche.compareTo("l") == 0){
            return BAS;
        }else if(touche.compareTo("d") == 0 || touche.compareTo("m") == 0){
            return DROITE;
        }else if(touche.compareTo("z") == 0 || touche.compareTo("o") == 0){
            return HAUT;
        }else{
            return 0;
        }        
    }
    ///prend en paramètre une chaine de caratère (touche du clavier) renvoire le int correspondant à la dirrection pour le joueur1
    static public int convertDirectionJ1(String touche){
        int direction = 0;
        if (touche.compareTo("q") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            direction = GAUCHE;
        } else if (touche.compareTo("d") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            direction = DROITE;
        } else if (touche.compareTo("z") == 0) { // utilisateur appuie sur "z" pour envoyer la tuile vers le haut
            direction = HAUT;
        } else if (touche.compareTo("s") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile vers le bas
            direction = BAS;
        }
        return direction;
    }
    ///prend en paramètre une chaine de caratère (touche du clavier) renvoire le int correspondant à la dirrection pour le joueur2
    static public int convertDirectionJ2(String touche){
        int direction = 0;
        if (touche.compareTo("k") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            direction = GAUCHE;
        } else if (touche.compareTo("m") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            direction = DROITE;
        } else if (touche.compareTo("o") == 0) { // utilisateur appuie sur "z" pour envoyer la tuile vers le haut
            direction = HAUT;
        } else if (touche.compareTo("l") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile vers le bas
            direction = BAS;
        }
        return direction;
    }
}

