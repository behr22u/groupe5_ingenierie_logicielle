/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Grille;
import Model.SuitesMathematiques;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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
        // TODO
    }    
}
