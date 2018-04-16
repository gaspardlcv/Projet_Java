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
public class Chambre {
    private int id_chambre;
    private int id_service;
    private Infirmier surveillant;
    private int nb_lits;
    
    public Chambre(int id_chambre,int id_service,Infirmier surveillant,int nb_lits)
    {
        this.id_chambre=id_chambre;
        this.id_service=id_service;
        this.nb_lits=nb_lits;
        this.surveillant=surveillant;
    }
    
}
