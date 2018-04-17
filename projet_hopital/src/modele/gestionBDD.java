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
    private ArrayList<Object> table;//données de la table
    private ArrayList<Object> champs_table;//nom des propriétes de la table
    private String nom_table;//nom de la table recherchée obetnue dnas le menu de recherche
    
    public gestionBDD() throws ClassNotFoundException, SQLException{
        local = new Connexion("projet_java","root","");
        
        ///test pour bosser sur un exemple (docteur ici)
        champs_table = local.remplirChampsTable("docteur");
        for(Object elem : champs_table){
        System.out.println(elem+"  ");
        }

        table = local.remplirChampsRequete("SELECT * FROM docteur");
        for(Object elem : table){
        System.out.println(elem+"  ");
        }
        nom_table="docteur";
        ///fin du test

    }
    
    
    public void remove(int numero_objet) throws SQLException
    {
        ///test
        String request="DELETE FROM ";
        request+=nom_table;
        //convertir object en string
        // extraire id donc tout avant la virgule
        //
        /*table[]=4;
        
        request+="WHERE condition";
        
        local.ajouterRequeteMaj(request);
        local.executeUpdate(request);
        table.remove(numero_objet);*/
        //réafficher la liste table actualisé sans l'élément suprrimé

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
