/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

/**
 *
 * @author gaspa
 */

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modele.*;

public class BDDgestion_vue extends gestionBDD implements ActionListener{

    ArrayList<JPanel> docteurs;
    JFrame panneau = new JFrame();
    JTextField texte= new JTextField();
            
    public BDDgestion_vue() throws ClassNotFoundException, SQLException
    {
        super();
        panneau.setTitle("Hopital");
        panneau.setLayout(new BoxLayout(panneau, BoxLayout.PAGE_AXIS));
        panneau.setSize(1200,800);
        panneau.setResizable(false);
        panneau.setLocationRelativeTo(null);
        panneau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public void display()
    {
        /*for(String elem : this.getTable()){
        texte.setText(elem);  
        docteurs.add(texte);
        }*/
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
