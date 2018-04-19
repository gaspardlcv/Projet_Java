/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author LAM
 */
public class Menu_page extends JFrame  {
    //Panel du menu
    private JPanel panel2 = new JPanel();
    
    //Panels pour chaque onglet
    private JPanel panel_recherche = new JPanel();
    
    private JPanel panel_ajout = new JPanel();
    private JPanel panel_ajout_1 = new JPanel();
    private JPanel panel_ajout_2 = new JPanel();
    private JPanel panel_ajout_3 = new JPanel();
    private JPanel panel_ajout_4 = new JPanel();
    
    private JPanel panel_delete = new JPanel();
    private JPanel panel_reporting = new JPanel();
    
    //Liste deroulante de recherche
    private JComboBox combo_find = new JComboBox();
    private JComboBox combo_add = new JComboBox();
    
    //Affiche label
    private JLabel label_recherche = new JLabel("Recherche : ");
    private JLabel label_ajout = new JLabel("Ajouter : ");
    
    private JTextField txt_numero = new JTextField(null);
    private JTextField txt_nom = new JTextField(null);
    private JTextField txt_prenom = new JTextField(null);
    private JTextField txt_tel = new JTextField(null);
    private JTextField txt_adresse = new JTextField(null);
    private JTextField txt_mutuelle = new JTextField(null);
    private JTextField txt_specialite = new JTextField(null);
    
    private JLabel label_numero = new JLabel("Numéro ");
    private JLabel label_nom = new JLabel("Nom ");
    private JLabel label_prenom = new JLabel("Prénom ");
    private JLabel label_tel = new JLabel("Numéro de téléphone ");
    private JLabel label_adresse = new JLabel("Adresse ");
    private JLabel label_mutuelle = new JLabel("Mutuelle ");
    private JLabel label_specialite = new JLabel("Spécialité ");
   
    
    //Onglets du menu
    private JTabbedPane panelOnglet = new JTabbedPane();
    
    private JButton confirm = new JButton("Confirmer");
    
    
    public Menu_page(){
        //On appelle la classe mere JFrame pour la deuxieme page
        super("Fenetre de menu");
        
        //On appelle la methode pour lancer le menu
        lancement_menu();
    }
    
    
    
