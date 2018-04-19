/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

/**
 *
 * @author gaspa
 */

import controleur.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.*;
import vue.BDDgestion_vue;
import vue.Connection_page;

public class Projet_hopital {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            gestionBDD bdd = new gestionBDD();
            BDDgestion_vue bddvue = new BDDgestion_vue();
            Connection_page co = new Connection_page();
            //bdd.remove(4);
            //ArrayList<String> n=new ArrayList();
            //n.add("docteur");n.add("26");n.add("Traumatologue");
            //bdd.add(n);
        } catch (SQLException ex) {
            System.out.println("pas pu se connecter     SQLException ex");
            while (ex != null) { 
            String message = ex.getMessage(); 
            String sqlState = ex.getSQLState(); 
            int errorCode = ex.getErrorCode(); 
            System.out.println("Message = "+message); 
            System.out.println("SQLState = "+sqlState); 
            System.out.println("ErrorCode = "+errorCode); 
            ex.printStackTrace(); 
            ex = ex.getNextException(); 
            } 
        }
        catch (ClassNotFoundException ex) {
            System.out.println("pas pu se connecter     ClassNotFoundException ex");
        } 
        
    }
    
}
