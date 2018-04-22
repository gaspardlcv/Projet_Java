/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modele.gestionBDD;

/**
 *
 * @author LAM
 */
public class Modifier_page extends JFrame implements ActionListener{
    
    private gestionBDD gestion_modifier;
            
    private JPanel panel_modifier = new JPanel();
    private JPanel panel_modifier_1 = new JPanel();
        
    public void Modifier_page(){
        lancer_page();
    }
    
    public void lancer_page(){
        this.setSize(800, 600);
           
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*Titre de la page*/
        this.setTitle("Fenetre de modification");
            
        /*On rend visible la deuxieme page*/
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
