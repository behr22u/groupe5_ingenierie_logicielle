/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Case;
import Model.Grille;
import Model.Parametres;
import static Model.Parametres.BAS;
import static Model.Parametres.DROITE;
import static Model.Parametres.GAUCHE;
import static Model.Parametres.HAUT;
import static Model.Parametres.OBJECTIF;
import Model.SuitesMathematiques;
import static java.lang.StrictMath.E;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author leath
 */


public class Controller implements Initializable, Parametres{
    public static ArrayList<Integer> termesFibonacci = SuitesMathematiques.fibonacci(OBJECTIF);
    private static Grille grilles[] = new Grille[NOMBREDEJOUEURS]; // ici parce que utilisé dans plein de fonctions différentes, et plus simple que de le placer en parametre
    
    /*
     * Variables globales correspondant à des objets définis dans la vue (fichier .fxml)
     * Ces variables sont ajoutées à la main et portent le même nom que les fx:id dans Scene Builder
     */
    @FXML
    private Label score; // value will be injected by the FXMLLoader
    @FXML
    private GridPane gridpane;
    @FXML
    private Pane fond; // panneau recouvrant toute la fenêtre

    
    
    public static void lancementJeuGraphique(){
        for( int i = 0 ; i < NOMBREDEJOUEURS ; i++ ){
            System.out.println("Grille du joueur" + i);
            grilles[i] = new Grille();
            boolean b = grilles[i].nouvelleCase();
            b = grilles[i].nouvelleCase(1);
        }
    }
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        // TODO
        System.out.println("le contrôleur initialise la vue");
        for( int i = 0 ; i < NOMBREDEJOUEURS ; i++ ){
            System.out.println(grilles[i]);
            System.out.println("ici 1 : " + i);
            HashSet<Case> g = grilles[i].getGrille(); 
            System.out.println("ici 2 : " + i);
            for(Case c : g){
                System.out.println("ici 3 : " + i);
                Pane p = new Pane();
                Label l = new Label(Integer.toString(c.getValeur()));
                //gridpane.add(l, c.getX(), c.getY());
                System.out.println("ici 4 : " + i);
                // utilisation de styles pour la grille et la tuile (voir styles.css)
                p.getStyleClass().add("pane"); 
                System.out.println("ici 5 : " + i);
                l.getStyleClass().add("tuile");
                System.out.println("ici 6 : " + i);
               // gridpane.getStyleClass().add("gridpane");
                System.out.println("ici 7 : " + i);
                GridPane.setHalignment(l, HPos.CENTER);
                System.out.println("ici 8 : " + i);
                fond.getChildren().add(p);
                System.out.println("ici 9 : " + i);
                p.getChildren().add(l);
                // on place la tuile en précisant les coordonnées (x,y) du coin supérieur gauche 
                
                switch(i){
                    
                    case 0 :  p.setLayoutX(24 + c.getX()*(397/4));
                              p.setLayoutY(191 + c.getY()*(397/4));
                              break;
                              
                    case 1 :  p.setLayoutX(618 + c.getX()*(397/4));
                              p.setLayoutY(191 + c.getY()*(397/4));
                              break;
                }
               
                p.setVisible(true);
                l.setVisible(true);
                
            }
        
        }
        /*
        Iterator<Case> it = grille.iterator();
        while(it.hasNext()){
            
            Pane pane = new Pane();
            Label lab = new Label(Integer.toString(it.next().getValeur()));
            gridpane.getChildren().add(pane);
            pane.getChildren().add(lab);
            
        }
        */
        
        
        
        
        /* de la merde
        //ajout de la premiere case au hasard dans la grille
        int valeurx = 1 + r.nextInt(3);
        int valeury = 1 + r.nextInt(3);
        gridpane.add(p, valeurx, valeury);

        //ajout de la premiere case au hasard dans la grille    
        valeurx = 1 + r.nextInt(3);
        valeury = 1 + r.nextInt(3);
        gridpane.add(p, valeurx, valeury);
        */
        
        
        
        
        
        /*
        for(int i = 0; i <= getRowCount(gridpane); i++ ){
            for(int j = 0; i <= getColumnCount(gridpane); i++){
                Pane pane = new Pane();
                c = new Label();  
                gridpane.add(label,i, j);
                fond.getChildren().add(p);
                p.getChildren().add(label);
            }
            
        }*/
        
        
    }
    
  


    private List<Node> getNodesFromRow(int i) {
        List<Node> list = new ArrayList<Node>();
        for (Node n : gridpane.getChildren()) {
            if (gridpane.getRowIndex(n).equals(i)) {
                list.add(n);
            }
        }
        System.out.println(list.size());
        return list;
    }

    /**
     * methode permettant de vider completement la gridpane rentrée en parametres
     * @param gridpane 
     */
    public void viderGrid(GridPane gridpane){
            gridpane.getChildren().clear();
    }

