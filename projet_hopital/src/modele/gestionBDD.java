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
    
    // recherches de base 
    protected java.util.List<JCheckBox> champsCoches= new ArrayList();
    protected java.util.List<CustomTextField> champs_recherche= new ArrayList();
    
    //recherches "compliquées"
    protected java.util.List<JCheckBox> barreCheck2 = new ArrayList();
    
    
    public gestionBDD() throws ClassNotFoundException, SQLException{
        local = new Connexion("hopital","root","");
        
        //Ajout STeven
        remplirTables();
        //Ajout STeven
        
        ///test pour bosser sur un exemple (docteur ici)
        champs_table = new ArrayList<Object>();
        table = new ArrayList<Object>();
        nom_table="";
        ///fin du test

    }
    
    public gestionBDD(String nom_table) throws ClassNotFoundException, SQLException{
        local = new Connexion("hopital","root","");
        
        //Ajout STeven
        remplirTables();
        
        //Ajout STeven
        
        ///test pour bosser sur un exemple (docteur ici)
        champs_table = local.remplirChampsTable(nom_table);
        for(Object elem : champs_table){
        System.out.println(elem+"  ");
        }

        table = local.remplirChampsRequete("SELECT * FROM " + nom_table);
        for(Object elem : table){
        System.out.println(elem+"  ");
        }
        this.nom_table=nom_table;
        //fin du test

    }
    
    /**
     * 
     * Constructeur de la classe gestion avec la BDD en ligne
     * @param username 
     * @param password
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.lang.ClassNotFoundException Rejete si il n'arrvie pas à se connecter à la base de donnée locale
     * @throws java.sql.SQLException    Rejete si il n'arrvie pas à se connecter à la base de donnée locale
     */
    public gestionBDD(String username, String password,String loginDatabase,String passwordDatabase) throws ClassNotFoundException, SQLException{
        local = new Connexion( username, password, loginDatabase, passwordDatabase);
        
        //Ajout STeven
        remplirTables();
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
    
    
    
    /**
     * 
     * @throws java.sql.SQLException si le sql ne contient rien
     */
    public String[] getcleprimaire() throws SQLException
    {
        String[] splitchamps;
        String champs= (String)champs_table.get(0);
        champs=champs.substring(1);
        splitchamps = champs.split(" ");
        return splitchamps;
    }
    
    /**
     * Supprime un objet dans une table de la BDD
     * @param numero_objet indice de l'objet à supprimer dans le arraylist table
     * @throws java.sql.SQLException    Rejete s'il y a un problème concernant la requete DELETE FROM
     */
    public void remove(int numero_objet) throws SQLException
    {
        String[] champs;
        String element_to_delete= (String)table.get(numero_objet);
        String request="DELETE FROM `";
        String[] data=element_to_delete.split(",");;
        request+=nom_table;
        request+="`";
        champs=getcleprimaire();  
        request+=" WHERE";
        for(int i = 0; i< champs.length;i++){
                if(i>=1){
                    request+=" AND";
                }
                //ajouter le champs a comparer dans la requete exemple: 'id'
                request+=" CONCAT(`"+nom_table+"`.`" +champs[i]+"`)" ;
                //ajouter la donnée a comparer dans la requete exemple: ''
                request+=" ='" +data[i]+"'" ;
                request=request.replaceAll("[\r\n]+", "");
                
        }
        System.out.println(request);  
        local.executeUpdate(request);
        
        if(("docteur".equals(nom_table))||("infirmier".equals(nom_table)))
        {
            String second_request="DELETE FROM `employe`  WHERE";
            second_request+=" CONCAT(`employe`.`numero`)" ;
            //ajouter la donnée a comparer dans la requete exemple: ''
            second_request+=" ='" +data[0]+"'" ;
            second_request=second_request.replaceAll("[\r\n]+", "");             
            System.out.println(second_request); 
            System.out.println(data[1]);
            local.executeUpdate(second_request);
        }
        //réafficher la liste table actualisé sans l'élément suprrimé

    }
    
    /**
     * Ajoute un objet dans une table de la BDD
     * @param data contient les nouvelles données à ajouter avec à data.get(0) le type de donnée (ex: medecin,infirmier...)
     * @throws java.sql.SQLException    Rejete si il n'arrvie pas à se connecter à la base de donnée locale
     */
    public void add(ArrayList<String> data) throws SQLException
    {
        ArrayList<Object> champs;
        String[] champs_nom;
        String request="INSERT INTO `";
        request+=data.get(0);
        request+="` (";
        champs = local.remplirChampsTable(data.get(0));
        String c=(String)champs.get(0);
        c=c.substring(1);
        champs_nom = c.split(" ");
        
        if(("docteur".equals(data.get(0)))||("infirmier".equals(data.get(0))))
        {
            String second_request="INSERT INTO `employe` (";
            ArrayList<Object> second_champs = local.remplirChampsTable("employe");
            String[] second_champs_nom;
            String second_c=(String)second_champs.get(0);
            second_c=second_c.substring(1);
            second_champs_nom = second_c.split(" ");
            for(int i=1;i<6;i++)
            {
                if(i>=2)
                    second_request+=",";
                second_request+="`" + second_champs_nom[i-1]+ "`";
            }
            second_request+=") VALUES (";
            for(int i=1;i<6;i++)
            {
                if(i>=2)
                    second_request+=",";
                second_request+="'" + data.get(i)+ "'";
            }
            second_request+=")";
            second_request=second_request.replaceAll("[\r\n]+", "");
            System.out.println(second_request);
            local.executeUpdate(second_request);
            for(int i=1;i<5;i++)
            {
                data.remove(2);
            }
        }
        
        for(int i=0;i<data.size()-1;i++)
        {
            if(i>=1)
                request+=",";
            request+="`" + champs_nom[i]+ "`";
        }
        request+=") VALUES (";
        for(int i=1;i<data.size();i++)
        {
            if(i>=2)
                request+=",";
            request+="'" + data.get(i)+ "'";
        }
        request+=")";
        request=request.replaceAll("[\r\n]+", "");
        System.out.println(request);
        local.executeUpdate(request);
    }
    
    /**
     * Modifie un objet dans une table de la BDD
     * @param numero_objet indice de l'objet à supprimer dans le arraylist table
     * @param data contient les nouvelles données à modifier avec à data.get(0) le type de donnée (ex: medecin,infirmier...)
     * @throws java.sql.SQLException    Rejete si il n'arrvie pas à se connecter à la base de donnée locale
     */
    public void modify(int numero_objet, ArrayList<String> data) throws SQLException
    {
        String element_to_delete= (String)table.get(numero_objet);
        String[] ancient_data=element_to_delete.split(",");
        ArrayList<Object> champs;
        String[] champs_nom;
        String request="UPDATE `";
        request+=data.get(0);
        request+="` SET ";
        champs = local.remplirChampsTable(data.get(0));
        String c=(String)champs.get(0);
        c=c.substring(1);
        champs_nom = c.split(" ");
        
        
        for(int i=0;i<data.size()-1;i++)
        {
            if(i>=1)
                request+=", ";
            request+="`" + champs_nom[i]+ "`=";
            request+="'" + data.get(i+1)+ "'";
        }
        request+=" WHERE";
        for(int i = 0; i< data.size()-1;i++){
                if(i>=1){
                    request+=" AND";
                }
                //ajouter le champs a comparer dans la requete exemple: 'id'
                request+=" CONCAT(`"+data.get(0)+"`.`" +champs_nom[i]+"`)" ;
                //ajouter la donnée a comparer dans la requete exemple: ''
                request+=" ='" +ancient_data[i]+"'" ;      
        }
        request=request.replaceAll("[\r\n]+", "");
        System.out.println(request);
        local.executeUpdate(request);
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
