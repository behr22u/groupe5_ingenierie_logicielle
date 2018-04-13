/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Controller.Controller.termesFibonacci;

/**
 *
 * @author Theloua
 */
public class Case implements Parametres {

    private int x, y, valeur;
    private Grille grille;

    /**
     * Constructeur de Case.
     *
     * @param abs Position de la case sur l'axe des abscisses.
     * @param ord Position de la case sur l'axe des ordonnées.
     * @param v Valeur de la case.
     */
    public Case(int abs, int ord, int v) {
        this.x = abs;
        this.y = ord;
        this.valeur = v;
    }

    public void setGrille(Grille g) {
        this.grille = g;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return this.valeur;
    }

    /**
     * La méthode equals est utilisée lors de l'ajout d'une case à un ensemble
     * pour vérifier qu'il n'y a pas de doublons (teste parmi tous les candidats
     * qui ont le même hashcode).
     *
     * @param obj L'objet comparé à this.
     * @return Retourne vrai si l'objet comparé était une Case, faux sinon.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Case) {
            Case c = (Case) obj;
            return (this.x == c.x && this.y == c.y);
        } else {
            return false;
        }
    }

    /**
     * Teste si 2 cases possèdent des valeurs voisines dans la suite de
     * Fibonacci.
     *
     * @param c la case testée avec this.
     * @return le résultat sous forme de booléen
     */
    public boolean estVoisinFibo(Case c) {
        Integer somme = c.getValeur() + this.getValeur();
        return termesFibonacci.contains(somme);

    }

    /**
     * Détermine le hashcode.
     *
     * @return le hashcode
     */
    @Override
    public int hashCode() {
        return this.x * 7 + this.y * 13;
    }

    /**
     * Teste si 2 cases possèdent des valeurs égales
     *
     * @param c la case testée avec this.
     * @return le résultat sous forme de booléen
     */
    public boolean valeurEgale(Case c) {
        if (c != null) {
            return this.valeur == c.valeur;
        } else {
            return false;
        }
    }

    /**
     * Renvoie la case voisine de this dans la direction donnée en paramètre.
     *
     * @param direction la direction dans laquelle on cherche le voisin de this
     * @return la case voisine
     */
    public Case getVoisinDirect(int direction) {
        if (direction == HAUT) {
            for (int i = this.y - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == BAS) {
            for (int i = this.y + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == this.x && c.getY() == i) {
                        return c;
                    }
                }
            }
        } else if (direction == GAUCHE) {
            for (int i = this.x - 1; i >= 0; i--) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        } else if (direction == DROITE) {
            for (int i = this.x + 1; i < TAILLE; i++) {
                for (Case c : grille.getGrille()) {
                    if (c.getX() == i && c.getY() == this.y) {
                        return c;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Methode qui copie une case,elle sera utilisée dans le clone de grille
     *
     * @return un objet qui est une copie d'une case
     */
    @Override
    public Object clone() {
        Case c = new Case(this.x, this.y, this.valeur);
        return (Object) c;
    }

    @Override
    public String toString() {
        return "Case(" + this.x + "," + this.y + "," + this.valeur + ")";
    }

}
