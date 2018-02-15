/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Grille;
import static Model.Parametres.BAS;
import static Model.Parametres.DROITE;
import static Model.Parametres.GAUCHE;
import static Model.Parametres.HAUT;
import static Model.Parametres.OBJECTIF;
import Model.SuitesMathematiques;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author leath
 */
public class Controller implements Initializable{
    @FXML
    private Label label;
    
    Grille g = new Grille();
    ArrayList<Integer> termesFibonacci = SuitesMathematiques.fibonacci(2584);
    
    @FXML
    /**
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    **/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }  
    public void lancementJeu(){
        Model.Grille g = new Model.Grille();
            boolean b = g.nouvelleCase(1);
            b = g.nouvelleCase(1);
            System.out.println(g);
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

            while (!g.partieFinie()) {
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
                    boolean b2 = g.lanceurDeplacerCases(direction);
                    if (b2) {
                        b = g.nouvelleCase();
                        if (!b) g.gameOver();
                    }
                    System.out.println(g);
                    if (g.getValeurMax()>=OBJECTIF) g.victory();
                }
            }
            g.gameOver();
    }
    
    /*
    @Override
    public void start(Stage stage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
}
