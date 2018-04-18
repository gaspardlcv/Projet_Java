/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author LAM
 */

public class Connection_page extends JFrame implements ActionListener{
    //Le panel faisant la taille de toute la fenetre
    private JPanel panel = new JPanel();
    //Le panel avec formulaire pour se connecter 
    private JPanel panel_boutons = new JPanel();
    //Le panel du titre
    private JPanel panel_bloc = new JPanel();
    
    //Bouton si cliqu envoie vers le menu principal
    private JButton valid = new JButton("Valider");
    //Bouton si clique, quitte le programme
    private JButton quit = new JButton("Quitter");
    
    //Lignes de texte pour remplir formulaire
    private JTextField id = new JTextField(null);
    private JTextField mdp = new JTextField(null);
   
    private JLabel identifiant = new JLabel("Identifiant :");
    private JLabel code = new JLabel("Mot de passe :");
    private JLabel titre = new JLabel("Bienvenue ");
    
    //Changement de taillet et de font pour le titre
    private Font taille_titre = new Font("Arial", Font.PLAIN, 50);
    
    //Constructeur de fenêtre
    public Connection_page(){
        
        //On appelle la classe mere JFrame
        super("Fenetre de connection");
  
        //On initialise la taille de la fenetre
        this.setSize(800, 600);
        //Positionnenment de la fenetre a l'ecran
        this.setLocationRelativeTo(null);
        //
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Disposition des panels les unes en dessous des autres
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        
        //On ajoute les boutons dans le panel
        valid.addActionListener(this);
        quit.addActionListener(this);
        
        //Changement de couleur du fond des panels
        panel_bloc.setBackground(Color.yellow);
        panel_boutons.setBackground(Color.green);
        
        //Taille du panel
        panel_bloc.setPreferredSize(new Dimension(10,10));
        
        //Par défaut, on place le bouton dans le premier panel qui est dans la fenêtre
        this.panel.add(this.panel_bloc);
        this.panel_bloc.add(this.titre);
        
        //On change la taille du titre
        this.titre.setFont(taille_titre);
        
        //Changement de taille des textfields
        id.setPreferredSize(new Dimension(150,30));
        mdp.setPreferredSize(new Dimension(150,30));
        
        //On ajoute le formulaire dans le panel du bas
        panel_boutons.add(identifiant);
        panel_boutons.add(id);
        panel_boutons.add(code);
        panel_boutons.add(mdp);
        panel_boutons.add(valid);
        panel_boutons.add(quit);
        
        //On ajoute le panel du bas sur le panel principal
        this.panel.add(this.panel_boutons);
        
        //On affiche le panel principal
        this.setContentPane(panel);
       
        //On rend visibles les panels
        this.setVisible(true);
    }
    
    
    //Méthode qui change le panel de fenêtre
    public void changerMenu(){
        try {
            // this.setContentPane(this.panel2);
            Menu_page menu = new Menu_page();
            BDDgestion_vue pp = new BDDgestion_vue();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection_page.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Connection_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.revalidate();
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        String login = id.getText();
        String password = mdp.getText();
        
       
        boolean  essai = Pattern.compile(login).matcher("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$").matches();
        
        //Si on clique sur Valider changement de fenetre
        if(source == valid)
        {
            if(login != null && password != null){
                changerMenu();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Mot de passe incorrect ! ","Error",1);
            }
            
                
        }
        
        //Si on clique sur Quitter fenetre se ferme
        if(source == quit)
        {
            exit(0);
        }
    }
    
   


     
 
}