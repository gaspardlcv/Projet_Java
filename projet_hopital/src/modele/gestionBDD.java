/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author gaspard
 */

import connexion.*;
import controleur.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class gestionBDD {
    Connexion local;
    ArrayList<String> table;
    
    public gestionBDD() throws ClassNotFoundException, SQLException{
        local = new Connexion("projet_java","root","");
        
        table = local.remplirChampsTable("docteur");
        for(String elem : table){
        System.out.println(elem+"  ");
        
        }
        table = local.remplirChampsRequete("SELECT * FROM docteur");
        for(String elem : table){
        System.out.println(elem+"  ");
        
        }
    }
    
    
}
