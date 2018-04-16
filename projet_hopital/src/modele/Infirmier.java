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
public class Infirmier extends Employe{
    private int code_service;
    private String rotation;
    private int salaire;
    
    public Infirmier(int code_service, String rotation, int salaire,int id, String nom, String prenom, String adresse)
    {
        super( id,  nom,  prenom,  adresse);
        this.code_service=code_service;
        this.rotation=rotation;
        this.salaire=salaire;
    }
}
