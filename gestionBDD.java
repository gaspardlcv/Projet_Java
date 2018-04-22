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
    protected ArrayList<Object> table;//stocker ttes les données de la table
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
        //fin du test

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
        String element_to_delete = (String)table.get(numero_objet);
        String request="DELETE FROM `";
        String[] data = element_to_delete.split(",");
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
        //réafficher la liste table actualisé sans l'élément supprimé

    }
    
    //INSERT INTO `docteur` (`numero`, `specialite`) VALUES ('', 'Generaliste')
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
    
    //UPDATE `docteur` SET `numero` = '7', `specialite` = 'Pneumologue' WHERE CONCAT(`docteur`.`numero`) = '4'
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
