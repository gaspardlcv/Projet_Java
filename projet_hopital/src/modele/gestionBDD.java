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
import java.sql.SQLException;
import java.util.ArrayList;

public class gestionBDD {
    private Connexion local;
    private ArrayList<Object> table;
    
    public gestionBDD() throws ClassNotFoundException, SQLException{
        local = new Connexion("projet_java","root","");
       
    }
    
    
    public void remove(int numero_objet)
    {
        
    }
    
    public void add()
    {

    }
    
    public void modify()
    {

    }

    /**
     * @return the local
     */
    public Connexion getLocal() {
        return local;
    }

    /**
     * @return the table
     */
    public ArrayList<Object> getTable() {
        return table;
    }
    
    
}
