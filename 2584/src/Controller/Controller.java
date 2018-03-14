/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Grille;
import Model.Parametres;
import static Model.Parametres.BAS;
import static Model.Parametres.DROITE;
import static Model.Parametres.GAUCHE;
import static Model.Parametres.HAUT;
import static Model.Parametres.OBJECTIF;
import Model.SuitesMathematiques;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private static Grille grille = new Grille();
    @FXML
    private Label label;
    
    Grille g = new Grille();
    public static ArrayList<Integer> termesFibonacci = SuitesMathematiques.fibonacci(OBJECTIF);
    
    
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
        /*System.out.println("X:");
        int x= sc.nextInt();
        System.out.println("Y:");
        int y= sc.nextInt();
        System.out.println("Valeur:");
        int valeur= sc.nextInt();
        Case c = new Case(x,y,valeur);
        g.getGrille().remove(c);
        System.out.println(g);*/

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
    
    
    static public void lancementJeuGraphique(){
        for( int i = 0 ; i < NOMBREDEJOUEURS ; i++ ){
            System.out.println("Grille du joueur" + i);
            grille = new Grille();
            boolean b = grille.nouvelleCase(1);
            b = grille.nouvelleCase(1);
            System.out.println(grille);
        }
        if (grille.partieFinie()){
            grille.gameOver();
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
    
    ///////////////////////// Prite affichage
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

    
    
    // variables globales non définies dans la vue (fichier .fxml)
    private final Pane p = new Pane(); // panneau utilisé pour dessiner une tuile "2"
    private Label c = new Label();
    private int x = 24, y = 191;
    private int objectifx = 24, objectify = 191;
    private ObservableList<Node> bla;
    

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // a laisser si jamais on fait la version où la gridpane se met à jour (pas de déplacements visuels)
        //viderGrid(gridpane);
        
        // TODO
        System.out.println("le contrôleur initialise la vue");
        // utilisation de styles pour la grille et la tuile (voir styles.css)
        p.getStyleClass().add("pane"); 
        c.getStyleClass().add("tuile");
        gridpane.getStyleClass().add("gridpane");
        
        for(int i = 0; i <= getRowCount(gridpane); i++ ){
            for(int j = 0; i <= getColumnCount(gridpane); i++){
                Pane pane = new Pane();
                c = new Label();  
                gridpane.add(label,i, j);
                fond.getChildren().add(p);
                p.getChildren().add(label);
            }
            
        }
        
        // on place la tuile en précisant les coordonnées (x,y) du coin supérieur gauche
        p.setLayoutX(x);
        p.setLayoutY(y);
        p.setVisible(true);
        c.setVisible(true);
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
public void delete(int row) {
        gridpane.removeNodes(getNodesFromRow(row));
        int i = row;
        while (!getNodesFromRow(i + 1).isEmpty()) {
            moveNodes(i + 1, getNodesFromRow(i + 1));
            removeNodes(getNodesFromRow(i + 1));
            i++;
        }
    }



/* A VOIR
 public void addPane(){
 	for(int i = 0; i< Fields.size(); i++){
 		gridpane.add(new Pane(), i, 1);
 	}
 }*/


/**
 * méthode qui permet de retourner le nombre de ligne de la gridpane utilisée
 * @param pane
 * @return le nombre de ligne
 */
 private int getRowCount(GridPane pane) {
        int numRows = pane.getRowConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if(rowIndex != null){
                    numRows = Math.max(numRows,rowIndex+1);
                }
            }
        }
        return numRows;
    }
 
 
 /**
  * méthode qui permet de retourner le nombre de colonnes de la gridpane utilisée
  * @param pane
  * @return le nombre de colonnes
  */
 private int getColumnCount(GridPane pane){
     int numCol = pane.getRowConstraints().size();
     for(int i = 0; i < pane.getChildren().size(); i++){
         Node child = pane.getChildren().get(i);
         if(child.isManaged()){
             Integer ColIndex = GridPane.getColumnIndex(child);
             if(ColIndex != null){
                 numCol = Math.max(numCol, ColIndex+1);
             }
         }
     }
     return numCol;
 }
 
    
    

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
            boolean b2 = grille.lanceurDeplacerCases(direction);
            if (b2) {
                boolean b = grille.nouvelleCase();
                if (!b) grille.gameOver();
            }
        }
    }
}
