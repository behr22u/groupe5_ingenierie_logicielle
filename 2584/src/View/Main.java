/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller;
import static Controller.Controller.lancementJeu;
import static Controller.Controller.lancementJeuGraphique;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Louis
 */
public class Main extends Application implements Model.Parametres{
    //Controller c = new Controller();
    
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("ici");
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        System.out.println("ici");
        Scene scene = new Scene(root);
        boolean add = scene.getStylesheets().add("style.css");
        System.out.println("ici");
        stage.setScene(scene);
        stage.show();
        System.out.println("ici");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        // TODO code application logic here
        //lancementJeu();
        lancementJeuGraphique();
        launch(args);
    }

    /*
    @Override
    public void start(Stage stage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    
}
