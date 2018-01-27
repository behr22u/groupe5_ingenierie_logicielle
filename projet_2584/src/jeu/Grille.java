/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Louis
 */
public class Grille implements Parametres {
    
//hash set qui contient toutes les cases de la grille
// ne fonctionne pas parce qu'il y a erreur dans la classe case, pas complète    
private final HashSet<Case> grille;
    
private int valMax = 0; 

private boolean deplacement;

/**
 * Constructeur d'une grille, on lui donne un hashset qui contiendra les différentes cases
 */
public Grille(){
    this.grille = new HashSet<>;
}




}