// REGARDER API POUR CHANGER
/*
public void delete(int row) {
        gridpane.removeNodes(getNodesFromRow(row));
        int i = row;
        while (!getNodesFromRow(i + 1).isEmpty()) {
            moveNodes(i + 1, getNodesFromRow(i + 1));
            removeNodes(getNodesFromRow(i + 1));
            i++;
        }
    }
*/



/* A VOIR
 public void addPane(){
 	for(int i = 0; i< Fields.size(); i++){
 		gridpane.add(new Pane(), i, 1);
 	}
 }*/


    
    @FXML
    private void handleButtonAction(MouseEvent event) {
        System.out.println("Clic de souris sur le bouton menu");
    }

    @FXML
    public void keyPressed(KeyEvent ke) {
        System.out.println("touche appuyée");
        String touche = ke.getText();
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
        if (direction != 0){
            boolean b2 = grilles[0].lanceurDeplacerCases(direction);
            if (b2) {
                boolean b = grilles[0].nouvelleCase();
                if (!b) grilles[0].gameOver();
            }
            System.out.println(grilles[0]);
        }
        
        ///// Rajouter eune condition ici pour quand il y aura un joueur non réel ou pe mettre dans une autre fonction?
        direction = 0;
        if (touche.compareTo("k") == 0) { // utilisateur appuie sur "q" pour envoyer la tuile vers la gauche
            direction = GAUCHE;
        } else if (touche.compareTo("m") == 0) { // utilisateur appuie sur "d" pour envoyer la tuile vers la droite
            direction = DROITE;
        } else if (touche.compareTo("o") == 0) { // utilisateur appuie sur "z" pour envoyer la tuile vers le haut
            direction = HAUT;
        } else if (touche.compareTo("l") == 0) { // utilisateur appuie sur "s" pour envoyer la tuile vers le bas
            direction = BAS;
        }
        if (direction != 0){
            boolean b2 = grilles[1].lanceurDeplacerCases(direction);
            if (b2) {
                boolean b = grilles[1].nouvelleCase();
                if (!b) grilles[1].gameOver();
            }
            System.out.println(grilles[1]);
        }
        
    }
    
     static public void lancementJeu(){
        Grille grilles[] = new Grille[NOMBREDEJOUEURS];
        for( int i = 0 ; i < NOMBREDEJOUEURS ; i++ ){
            System.out.println("Grille du joueur" + i);
            grilles[i] = new Grille();
            boolean b = grilles[i].nouvelleCase(1);
            b = grilles[i].nouvelleCase(1);
            System.out.println(grilles[i]);
        }
        Scanner sc = new Scanner(System.in);

        while (!finDePartie(grilles)) {
            for (int i=0 ; i<NOMBREDEJOUEURS ; i++ ){
                System.out.println("C'est a vous joueur " + i + " !");
                System.out.println("Déplacer vers la Droite (d), Gauche (g), Haut (h), ou Bas (b) ?");
                String s = sc.nextLine();
                s.toLowerCase();
                if (!(s.equals("d") || s.equals("droite")
                        || s.equals("g") || s.equals("gauche")
                        || s.equals("h") || s.equals("haut")
                        || s.equals("b") || s.equals("bas"))) {
                    System.out.println("Vous devez écrire d pour Droite, g pour Gauche, h pour Haut ou b pour Bas");
                } else {
                    int direction;
                    if (s.equals("d") || s.equals("droite")) {
                        direction = DROITE;
                    } else if (s.equals("g") || s.equals("gauche")) {
                        direction = GAUCHE;
                    } else if (s.equals("h") || s.equals("haut")) {
                        direction = HAUT;
                    } else {
                        direction = BAS;
                    }
                    boolean b2 = grilles[i].lanceurDeplacerCases(direction);
                    if (b2) {
                        boolean b = grilles[i].nouvelleCase();
                        if (!b) grilles[i].gameOver();
                    }
                    System.out.println(grilles[i]);
                    if (grilles[i].getValeurMax()>=OBJECTIF) grilles[i].victory();
                }
            }
        }
        for(Grille g : grilles){
            g.gameOver();
        }
            
    }
     
     static private boolean finDePartie(Grille grilles[]){
        boolean fini = false;
        for(Grille g : grilles){
            if (g.partieFinie()){
                fini = true;
            }
        }
        return fini;
    }
}
