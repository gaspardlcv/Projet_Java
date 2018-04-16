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
public class Hospitalisation {
    
    private int id_malade;
    private int id_service;
    private int id_chambre;
    private int lit;
    
    public Hospitalisation(int id_malade,int id_service,int id_chambre,int lit)
    {
        this.id_chambre=id_chambre;
        this.id_malade=id_malade;
        this.id_service=id_service;
        this.lit=lit;
    }
}
