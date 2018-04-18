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
import javax.swing.JCheckBox;

public class gestionBDD {
    protected Connexion local;
    protected ArrayList<Object> table;//données de la table
    protected ArrayList<Object> champs_table;//nom des propriétes de la table
    protected String nom_table;//nom de la table recherchée obetnue dnas le menu de recherche
    
    protected java.util.List<JCheckBox> champsCoches= new ArrayList();
    
    public gestionBDD() throws ClassNotFoundException, SQLException{
        local = new Connexion("hopital","root","");
        
        //Ajout STeven
        remplirTables();
        //Ajout STeven
        
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
    
    //AJout steven
    private void remplirTables() {
        
        local.ajouterTable("chambre");
        local.ajouterTable("docteur");
        local.ajouterTable("employe");
        local.ajouterTable("hospitalisation");
        local.ajouterTable("infirmier");
        local.ajouterTable("malade");
        local.ajouterTable("service");
        local.ajouterTable("soigne");
    }
    
    public ArrayList retourLignes()
    {
        
        ArrayList champsChoisis = new ArrayList(); // champs cochés à retourner
        for(int i=0; i<champsCoches.size();i++)
        {
            if((!"".equals(champsCoches.get(i).getText())) && champsCoches.get(i).isSelected())
                champsChoisis.add(champsCoches.get(i).getText());
        }
        return champsChoisis;
        
    }
    
    //Ajout Steven
    
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
