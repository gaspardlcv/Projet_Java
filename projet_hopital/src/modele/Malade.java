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
public class Malade {
    private int id;
    private String nom;
    private String prenom;
    private int tel;
    private String adresse;
    private String mutuelle;
    private int id_docteur;
    
    public Malade(int id,String nom,String prenom,int tel,String adresse,String mutuelle,int id_docteur)
    {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.tel=tel;
        this.adresse=adresse;
        this.mutuelle=mutuelle;
        this.id_docteur=id_docteur;
    }
    
    
}
