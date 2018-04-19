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
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;
import vue.CustomTextField;

public class gestionBDD {
    protected Connexion local;
    protected ArrayList<Object> table;//données de la table
    protected ArrayList<Object> champs_table;//nom des propriétes de la table
    protected String nom_table;//nom de la table recherchée obetnue dnas le menu de recherche
    
    protected java.util.List<JCheckBox> champsCoches= new ArrayList();
    protected java.util.List<CustomTextField> champs_recherche= new ArrayList();
    
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
            {                
                champsChoisis.add(champsCoches.get(i).getText());
            }
        }
        return champsChoisis;
        
    }
    
    //Ajout Steven
    
    public String[] getcleprimaire() throws SQLException
    {
        String[] splitchamps;
        String champs= (String)champs_table.get(0);
        champs=champs.substring(1);
        splitchamps = champs.split(" ");
        return splitchamps;
    }
    
    public void remove(int numero_objet) throws SQLException
    {
        String[] champs;
        String element_to_delete= (String)table.get(numero_objet);
        String request="DELETE FROM ";
        String[] data=element_to_delete.split(",");;
        request+=nom_table;
        champs=getcleprimaire();
        request+=" WHERE";
        for(int i = 0; i< champs.length;i++){
                if(i>=1){
                    request+=" AND";
                }
                //ajouter le champs a comparer dans la requete exemple: 'id'
                request+=" '" +champs[i]+"'" ;
                //ajouter la donnée a comparer dans la requete exemple: ''
                request+=" ='" +data[i]+"'" ;
                
        }
        System.out.println(request);  
        table= local.remplirChampsRequete(request);
        //réafficher la liste table actualisé sans l'élément suprrimé
        for(Object elem : table){
        System.out.println(elem+"  ");
        }

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
