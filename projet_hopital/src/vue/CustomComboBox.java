/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import javax.swing.JComboBox;

/**
 *
 * @author Steven
 */
public class CustomComboBox extends JComboBox {
    
    private String nom_table;

    public String getNomTable()
    {
        if("docteur".equals((String)getSelectedItem()))
        {
            nom_table = "(SELECT employe.nom, employe.prenom, employe.numero, employe.adresse, employe.tel, docteur.specialite\n" +
"      FROM docteur\n" +
"      INNER JOIN employe\n" +
"        on docteur.numero=employe.numero) as s";
        }
        
        else if("chambre".equals((String)getSelectedItem()))
        {
            nom_table="(SELECT chambre.code_service, chambre.no_chambre, chambre.surveillant, chambre.nb_lits, hospitalisation.no_malade, hospitalisation.lit\n" +
"      FROM chambre\n" +
"      INNER JOIN hospitalisation\n" +
"        on (chambre.no_chambre=hospitalisation.no_chambre AND chambre.code_service=hospitalisation.code_service)) as s";
        }
        
        else if("hospitalisation".equals((String)getSelectedItem()))
        {
            nom_table = "(select hospitalisation.no_malade, hospitalisation.code_service, hospitalisation.no_chambre, hospitalisation.lit, malade.nom, malade.prenom, malade.adresse, malade.tel, malade.mutuelle\n" +
"      FROM hospitalisation\n" +
"      INNER JOIN malade\n" +
"         ON (malade.numero=hospitalisation.no_malade)) as s";
        }
        
        else if("infirmier".equals((String)getSelectedItem()))
        {
            nom_table = "(select infirmier.numero, infirmier.code_service, infirmier.rotation, infirmier.salaire, employe.nom, employe.prenom, employe.adresse, employe.tel\n" +
"      FROM infirmier\n" +
"      INNER JOIN employe\n" +
"         ON (employe.numero=infirmier.numero)) as s";
        }
        
        else if("service".equals((String)getSelectedItem()))
        {
            nom_table = "(select service.code, service.nom, service.batiment, service.directeur, employe.prenom, employe.adresse, employe.tel\n" +
"      FROM service\n" +
"      INNER JOIN employe\n" +
"         ON (employe.numero=service.directeur)) as s";
        }
        
        else
            nom_table=(String) getSelectedItem();
        
        return nom_table;
    }
    
    

    
    
    
    
}
