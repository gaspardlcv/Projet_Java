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
public class Service {
    private int code;
    private String nom;
    private int directeur;
    private String batiment;
    
    public Service(int code,String nom, int directeur,String batiment)
    {
        this.batiment=batiment;
        this.code=code;
        this.directeur=directeur;
        this.nom=nom;
    }
    
    
}
