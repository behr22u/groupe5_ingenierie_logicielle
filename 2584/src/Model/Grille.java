/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Controller;
import static Controller.Controller.partie;
import static Model.Parametres.convertDirectionJ1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Theloua
 **/
public class Grille implements Parametres {

    private final HashSet<Case> grille;
    private int valeurMax;
    private boolean deplacement;
    

    /**
     * Constructeur d'une grille
     */
    public Grille() {
        this.grille = new HashSet<>();
        this.valeurMax = 0;
    }
    
    public Grille(HashSet<Case> g, int vmax, boolean deplacement ){
        this.grille = g;
        this.valeurMax = vmax;
        this.deplacement = deplacement;
    }
    
   
    @Override
    public Object clone(){
        Grille g = new Grille();
        Case case_t;
        
        for (Case c : this.grille){
            case_t = (Case) c.clone();
            case_t.setGrille(g);
            g.grille.add(case_t);
        }
        
        g.valeurMax = this.valeurMax;
        g.deplacement = this.deplacement;
        return (Object) g;
    }
    
    
    /*
    * Si direction = HAUT : retourne les 4 cases qui sont le plus en haut (bas!?) (une pour chaque colonne)
    * Si direction = DROITE : retourne les 4 cases qui sont le plus à droite (une pour chaque ligne)
    * Si direction = BAS : retourne les 4 cases qui sont le plus en bas (une pour chaque colonne)
    * Si direction = GAUCHE : retourne les 4 cases qui sont le plus à gauche (une pour chaque ligne)
    * Attention : le tableau retourné peut contenir des null si les lignes/colonnes sont vides
     */
    public Case[] getCasesExtremites(int direction) {
        Case[] result = new Case[TAILLE];
        for (Case c : this.grille) {
            switch (direction) {
                case HAUT:
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() > c.getY())) { // si on n'avait pas encore de case pour cette rangée ou si on a trouvé un meilleur candidat
                        result[c.getX()] = c;
                    }
                    break;
                case BAS:
                    if ((result[c.getX()] == null) || (result[c.getX()].getY() < c.getY())) {
                        result[c.getX()] = c;
                    }
                    break;
                case GAUCHE:
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() > c.getX())) {
                        result[c.getY()] = c;
                    }
                    break;
                default:
                    if ((result[c.getY()] == null) || (result[c.getY()].getX() < c.getX())) {
                        result[c.getY()] = c;
                    }
                    break;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        int[][] tableau = new int[TAILLE][TAILLE];
        for (Case c : this.grille) {
            tableau[c.getY()][c.getX()] = c.getValeur();
        }
        String result = "";
        for (int i = 0; i < tableau.length; i++) {
            result += Arrays.toString(tableau[i]) + "\n";
        }
        return result;
    }
    

   

    
    /**
     * methode partieFinie qu'on appelle pour tester si la partie est finie ou pas
     * @return true si le nombre d'éléments de la grille est supérieur à 16, false si le nombre d'élément est inférieur à 16
     */
    public boolean partieFinie() {
        if (this.grille.size() < TAILLE * TAILLE) {
            return false;
        } else {
            for (Case c : this.grille) {
                for (int i = 1; i <= 2; i++) {
                    if (c.getVoisinDirect(i) != null) {
                        if (c.estVoisinFibo(c.getVoisinDirect(i))) {
                            
                            return false;
                        } 
                    }
                }
            }
        }
        return true;
    }
    
    
    /**
     * 
     * @param direction
     * @return 
     */
    public boolean lanceurDeplacerCases(int direction) {
        Case[] extremites = this.getCasesExtremites(direction);
        deplacement = false; // pour vérifier si on a bougé au moins une case après le déplacement, avant d'en rajouter une nouvelle
        for (int i = 0; i < TAILLE; i++) {
            switch (direction) {
                case HAUT:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case BAS:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                case GAUCHE:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
                default:
                    this.deplacerCasesRecursif(extremites, i, direction, 0);
                    break;
            }
        }
        return deplacement;
    }

    /**
     * 
     * @param c1
     * @param c2 
     */
    private void fusion(Case c1, Case c2) {////////////
        c1.setValeur(c1.getValeur()+c2.getValeur() );
        if (this.valeurMax < c1.getValeur()) {
            this.valeurMax = c1.getValeur();
        }
        deplacement = true;
        
        if(this == partie.getG(0)){
           partie.getJ(0).setScore(c1.getValeur());
        } else
        if(this == partie.getG(1)){
            partie.getJ(1).setScore(c1.getValeur());
        }        
    }
    

    private void deplacerCasesRecursif(Case[] extremites, int rangee, int direction, int compteur) {
        if (extremites[rangee] != null) {
            if ((direction == HAUT && extremites[rangee].getY() != compteur)
                    || (direction == BAS && extremites[rangee].getY() != TAILLE - 1 - compteur)
                    || (direction == GAUCHE && extremites[rangee].getX() != compteur)
                    || (direction == DROITE && extremites[rangee].getX() != TAILLE - 1 - compteur)) {
                this.grille.remove(extremites[rangee]);
                switch (direction) {
                    case HAUT:
                        extremites[rangee].setY(compteur);
                        break;
                    case BAS:
                        extremites[rangee].setY(TAILLE - 1 - compteur);
                        break;
                    case GAUCHE:
                        extremites[rangee].setX(compteur);
                        break;
                    default:
                        extremites[rangee].setX(TAILLE - 1 - compteur);
                        break;
                }
                this.grille.add(extremites[rangee]);
                deplacement = true;
            }
            Case voisin = extremites[rangee].getVoisinDirect(-direction);
            if (voisin != null) {
                if (extremites[rangee].estVoisinFibo(voisin)) {
                    this.fusion(extremites[rangee],voisin);
                    extremites[rangee] = voisin.getVoisinDirect(-direction);
                    this.grille.remove(voisin);
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
                } else {
                    extremites[rangee] = voisin;
                    this.deplacerCasesRecursif(extremites, rangee, direction, compteur + 1);
                }
            }
        }
    }

    

    public void victory() {
        System.out.println("Bravo ! Vous avez atteint " + this.valeurMax);
        System.exit(0);
    }

    
    public void gameOver() {
        System.out.println("La partie est finie. Votre score est " + this.valeurMax);
        System.exit(1);
    }

    public boolean nouvelleCase() {
        if (this.grille.size() < TAILLE * TAILLE) {
            ArrayList<Case> casesLibres = new ArrayList<>();
            Random ra = new Random();
            int valeur = (1 + ra.nextInt(2));
            // on crée toutes les cases encore libres
            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case c = new Case(x, y, valeur);
                    if (!this.grille.contains(c)) { // contains utilise la méthode equals dans Case (qui vérifie les positions)
                        casesLibres.add(c);
                    }
                }
            }
            // on en choisit une au hasard et on l'ajoute à la grille
            Case ajout = casesLibres.get(ra.nextInt(casesLibres.size()));
            ajout.setGrille(this);
            this.grille.add(ajout);
            if ((this.grille.size() == 1) || (this.valeurMax == 2 && ajout.getValeur() == 4)) { // Mise à jour de la valeur maximale présente dans la grille si c'est la première case ajoutée ou si on ajoute un 4 et que l'ancien max était 2
                this.valeurMax = ajout.getValeur();
            }
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean nouvelleCase(int n) {
        if (this.grille.size() < TAILLE * TAILLE) {
            ArrayList<Case> casesLibres = new ArrayList<>();
            Random ra = new Random();
            int valeur = (n);
            // on crée toutes les cases encore libres
            for (int x = 0; x < TAILLE; x++) {
                for (int y = 0; y < TAILLE; y++) {
                    Case c = new Case(x, y, valeur);
                    if (!this.grille.contains(c)) { // contains utilise la méthode equals dans Case (qui vérifie les positions)
                        casesLibres.add(c);
                    }
                }
            }
            // on en choisit une au hasard et on l'ajoute à la grille
            Case ajout = casesLibres.get(ra.nextInt(casesLibres.size()));
            ajout.setGrille(this);
            this.grille.add(ajout);
            if ((this.grille.size() == 1) || (this.valeurMax == 1 && ajout.getValeur() == 2)) { // Mise à jour de la valeur maximale présente dans la grille si c'est la première case ajoutée ou si on ajoute un 2 et que l'ancien max était 1
                this.valeurMax = ajout.getValeur();
            }
            return true;
        } else {
            return false;
        }
    }
    
    
    
       
     /**
     * getGrille qui retourne le hashset
     * @return un hashset qui contient des objets cases
     */
    public HashSet<Case> getGrille() {
        return grille;
    }

    /**
     * retourne la valeur maximale de la grille
     * @return un entier
     */
    public int getValeurMax() {
        return valeurMax;
    }
    
}