    public void lancement_menu()
    {
        //Meme caracteristiques que la premiere page
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         //Titre de la page
        this.setTitle("Fenetre du menu");
        
         //Disposition des panels les unes en dessous des autres
     //   panel_ajout.setLayout(new BoxLayout(panel_ajout, BoxLayout.PAGE_AXIS));
        
        //On affiche la deuxieme page
       // this.setContentPane(panel2);
        
        //On change couleur du fond du panel
        this.panel2.setBackground(Color.blue);
        
         //Ajout des onglets
      //  panelOnglet.addTab("Recherche", null, new testpanel());
        panelOnglet.addTab("Recherche", null, panel_recherche);
        panelOnglet.addTab("Ajouter", null, panel_ajout);
        panelOnglet.addTab("Supprimer", null, panel_delete);
        panelOnglet.addTab("Reporting", null, panel_reporting);
        
        //
        onglet_recherche();
        onglet_ajout();
        
        //
        this.getContentPane().add(panelOnglet);
       
        
        
      /*  switch(select_add)
        {
            case "Malade" :
                panel_ajout.add(label_numero);
                panel_ajout.add(txt_numero);
                break;
            default:
                System.out.println("bleble");
        }*/
        
      
      /*  panel2.add(label);
        //Ajout de la liste deroulante
        panel2.add(combo);*/
        
        //On rend visible la deuxieme page
       this.setVisible(true);
    }
   
    
    public void onglet_recherche()
    {
        //Taille de la liste deroulante
        combo_find.setPreferredSize(new Dimension(100,20));
        
         //Les champs de la liste Recherche
        combo_find.addItem("Malade");
        combo_find.addItem("Service");
        combo_find.addItem("Chambre");
        combo_find.addItem("Docteur");
        combo_find.addItem("Employe");
        combo_find.addItem("Infirmier");
        combo_find.addItem("Hospitalisation");
        combo_find.addItem("Soigne");
        
          //Ajout de la liste deroulante
        panel_recherche.add(label_recherche);
        panel_recherche.add(combo_find);
        
        //On recupere la valeur du champ selectionné
        String select_find = combo_find.getSelectedItem().toString();
    }
    
    
    public void onglet_ajout()
    {
        //Espaces des bordures
        panel_ajout.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
          
       
         
        //On redéfinit la taille des zones de texte
        txt_numero.setPreferredSize(new Dimension(150,30));
        txt_nom.setPreferredSize(new Dimension(150,30));
        txt_prenom.setPreferredSize(new Dimension(150,30));
        txt_tel.setPreferredSize(new Dimension(110,30));
        txt_adresse.setPreferredSize(new Dimension(220,30));
        txt_mutuelle.setPreferredSize(new Dimension(100,30));
        
        
        //Layout en  
   //     panel_ajout.setLayout(new BoxLayout(panel_ajout, BoxLayout.PAGE_AXIS));
        panel_ajout.setLayout(new BorderLayout());
      //  panel_ajout.setLayout(new GridLayout(2,3));
        
        //Les champs de la liste Ajout
        combo_add.addItem("Fonction");
        combo_add.addItem("Docteur");
        combo_add.addItem("Malade");
        combo_add.addItem("Infirmier");
        
        combo_add.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                Object source = arg0.getSource();
                JComboBox comboBox = (JComboBox)source;
                switch(comboBox.getSelectedIndex()){
                    case 0:
                        break;
                        
                    case 1:
                        form_docteur();
                        break;
                        
                    case 2:
                        form_malade();
                        break;
                        
                    case 3:
                        form_infirmier();
                        break;
                }
                    
            }

            
            
        });
        
        //On place la liste tout en haut
        panel_ajout_1.add(label_ajout);
        panel_ajout_1.add(combo_add);
        
        
     
       //  panel_ajout.setLayout(new BoxLayout(panel_ajout, BoxLayout.PAGE_AXIS));
        panel_ajout.add(panel_ajout_1, BorderLayout.NORTH);
        panel_ajout.add(panel_ajout_2, BorderLayout.CENTER);
        panel_ajout.add(panel_ajout_3, BorderLayout.SOUTH);
      
        
        
     //   panel_ajout.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
  
       
    }
    
    
    public void form_malade()
    {
        //On nettoie la page
        panel_ajout_2.removeAll();
        
        //Layout en ligne pour le panel des zones de texte
        panel_ajout_2.setLayout(new BoxLayout(panel_ajout_2, BoxLayout.PAGE_AXIS));
        
        //Création de sous panels
        JPanel central1 = new JPanel();
        JPanel central2 = new JPanel();
        JPanel central3 = new JPanel();
        
        central1.add(label_numero);
        central1.add(txt_numero);
        
         
        central2.add(label_nom);
        central2.add(txt_nom);
        
        central2.add(label_prenom);
        central2.add(txt_prenom);
        
     
        central3.add(label_tel);
        central3.add(txt_tel);
        
        central3.add(label_adresse);
        central3.add(txt_adresse);
        
        central3.add(label_mutuelle);
        central3.add(txt_mutuelle);
        
        central1.setBackground(Color.orange);
        central2.setBackground(Color.pink);
        central3.setBackground(Color.cyan);
       
        panel_ajout_2.add(central1);
        panel_ajout_2.add(central2);
        panel_ajout_2.add(central3);
        
        
        panel_ajout_3.add(confirm);
        
        //Taille de la liste deroulante
        //combo_add.setPreferredSize(new Dimension(100,20));
        
     //   panel_ajout_2.setBackground(Color.yellow);
        this.revalidate();
        this.repaint();
    }
    
    
    public void form_docteur()
    {
        //On nettoie la page
        panel_ajout_2.removeAll();
        
        //Layout en ligne pour le panel des zones de texte
        panel_ajout_2.setLayout(new BoxLayout(panel_ajout_2, BoxLayout.PAGE_AXIS));
 
       
        JPanel central1 = new JPanel();
        JPanel central2 = new JPanel();
        JPanel central3 = new JPanel();
        JPanel central4 = new JPanel();
        
        central1.add(label_numero);
        central1.add(txt_numero);
        
         
        central2.add(label_nom);
        central2.add(txt_nom);
        
        central2.add(label_prenom);
        central2.add(txt_prenom);
        
     
        central3.add(label_tel);
        central3.add(txt_tel);
        
        central3.add(label_adresse);
        central3.add(txt_adresse);
        
        //Creation de la liste pour la spécialité
        JComboBox combo_specialite = new JComboBox();
        
        central4.add(label_specialite);
        
        //On ajoute les champs à la liste
        combo_specialite.addItem("Sélectionner");
        combo_specialite.addItem("Traumatologue");
        combo_specialite.addItem("Radiologue");
        combo_specialite.addItem("Pneumologue");
        combo_specialite.addItem("Cardiologue");
        combo_specialite.addItem("Anesthesiste");
        combo_specialite.addItem("Orthopediste");
        
        //ON affiche la liste sur le panel correspondant
        central4.add(combo_specialite);
        
        //On donne une couleur de fond pour chaque panel
        central1.setBackground(Color.lightGray);
        central2.setBackground(Color.green);
        central3.setBackground(Color.red);
        central4.setBackground(Color.yellow);
       
        //Ajout des sous-panels sur le panel principal
        panel_ajout_2.add(central1);
        panel_ajout_2.add(central2);
        panel_ajout_2.add(central3);
        panel_ajout_2.add(central4);
      
        panel_ajout_3.add(confirm);
        
        //Taille de la liste deroulante
        //combo_add.setPreferredSize(new Dimension(100,20));
        
        //panel_ajout_2.setBackground(Color.yellow);
        this.revalidate();
        this.repaint();
    }
    
    
    public void form_infirmier()
    {
        //On nettoie la page
        panel_ajout_2.removeAll();
        
        //Layout en ligne pour le panel des zones de texte
        panel_ajout_2.setLayout(new BoxLayout(panel_ajout_2, BoxLayout.PAGE_AXIS));
 
        //Creation des sous panels
        JPanel central1 = new JPanel();
        JPanel central2 = new JPanel();
        JPanel central3 = new JPanel();
        JPanel central4 = new JPanel();
        
        central1.add(label_numero);
        central1.add(txt_numero);
        
         
        central2.add(label_nom);
        central2.add(txt_nom);
        
        central2.add(label_prenom);
        central2.add(txt_prenom);
        
     
        central3.add(label_tel);
        central3.add(txt_tel);
        
        central3.add(label_adresse);
        central3.add(txt_adresse);
        
        //Creation de la liste pour les infirmiers
        JComboBox combo_rotation = new JComboBox();
        JLabel label_rotation = new JLabel("Rotation ");
        //Creation zones de texte pour salaire et code service des infirmiers
        JLabel label_service = new JLabel("Code Service ");
        JLabel label_salaire = new JLabel("Salaire ");
        JTextField txt_service = new JTextField(null);
        JTextField txt_salaire = new JTextField(null);
        
        txt_service.setPreferredSize(new Dimension(50,30));
        txt_salaire.setPreferredSize(new Dimension(100,30));
        
        
        central4.add(label_service);
        central4.add(txt_service);
  
        //On ajoute les champs à la liste
        combo_rotation.addItem("Sélectionner");
        combo_rotation.addItem("Jour");
        combo_rotation.addItem("Nuit");
        
        //ON affiche la liste sur le panel correspondant
        central4.add(label_rotation);
        central4.add(combo_rotation);
        
        central4.add(label_salaire);
        central4.add(txt_salaire);
        
        //On donne une couleur de fond pour chaque panel
        central1.setBackground(Color.yellow);
        central2.setBackground(Color.cyan);
        central3.setBackground(Color.blue);
        central4.setBackground(Color.gray);
       
        //Ajout des sous-panels sur le panel principal
        panel_ajout_2.add(central1);
        panel_ajout_2.add(central2);
        panel_ajout_2.add(central3);
        panel_ajout_2.add(central4);
      
        panel_ajout_3.add(confirm);
        
        //Taille de la liste deroulante
        //combo_add.setPreferredSize(new Dimension(100,20));
        
        //panel_ajout_2.setBackground(Color.yellow);
        this.revalidate();
        this.repaint();
    }
    
    
    public void onglet_supp()
    {
        //Création de la liste pour le choix de suppression
        JLabel label_supp = new JLabel("Supprimer : ");
        JComboBox combo_supp = new JComboBox();
        
        //Layout
        panel_ajout.setLayout(new BorderLayout());
        
        //Espaces des bordures
        panel_ajout.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        //Insertion des champs dans la liste
        combo_supp.addItem("Sélectionner");
        combo_supp.addItem("Docteur");
        combo_supp.addItem("Malade");
        combo_supp.addItem("Infirmier");
        
        
        
        
    }
}

