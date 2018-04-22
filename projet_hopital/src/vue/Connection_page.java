/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;



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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author LAM
 */

public class Connection_page extends JFrame implements ActionListener{
    /*Le panel faisant la taille de toute la fenetre*/
    private JPanel panel = new JPanel();
    /*e panel avec formulaire pour se connecter */
    private JPanel panel_boutons = new JPanel();
    /*Le panel du titre*/
    private JPanel panel_bloc = new JPanel();
    
    /*Bouton si cliqu envoie vers le menu principal*/
    private JButton valid = new JButton("Valider");
    /*Bouton si clique, quitte le programme*/
    private JButton quit = new JButton("Quitter");
    
    /*Lignes de texte pour remplir formulaire*/
    private JTextField id = new JTextField(null);
    private JTextField mdp = new JTextField(null);
   
    private JLabel identifiant = new JLabel("Identifiant :");
    private JLabel code = new JLabel("Mot de passe :");
    private JLabel titre = new JLabel("BIENVENUE ");
    
    /*Changement de taillet et de font pour le titre*/
    private Font taille_titre = new Font("Arial", Font.PLAIN+Font.BOLD, 50);
    private Font taille_form = new Font("Vernon Adams", Font.PLAIN+Font.BOLD, 20);
    private Font taille_bouton = new Font("Vernon Adams", Font.PLAIN+Font.BOLD, 20);
  
     
/*Constructeur de fenêtre*/
    public Connection_page(){
        
        /*On appelle la classe mere JFrame*/
        super("Fenetre de connexion");
  
        /*On initialise la taille de la fenetre*/
        //this.setSize(800, 600);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        /*Positionnenment de la fenetre a l'ecran*/
        this.setLocationRelativeTo(null);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         /*Creation de sous panel*/
        JPanel panel_form_1 = new JPanel();
        panel_form_1.setPreferredSize(new Dimension(0,300));
        JPanel panel_form_2 = new JPanel();
        panel_form_2.setPreferredSize(new Dimension(0,500));
        JPanel panel_form_3 = new JPanel();
        panel_form_3.setPreferredSize(new Dimension(0,500));
        
        /*Disposition des panels les unes en dessous des autres*/
        panel_boutons.setLayout(new BoxLayout(panel_boutons, BoxLayout.PAGE_AXIS));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
      
      
        /*On ajoute une image à la page*/
        ImageIcon icon1 = new ImageIcon("C:\\Users\\LAM\\Documents\\NetBeansProjects\\project_graph\\hopital.png");
        ImageIcon icon2 = new ImageIcon("C:\\Users\\LAM\\Documents\\NetBeansProjects\\project_graph\\logo.png");
        
        JLabel img1 = new JLabel(icon1);
        JLabel img2 = new JLabel(icon2);
       
 
        /*On ajoute les boutons dans le panel*/
        valid.addActionListener(this);
        quit.addActionListener(this);
        
        /*Repositionnement des boutons et des textFields par rapports aux bords de chaque panel*/
        panel_form_1.setBorder(BorderFactory.createEmptyBorder(80,0,0,0));
        panel_form_3.setBorder(BorderFactory.createEmptyBorder(60,0,0,0));
        
        /*Changement de couleur du fond des panels*/
        panel_bloc.setBackground(new Color(158,197,236));
        panel_form_1.setBackground(new Color(0,171,159));
        panel_form_2.setBackground(new Color(0,171,159));
        panel_form_3.setBackground(new Color(158,197,236));
        
         
        /*Changement de taille des textfields*/
        id.setPreferredSize(new Dimension(180,30));
        mdp.setPreferredSize(new Dimension(150,30));
        
        /*Changement de taille des boutons*/
        valid.setPreferredSize(new Dimension(100,50));
        quit.setPreferredSize(new Dimension(100,50));
        
        /*Changement de taille ecriture des boutons*/
        this.valid.setFont(taille_bouton);
        this.quit.setFont(taille_bouton);
        
        /*On change la taille du titre*/
        this.titre.setFont(taille_titre);
        this.identifiant.setFont(taille_form);
        this.code.setFont(taille_form);    
        
        /*On change la couleur du titre*/
        this.titre.setForeground(new Color(255,255,255));
        
        /*On change la taille de l'image*/
        img1.setPreferredSize(new Dimension(150,150));
        
        /*On ajoute l'image*/
        panel_bloc.add(img1);
        panel_bloc.add(img2);
         
        /*Par défaut, on place le bouton dans le premier panel qui est dans la fenêtre*/
        this.panel.add(this.panel_bloc);
        this.panel_bloc.add(this.titre);
 
       /*On ajoute le formulaire dans le panel du bas*/
        panel_form_1.add(identifiant);
        panel_form_1.add(id);
        panel_form_2.add(code);
        panel_form_2.add(mdp);
        panel_form_3.add(valid);
        panel_form_3.add(quit);
        
        panel_boutons.add(panel_form_1);
        panel_boutons.add(panel_form_2);
        panel_boutons.add(panel_form_3);
        
       
    
        /*On ajoute le panel du bas sur le panel principal*/
        this.panel.add(this.panel_boutons);
        
        /*On affiche le panel principal*/
        this.setContentPane(panel);
       
        /*On rend visibles les panels*/
        this.setVisible(true);
    }
    
    
    /*Méthode qui change le panel de fenêtre*/
    public void changerMenu(){
        try {
            // this.setContentPane(this.panel2);
            Menu_page menu = new Menu_page();
          //  BDDgestion_vue pp = new BDDgestion_vue();
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

        
        /*Si on clique sur Valider changement de fenetre*/
        if(source == valid){
            /*On blinde la saisie de l'utilisateur en local*/
            if(id.getText().equals("root") && mdp.getText().equals("")){
                changerMenu();
  
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Identifiant ou mot de passe incorrect ! ","Error",1);
               
            }
        }   
         
         /*Si on clique sur Quitter fenetre se ferme*/
        if(source == quit)
        {
            exit(0);
        }
    }

}
