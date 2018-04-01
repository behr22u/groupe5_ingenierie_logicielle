/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Case;
import Model.Grille;
import Model.Joueur;
import Model.Parametres;
import static Model.Parametres.BAS;
import static Model.Parametres.DROITE;
import static Model.Parametres.GAUCHE;
import static Model.Parametres.HAUT;
import static Model.Parametres.OBJECTIF;
import static Model.Parametres.convertDirectionJ1;
import static Model.Parametres.convertDirectionJ2;
import Model.Partie;
import Model.SuitesMathematiques;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author leath
 */


public class Controller implements Initializable, Parametres{
    public static ArrayList<Integer> termesFibonacci = SuitesMathematiques.fibonacci(OBJECTIF);
    public static Partie partie = new Partie();
    
    /*
     * Variables globales correspondant à des objets définis dans la vue (fichier .fxml)
     * Ces variables sont ajoutées à la main et portent le même nom que les fx:id dans Scene Builder
     */
    @FXML
    private Label score1; // value will be injected by the FXMLLoader
    @FXML
    private Label score2;
    
    @FXML
    private Label move1;
    @FXML
    private Label move2;
    @FXML
    private GridPane gridpane = new GridPane();
    @FXML
    private Pane fond; // panneau recouvrant toute la fenêtre
    
    /*
    private int movej1 = 0; // nombre de déplacements du joueur 1
    private int movej2 = 0; // nombre déplacements du joueur 2
*/
    
    
    public static void lancementJeuGraphique(){
        Grille g1 = new Grille();
        boolean b = g1.nouvelleCase();
        b = g1.nouvelleCase(1);
        Joueur j1 = new Joueur(g1);
        Grille g2 = g1.clone();
        Joueur j2 = new Joueur(g2);
        Controller.partie.setJoueurs(j1, j2);
        System.out.println(g1);
        System.out.println(g2);
 
        
        /*
        for( int i = 0 ; i < NOMBREDEJOUEURS ; i++ ){
            System.out.println("Grille du joueur" + i);
            Joueur j = new Joueur();
            Grille g = new Grille(j);
            boolean b = g.nouvelleCase();
            b = g.nouvelleCase(1);
            System.out.println(g);
            partie.setG(i,g);
        }
        */
    }
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        // TODO
        System.out.println("le contrôleur initialise la vue");
        this.afficheTableau();
    }
    
    public void afficheTableau(){
        for( int i = 0 ; i < NOMBREDEJOUEURS ; i++ ){
            System.out.println("ici 1 : " + i);
            HashSet<Case> g = partie.getHashGrille(i); 
            System.out.println("ici 2 : " + i);
            for(Case c : g){
                System.out.println("ici 3 : " + i);
                Pane p = new Pane();
                Label l = new Label(Integer.toString(c.getValeur()));
                gridpane.add(l, c.getX(), c.getY());
                System.out.println("ici 4 : " + i);
                // utilisation de styles pour la grille et la tuile (voir styles.css)
                p.getStyleClass().add("pane"); 
                System.out.println("ici 5 : " + i);
                l.getStyleClass().add("tuile");
                System.out.println("ici 6 : " + i);
                gridpane.getStyleClass().add("gridpane");
                System.out.println("ici 7 : " + i);
                GridPane.setHalignment(l, HPos.CENTER);
                System.out.println("ici 8 : " + i);
                fond.getChildren().add(p);
                System.out.println("ici 9 : " + i);
                p.getChildren().add(l);
                // on place la tuile en précisant les coordonnées (x,y) du coin supérieur gauche 
                
                switch(i){
                    
                    case 0 :  p.setLayoutX(24 + c.getX()*100);
                              p.setLayoutY(191 + c.getY()*100);
                              break;
                              
                    case 1 :  p.setLayoutX(618 + c.getX()*100);
                              p.setLayoutY(191 + c.getY()*100);
                              break;
                }
               
                p.setVisible(true);
                l.setVisible(true);
                
            }
        }
    }
    
  
    private void updateView(){
        
        //utiliser gridPane.getChildren().remove(node);
        
        
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





    
    @FXML
    private void handleButtonAction(MouseEvent event) {
        System.out.println("Clic de souris sur le bouton menu");
    }

    @FXML
    public void keyPressed(KeyEvent ke) {
        System.out.println("touche appuyée");
        String touche = ke.getText();
        int direction = convertDirectionJ1(touche);
        
        if (direction != 0){
            boolean b2 = partie.getG(0).lanceurDeplacerCases(direction);
            if (b2) {
                boolean b = partie.getG(0).nouvelleCase();
                if (!b) partie.getG(0).gameOver();
            }
            System.out.println(partie.getG(0));
            
            // on incrémente la variable
            partie.getJ(0).addDeplacement();
            // on modifie le label move1
            move1.setText(Integer.toString(partie.getJ(0).getDeplacement()));
        }
        
        ///// Rajouter eune condition ici pour quand il y aura un joueur non réel ou pe mettre dans une autre fonction?
        direction = convertDirectionJ2(touche);
        if (direction != 0){
            boolean b2 = partie.getG(1).lanceurDeplacerCases(direction);
            if (b2) {
                boolean b = partie.getG(1).nouvelleCase();
                if (!b) partie.getG(1).gameOver();
            }
            System.out.println(partie.getG(1));
            
            //on incremente la variable
            partie.getJ(1).addDeplacement();
            //on modifie le label move2
            move2.setText(Integer.toString(partie.getJ(1).getDeplacement()));
        }
        this.afficheTableau();
    }
    
    
    
    //jeu sans interface graphique
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
