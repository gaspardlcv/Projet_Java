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
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.*;

public class Projet_hopital {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            gestionBDD bdd = new gestionBDD();
            
        } catch (SQLException ex) {
            System.out.println("pas pu se connecter     SQLException ex");
        }
        catch (ClassNotFoundException ex) {
            System.out.println("pas pu se connecter     ClassNotFoundException ex");
        } 
        
    }
    
}