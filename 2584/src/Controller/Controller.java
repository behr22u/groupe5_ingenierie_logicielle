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
import Model.RandomPlayer;
import Model.SuitesMathematiques;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private Label nbundo1;
    @FXML
    private Label nbundo2;
   
    
    @FXML
    private GridPane gridpane;
    @FXML
    private Pane fond; // panneau recouvrant toute la fenêtre
    @FXML
    private Pane fond_case; // panneau utilisé pour la mise à jour des cases, suppression/reapparition
    
    // boutons utilisé dans le cas du Undo
    @FXML
    private Button undoj1;
    @FXML
    private Button undoj2;
    
    // menu déroulant
    @FXML
    private ComboBox choix;
    
    // bouton qui démarre le jeu
    @FXML
    private Button start;
    
    
    
    public static void lancementJeuGraphique(String val){
        
        switch(val){
            case "Joueur vs Joueur" : Controller.partie.setVs(VSJOUEUR);
            break;
            
            case "Joueur vs Random" : Controller.partie.setVs(VSRANDOM);
            break;
            
            case "IA vs Joueur" : Controller.partie.setVs(VSIA);
            break;
            
            case "IA vs Random" : Controller.partie.setVs(VSIARANDOM);
            break;
        }
        
        Grille g1 = new Grille();
        boolean b = g1.nouvelleCase();
        b = g1.nouvelleCase(1);
        Joueur j1 = new Joueur(g1);
        Grille g2 = (Grille) g1.clone();
        
        if(Controller.partie.getVs() == VSRANDOM){
            RandomPlayer rp = new RandomPlayer(g2);
            Controller.partie.setJoueurs(j1, rp);
        }else{
            Joueur j2 = new Joueur(g2);
            Controller.partie.setJoueurs(j1, j2);
        }
        
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
        
        //pré-choix d'aversaire via menu déroulant
        //this.afficheTableau();
        
        // si on clique sur le bouton undoj1
        undoj1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                Controller.partie.getJ(0).undo();
                //undoj1.setDisable(true);
                // on vide le panneau contenant les différents label représenant les cases
                viderGrid(fond_case);
                // on affiche les labels à leur nouvel emplacement ainsi que les nouveaux labels (les cases)
                afficheTableau();
            }
        });
        
        // si on clique sur le bouton undoj2
        undoj2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                Controller.partie.getJ(1).undo();
                // si on utilise setDisable(true), il n'est plus possible d'utiliser la methode keyPressed.. aucune idée pourquoi..
                //undoj2.setDisable(true);
                // on vide le panneau contenant les différents label représenant les cases
                viderGrid(fond_case);
                // on affiche les labels à leur nouvel emplacement ainsi que les nouveaux labels (les cases)
                afficheTableau();
            }
       });
        
        
        start.setOnAction(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent event){
               String val_choix = (String) choix.getValue();
               fond_case.getChildren().clear();
               lancementJeuGraphique(val_choix);
               // on vide le panneau contenant les différents label représenant les cases
               viderGrid(fond_case);
               // on affiche les labels à leur nouvel emplacement ainsi que les nouveaux labels (les cases)
               afficheTableau();
           }
        });  
        
    }
    
    /**
     * Méthode qui permet d'afficher les visuels des tableaux
     * On y appelle le css
     * On affiche les différents labels qui représentent les cases avec leur valeurs
     */
    public void afficheTableau(){
        for( int i = 0 ; i < NOMBREDEJOUEURS ; i++ ){
           // System.out.println("ici 1 : " + i);
            HashSet<Case> g = partie.getHashGrille(i); 
            
            gridpane = new GridPane();
            //System.out.println("ici 2 : " + i);
            for(Case c : g){
              //  System.out.println("ici 3 : " + i);
                Pane p = new Pane();
                Label l = new Label(Integer.toString(c.getValeur()));
                gridpane.add(l, c.getX(), c.getY());
               // System.out.println("ici 4 : " + i);
                // utilisation de styles pour la grille et la tuile (voir styles.css)
                p.getStyleClass().add("pane"); 
                //System.out.println("ici 5 : " + i);
                l.getStyleClass().add("tuile");
                //System.out.println("ici 6 : " + i);
                gridpane.getStyleClass().add("gridpane");
                //System.out.println("ici 7 : " + i);
                GridPane.setHalignment(l, HPos.CENTER);
                //System.out.println("ici 8 : " + i);
                fond_case.getChildren().add(p);
                //System.out.println("ici 9 : " + i);
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
    
    
    /**
     * methode permettant de vider completement le panneau rentrée en parametres
     * On l'utilise dans la methode keyPressed, juste avant l'appel d'afficherTableau qui permet d'afficher les nouveaux emplacements des cases
     * @param pane 
     */
    public void viderGrid(Pane pane){
            pane.getChildren().clear();
    }

       
    @FXML
    private void handleButtonAction(MouseEvent event) {
        System.out.println("Clic de souris sur le bouton menu");
    }

    @FXML
    public void keyPressed(KeyEvent ke) {
        System.out.println("touche appuyée");
        System.out.println("type vs = " + Controller.partie.getVs());
        String touche = ke.getText();
        if (Controller.partie.getVs() != VSIARANDOM){
            /// on teste les directions du joueur 1
            int direction = convertDirectionJ1(touche);
            boolean b1 = false;
            if (direction != 0){
                b1 = partie.getJ(0).jouer(direction);
                if (b1) {
                    boolean b = partie.getG(0).nouvelleCase();
                    if (!b) partie.getG(0).gameOver();
                    if (Controller.partie.getJ(0).undoPossible()){
                        undoj1.setDisable(false);
                    }
                }
                //affichage debogage
                System.out.println(partie.getG(0));
                // on incrémente la variable
                partie.getJ(0).addDeplacement();
                System.out.println(partie.getJ(0).getScore());
                // on modifie le label move1 et score1
                move1.setText(Integer.toString(partie.getJ(0).getDeplacement()));
                score1.setText(Integer.toString(partie.getJ(0).getScore()));
                nbUndo1.setText(Integer.toString(partie.getJ(0).getNbUndo()));
            }
            if (Controller.partie.getVs() == VSRANDOM && b1){
                System.out.println(Controller.partie.getJ(1).getClass());
                Controller.partie.getJ(1).jouer();
            }
            if (Controller.partie.getVs() == VSJOUEUR){
                direction = convertDirectionJ2(touche);
                boolean b2 = false;
                if (direction != 0){
                    b2 = partie.getJ(1).jouer(direction);
                    if (b2) {
                        boolean b = partie.getG(1).nouvelleCase();
                        if (!b) partie.getG(1).gameOver();
                        if (Controller.partie.getJ(1).undoPossible()){
                            undoj2.setDisable(false);
                        }
                    }
                    System.out.println(partie.getG(1));

                    //on incremente la variable
                    partie.getJ(1).addDeplacement();
                    //on modifie le label move2
                    move2.setText(Integer.toString(partie.getJ(1).getDeplacement()));
                    score2.setText(Integer.toString(partie.getJ(1).getScore()));
                    nbUndo2.setText(Integer.toString(partie.getJ(1).getNbUndo()));
                    
                }
            }

           
            
            // on vide le panneau contenant les différents label représenant les cases
            viderGrid(fond_case);
            // on affiche les labels à leur nouvel emplacement ainsi que les nouveaux labels (les cases)
            this.afficheTableau();
        }
    }
    
    
    
    /**
     * Méthode de lancement du Jeu, sans interface graphique, dans la console
     */
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
     
     /**
      * Méthode qui permet de déterminer si la partie est finie
      * @param grilles
      * @return un boolean : true --> la partie est finie ; false --> la partie n'est pas finie
      */
     static private boolean finDePartie(Grille grilles[]){
        boolean fini = false;
        for(Grille g : grilles){
            if (g.partieFinie()){
                fini = true;
                
                if(g.getValeurMax() == 2584){
                    g.victory();
                }
            }
        }
        return fini;
    }
     
     
     
    
}
