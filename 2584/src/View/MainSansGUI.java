/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import static Controller.Controller.lancementJeu;

/**
 *
 * @author Louis
 */
public class MainSansGUI {
    
    public static void main(String[] args){
        if(args.length == 0){
            Main.main(args);
        } else{
            lancementJeu();
        }
    }
    
}
