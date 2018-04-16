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
public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    
    public Employe(int id, String nom, String prenom, String adresse)
    {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
    }
}
