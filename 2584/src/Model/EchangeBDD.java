/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Louis
 */
public class EchangeBDD {
    private static String connectUrl = "jdbc:mysql://localhost/java";
    private static String username = "root";
    private static String password = "";
    private static Connection con = null;
    private static String query;
    private static ResultSet rs;
    
    public static void recupParties(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(connectUrl, username, password);
            System.out.println("Database connection established.");

            Statement stmt = con.createStatement();

            //recupération des 10 dernières parties
            System.out.println("recup id et tuile max des 10 dernières parties");
            query = "SELECT id, valeurMax FROM `partie` ORDER BY ID desc  LIMIT 10 ";
            ArrayList<String> id = new ArrayList<>();
            ArrayList<String> valeurMax = new ArrayList<>();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                id.add(rs.getString("id"));
                valeurMax.add(rs.getString("valeurMax"));
            }

            System.out.println("Historique des parties : ");
            for (int i=0; i<id.size();i++){
                //requete recupération des scores et nombre de déplacements
                query = "SELECT score, deplacement FROM Joueur WHERE id_partie=" + id.get(i);
                ArrayList<String> score = new ArrayList<>();
                ArrayList<String> deplacement = new ArrayList<>();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    score.add(rs.getString("score"));
                    deplacement.add(rs.getString("deplacement"));
                }
                System.out.println("Valeur max de la partie : " + valeurMax.get(i));
                for (int j=0 ; j<score.size() ; j++){
                    System.out.println("Joueur " + (j+1) + " : score : " + score.get(j) + " , Nombre de déplacements : " + deplacement.get(j));
                }
                System.out.println("------------------");
            }

        } catch (ClassNotFoundException cnfe) {
            System.out.println("Cannot load db driver: com.mysql.jdbc.Driver");
            cnfe.printStackTrace();
        } catch (SQLException se) {
            System.out.println("Avez-vous pensé à démarrer EasyPHP/Wamp ?");
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur inattendue");
            e.printStackTrace();
        } finally {
            // à la fin, on ferme la connection avec la BdD
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Database connection terminated.");
                } catch (Exception e) { /* ignore close errors */ }
            }
        }
    }
}
