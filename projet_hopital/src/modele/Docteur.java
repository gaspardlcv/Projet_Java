/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author gaspa
 */
public class Docteur extends Employe{
    private String specialite;
    
    public Docteur(String specialite, int id, String nom, String prenom, String adresse)
    {
        super( id,  nom,  prenom,  adresse);
        this.specialite=specialite;
    }
    
}
