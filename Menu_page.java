/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import connexion.Connexion;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modele.gestionBDD;
import modele.AfficheGraph;
/**
 *
 * @author LAM
 */
public class Menu_page extends JFrame implements ActionListener  {
    /*Panel du menu*/
    private JPanel panel2 = new JPanel();
    
    private String[] nom_tab
            ;
    
    private gestionBDD gestion;
    private Connexion connect;
    /*Panels pour chaque onglet*/
    private JPanel panel_recherche = new JPanel();
    private JPanel panel_modify = new JPanel();
    private JPanel panel_delete = new JPanel();
    private JPanel panel_reporting = new JPanel();
    private JPanel panel_ajout = new JPanel();
    
    /*Les sous panels de l'onglet ajout*/
    private JPanel panel_ajout_1 = new JPanel();
    private JPanel panel_ajout_2 = new JPanel();
    private JPanel panel_ajout_3 = new JPanel();

    /*Liste deroulante de recherche*/
    private JComboBox combo_find = new JComboBox();
    private JComboBox combo_add = new JComboBox();
    private JComboBox combo_supp = new JComboBox();
    
    /*Affiche label*/
    private JLabel label_recherche = new JLabel("Recherche : ");
    private JLabel label_ajout = new JLabel("Ajouter : ");
    
    private JTextField txt_numero = new JTextField(null);
    private JTextField txt_nom = new JTextField(null);
    private JTextField txt_prenom = new JTextField(null);
    private JTextField txt_tel = new JTextField(null);
    private JTextField txt_adresse = new JTextField(null);
    private JTextField txt_mutuelle = new JTextField(null);
   
    /*Labels pour chaque bouton*/
    private JLabel label_numero = new JLabel("Numéro ");
    private JLabel label_nom = new JLabel("Nom ");
    private JLabel label_prenom = new JLabel("Prénom ");
    private JLabel label_tel = new JLabel("Numéro de téléphone ");
    private JLabel label_adresse = new JLabel("Adresse ");
    private JLabel label_mutuelle = new JLabel("Mutuelle ");
    private JLabel label_specialite = new JLabel("Spécialité ");
    
    private JTable tab_supp = new JTable();

    /*Onglets du menu*/
    private JTabbedPane panelOnglet = new JTabbedPane();
    
    private JButton confirm = new JButton("Confirmer");
    
    
     private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    
    
    public Menu_page() throws SQLException, ClassNotFoundException{
        //On appelle la classe mere JFrame pour la deuxieme page*/
        super("Fenetre de menu");
        
        /*On instancie un objet de connexion*/
        connect = new  Connexion("hopital", "root", "");
        
        DatabaseMetaData coco = null;

        coco = connect.getConn().getMetaData();
        ResultSet rs = null;

        rs = coco.getTables(connect.getConn().getCatalog(), null, "%", null);

        ArrayList nameTable = new ArrayList();
          
        while (rs.next()) {

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                String nomColonne = rs.getMetaData().getColumnName(i + 1);

                Object valeurColonne = rs.getObject(i + 1);
                if (nomColonne == "TABLE_NAME") {
                    nameTable.add(valeurColonne);
                }

            }
        }

        nom_tab = new String[nameTable.size()];

        for (int i = 0; i < nameTable.size(); i++) {
            nom_tab[i] = (String) nameTable.get(i);
        }
        
        DefaultComboBoxModel supp_modele = new DefaultComboBoxModel(nom_tab);
   
        combo_supp.setModel(supp_modele);
        
        /*On appelle la methode pour lancer le menu*/
        lancement_menu();
    }
    
    
    
    public void lancement_menu() throws ClassNotFoundException, SQLException
    {
        /*Meme caracteristiques que la premiere page*/
       // this.setSize(800, 600);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
         /*Titre de la page*/
        this.setTitle("Fenetre du menu");
    
        /*On change couleur du fond du panel*/
        this.panel2.setBackground(Color.blue);
        
         /*Ajout des onglets*/
      //  panelOnglet.addTab("Recherche", null, new testpanel());
        panelOnglet.addTab("Recherche", null, panel_recherche);
        panelOnglet.addTab("Ajouter", null, panel_ajout);
        panelOnglet.addTab("Supprimer", null, panel_delete);
        panelOnglet.addTab("Modifier", null, panel_modify);
        panelOnglet.addTab("Reporting", null, panel_reporting);
        
        /*On affiches les panels des onglets*/
        onglet_recherche();
        onglet_ajout();
        onglet_supp();
        onglet_modify();
        onglet_reporting();        
        //
        this.getContentPane().add(panelOnglet);

        
        /*On rend visible la deuxieme page*/
       this.setVisible(true);
    }
   
    
    public void onglet_recherche()
    {
        /*Taille de la liste deroulante*/
        combo_find.setPreferredSize(new Dimension(100,20));
        
        /*Les champs de la liste Recherche*/
        combo_find.addItem("Malade");
        combo_find.addItem("Service");
        combo_find.addItem("Chambre");
        combo_find.addItem("Docteur");
        combo_find.addItem("Employe");
        combo_find.addItem("Infirmier");
        combo_find.addItem("Hospitalisation");
        combo_find.addItem("Soigne");
        
        /*Ajout de la liste deroulante*/
        panel_recherche.add(label_recherche);
        panel_recherche.add(combo_find);
        
        /*On recupere la valeur du champ selectionné*/
        String select_find = combo_find.getSelectedItem().toString();
    }
    
    
    public void onglet_ajout() throws ClassNotFoundException, SQLException
    {
        /*Espaces des bordures*/
        panel_ajout.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
          
        
        
        /*On redéfinit la taille des zones de texte*/
        txt_numero.setPreferredSize(new Dimension(150,30));
        txt_nom.setPreferredSize(new Dimension(150,30));
        txt_prenom.setPreferredSize(new Dimension(150,30));
        txt_tel.setPreferredSize(new Dimension(110,30));
        txt_adresse.setPreferredSize(new Dimension(220,30));
        txt_mutuelle.setPreferredSize(new Dimension(100,30));
        
        
        //Layout en  
         //panel_ajout.setLayout(new BoxLayout(panel_ajout, BoxLayout.PAGE_AXIS));
        panel_ajout.setLayout(new BorderLayout());
      //  panel_ajout.setLayout(new GridLayout(2,3));
        
        /*Les champs de la liste Ajout*/
        combo_add.addItem("Sélectionner");
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
        
       
        
        /*On place la liste tout en haut*/
        panel_ajout_1.add(label_ajout);
        panel_ajout_1.add(combo_add);

     
       /* panel_ajout.setLayout(new BoxLayout(panel_ajout, BoxLayout.PAGE_AXIS));*/
        panel_ajout.add(panel_ajout_1, BorderLayout.NORTH);
        panel_ajout.add(panel_ajout_2, BorderLayout.CENTER);
        panel_ajout.add(panel_ajout_3, BorderLayout.SOUTH);
      

     //   panel_ajout.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
  
       
    }
    
    
    public void form_malade()
    {
        
        /*On créé une arraylist afin de stocker les infos du patient*/
        ArrayList <String> recup_infos = new ArrayList();
        
        //Creation de strings pour stocker chaque info
       // String 
        
        /*On nettoie la page*/
        panel_ajout_2.removeAll();
        
        /*Layout en ligne pour le panel des zones de texte*/
        panel_ajout_2.setLayout(new BoxLayout(panel_ajout_2, BoxLayout.PAGE_AXIS));
        
        /*Création de sous panels*/
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
        
        
        
     //   panel_ajout_2.setBackground(Color.yellow);
        this.revalidate();
        this.repaint();
    }
    
    
    public void form_docteur()
    {
        /*On nettoie la page*/
        panel_ajout_2.removeAll();
        
        /*Layout en ligne pour le panel des zones de texte*/
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
        
        /*Creation de la liste pour la spécialité*/
        JComboBox combo_specialite = new JComboBox();
        
        central4.add(label_specialite);
        
        /*On ajoute les champs à la liste*/
        combo_specialite.addItem("Sélectionner");
        combo_specialite.addItem("Traumatologue");
        combo_specialite.addItem("Radiologue");
        combo_specialite.addItem("Pneumologue");
        combo_specialite.addItem("Cardiologue");
        combo_specialite.addItem("Anesthesiste");
        combo_specialite.addItem("Orthopediste");
        
        /*ON affiche la liste sur le panel correspondant*/
        central4.add(combo_specialite);
        
        /*On donne une couleur de fond pour chaque panel*/
        central1.setBackground(Color.lightGray);
        central2.setBackground(Color.green);
        central3.setBackground(Color.red);
        central4.setBackground(Color.yellow);
       
        /*Ajout des sous-panels sur le panel principal*/
        panel_ajout_2.add(central1);
        panel_ajout_2.add(central2);
        panel_ajout_2.add(central3);
        panel_ajout_2.add(central4);
      
        panel_ajout_3.add(confirm);
        
        /*Taille de la liste deroulante*/
        //combo_add.setPreferredSize(new Dimension(100,20));
        
        //panel_ajout_2.setBackground(Color.yellow);
        this.revalidate();
        this.repaint();
    }
    
    
    public void form_infirmier()
    {
        /*On nettoie la page*/
        panel_ajout_2.removeAll();
        
        /*Layout en ligne pour le panel des zones de texte*/
        panel_ajout_2.setLayout(new BoxLayout(panel_ajout_2, BoxLayout.PAGE_AXIS));
 
        /*Creation des sous panels*/
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
        
        /*Creation de la liste pour les infirmiers*/
        JComboBox combo_rotation = new JComboBox();
        JLabel label_rotation = new JLabel("Rotation ");
        
        /*Creation zones de texte pour salaire et code service des infirmiers*/
        JLabel label_service = new JLabel("Code Service ");
        JLabel label_salaire = new JLabel("Salaire ");
        JTextField txt_service = new JTextField(null);
        JTextField txt_salaire = new JTextField(null);
        
        /*Redimension des zones de textes*/
        txt_service.setPreferredSize(new Dimension(50,30));
        txt_salaire.setPreferredSize(new Dimension(100,30));
        
        
        central4.add(label_service);
        central4.add(txt_service);
  
        /*On ajoute les champs à la liste*/
        combo_rotation.addItem("Sélectionner");
        combo_rotation.addItem("Jour");
        combo_rotation.addItem("Nuit");
        
        /*ON affiche la liste sur le panel correspondant*/
        central4.add(label_rotation);
        central4.add(combo_rotation);
        
        central4.add(label_salaire);
        central4.add(txt_salaire);
        
        /*On donne une couleur de fond pour chaque panel*/
        central1.setBackground(Color.yellow);
        central2.setBackground(Color.cyan);
        central3.setBackground(Color.blue);
        central4.setBackground(Color.gray);
       
        /*Ajout des sous-panels sur le panel principal*/
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
        /*Création de la liste pour le choix de suppression*/
        JLabel label_supp = new JLabel("Supprimer : ");
        
        /*Creation bouton pour supprimer*/
        JButton button_supp = new JButton("Effacer");
        
        combo_supp.setPreferredSize(new Dimension(100,30));
    
        
        /*Creation de sous panels*/
        JPanel panel_delete_1 = new JPanel();
        JPanel panel_delete_2 = new JPanel();
        JPanel panel_delete_3 = new JPanel();
        
        /*Layout en ligne*/
        panel_delete.setLayout(new BoxLayout(panel_delete, BoxLayout.PAGE_AXIS));
        //Espaces des bordures
        //  panel_ajout.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        /*Insertion des champs dans la liste*/
        //   combo_supp.addItem("Sélectionner");
        //   combo_supp.addItem("Docteur");
        //   combo_supp.addItem("Malade");
        //   combo_supp.addItem("Infirmier");
        
 
        /*Creation d'un listener pour la liste deroulante*/
         combo_supp.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
               
                /*On récupère le nom sélectionné de la liste*/
                String nom_supp = (String)combo_supp.getSelectedItem();
                
                int cp=0;
                int manger=0;
                
                //Creation d'une ArrayList afin de stocker les noms des champs
                ArrayList <String> l_champs = null;
                try {
                    gestion = new gestionBDD();
                    l_champs = connect.recupChampsTable(nom_supp);
                    
                    /*Creation d'une table de strings de la taille de 
                    l'ArrayList car arraylist taille variable alors que
                    tableau taille fixe*/
                    String[] tab = new String[l_champs.size()];
                
                    for(int i=0; i<l_champs.size(); i++)
                    {
                        tab[i] = l_champs.get(i);
                    }
                    
                    while(connect.getRset().next())
                    {
                        cp++;
                        
                    }
                     /*Objet contenant toutes les valeurs de la base correspondants
                    au champ choisi*/
                    Object [][] tous_valeurs = new Object[cp][tab.length];
                    
                    /*On place le curseur à la position 0*/
                    connect.getRset().beforeFirst();
                    
                    /*On lit la table sélectionnée ligne par ligne jusqu'à la fin*/
                    while(connect.getRset().next())
                    {
                        for(int i = 0; i <tab.length; i++)
                        {
                            tous_valeurs[manger][i] = connect.getRset().getString(tab[i]);
                           //System.out.println(tous_valeurs[manger][i]);
                                 
                        }
                        manger++;
                        
                    }
                     
                    DefaultTableModel table_richar = new DefaultTableModel(tous_valeurs, tab);
                     
                    tab_supp.setModel(table_richar);
      
                } catch (SQLException ex) {
                    Logger.getLogger(Menu_page.class.getName()).log(Level.SEVERE, null, ex);
     
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Menu_page.class.getName()).log(Level.SEVERE, null, ex);
                }
        
            }
        
        });
         
         /*Creation d'un listener pour le bouton permettant de supprimer
         une donnée de la BDD*/
         button_supp.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ev) {
                Object bobo = ev.getSource();
                
                if(bobo == button_supp)
                {
                    try {
                        gestion.remove(tab_supp.getSelectedRow());
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(Menu_page.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            
            }
        
        });
        
        /*On place les components dans les bons panels*/ 
        panel_delete_1.add(label_supp);
        panel_delete_1.add(combo_supp);
        
        panel_delete_2.add(tab_supp);
        panel_delete_2.add(new JScrollPane(tab_supp));
        
        panel_delete_3.add(button_supp);
        
        /*On place les sous panels dans le panel principal*/
        panel_delete.add(panel_delete_1);
        panel_delete.add(panel_delete_2);
        panel_delete.add(panel_delete_3);
    }
    
    
    public void onglet_modify(){
        
        /*Creation de la liste deroulante*/
        JComboBox combo_modify = new JComboBox();
        
        /*Creation de sous panels pour chaque zone de la fenetre*/
        JPanel panel_modify_1 = new JPanel();
        JPanel panel_modify_2 = new JPanel();
        JPanel panel_modify_3 = new JPanel();
        
        /*Creation label*/
        JLabel label_modify = new JLabel("Modifier : ");
        
        /*Espaces des bordures*/
        panel_modify.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
   
        /*On redéfinit la taille des zones de texte*/
        txt_numero.setPreferredSize(new Dimension(150,30));
        txt_nom.setPreferredSize(new Dimension(150,30));
        txt_prenom.setPreferredSize(new Dimension(150,30));
        txt_tel.setPreferredSize(new Dimension(110,30));
        txt_adresse.setPreferredSize(new Dimension(220,30));
        txt_mutuelle.setPreferredSize(new Dimension(100,30));
        
        
        /*Choix du Layout*/
        panel_modify.setLayout(new BorderLayout());

        
        /*Les champs de la liste Ajout*/
        combo_modify.addItem("Sélectionner");
        combo_modify.addItem("Docteur");
        combo_modify.addItem("Malade");
        combo_modify.addItem("Infirmier");
        
        /*Creation d'une actionListener*/
        combo_modify.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
                Object source = arg0.getSource();
                JComboBox comboBox = (JComboBox)source;
                /*En cliquant sur liste deroulante, on choisit le formulaire correspondant aux champs
                On affiche le panel correspondant a chaque métier*/
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
        
        /*On place la liste tout en haut*/
        panel_modify_1.add(label_modify);
        panel_modify_1.add(combo_modify);
        
        
     
       /*On place les sous panels en fonction des directions grace au BorderLayout*/
        panel_modify.add(panel_modify_1, BorderLayout.NORTH);
        panel_modify.add(panel_modify_2, BorderLayout.CENTER);
        panel_modify.add(panel_modify_3, BorderLayout.SOUTH);
      
        
        
     //   panel_ajout.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
  
       
    }
    
    public void onglet_reporting() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        AfficheGraph a = new AfficheGraph();
        jPanel3 = a.affiche("Nombre d'hospitalisés par service", "hospitalisation", "code_service", "no_malade");
        jPanel4 = a.affiche("Nombre d'infirmier par rotation", "infirmier", "rotation", "numero");
        jPanel2 = a.affiche("Nombre de docteur par spécialité", "docteur", "specialite", "numero");
        jPanel1 = a.affiche("Nombre de malade par mutuelle", "malade", "mutuelle", "numero");
        
        jButton1.setText("Nombre d'hospitalisés par service");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton2.setText("Nombre d'infirmier par rotation");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setText("Nombre de docteur par spécialité");
        jButton3.addActionListener(this::jButton3ActionPerformed);
        
        jButton4.setText("Nombre de malade par mutuelle");
        jButton4.addActionListener(this::jButton4ActionPerformed);
        
        panel_reporting.setLayout(null);

        jPanel5.setBounds(110,120,570,360);
        jPanel5.setLayout(new java.awt.CardLayout());
        
        jPanel3.setBounds(0,0,570,360);
        jPanel4.setBounds(0,0,570,360);
        jPanel2.setBounds(0,0,570,360);
        jPanel1.setBounds(0,0,570,360);
        
        jButton1.setBounds(25, 25, 345, 30);
        jButton2.setBounds(415, 25, 345, 30);
        jButton3.setBounds(25, 70, 345, 30);
        jButton4.setBounds(415, 70, 345, 30);
        
        jPanel5.add(jPanel3, "panelOne");
        jPanel5.add(jPanel4, "panelTwo");
        jPanel5.add(jPanel2, "panelThree");
        jPanel5.add(jPanel1, "panelFour");
        
        panel_reporting.add(jPanel5);
        panel_reporting.add(jButton1);
        panel_reporting.add(jButton2);
        panel_reporting.add(jButton3);
        panel_reporting.add(jButton4);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout card = (CardLayout) jPanel5.getLayout();
        card.show(jPanel5, "panelOne");
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout card = (CardLayout) jPanel5.getLayout();
        card.show(jPanel5, "panelTwo");
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout card = (CardLayout) jPanel5.getLayout();
        card.show(jPanel5, "panelThree");
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        CardLayout card = (CardLayout) jPanel5.getLayout();
        card.show(jPanel5, "panelFour");
    }
        

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        /*On stocke les infos dans des strings*/
        String numero_add = txt_numero.getText();
        String nom_add = txt_nom.getText();
        String prenom_add = txt_prenom.getText();
        String tel_add = txt_tel.getText();
        String adresse_add = txt_adresse.getText();
        String mutuelle_add = txt_mutuelle.getText();
         
        /*Creation d'une arrayList*/
        ArrayList <String> stock_ajout = new ArrayList<String>();
        
        stock_ajout.add(numero_add);
        stock_ajout.add(nom_add);
        stock_ajout.add(prenom_add);
        stock_ajout.add(tel_add);
        stock_ajout.add(adresse_add);
        stock_ajout.add(mutuelle_add);
        
        
        gestionBDD gestion = null;
        try {
            gestion = new gestionBDD();
            System.out.println("*****");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Menu_page.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Menu_page.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(source == confirm)
        {
            try {
                gestion.add(stock_ajout);
            } catch (SQLException ex) {
                Logger.getLogger(Menu_page.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

/* Pour supprimer : afficher tous les docteurs par ex avec leur infos et a cote 
positionner des boutons afin de pouvoir supprimer le docteur ou le modifier
si clique sur supp : supp la case en question et rafraiche la page
si clique sur modifier : envoie vers le l'onglet modifier avce le meme formulaire
que celui d'ajouter mais avec les zones de texte deja pre-remplies avec les donnees
du docteur sélectionné.

pour modifier : en parametre le numero et l'array liste d'objet contenant ttes 
les nouvelles doonnees

1er case de la liste : le type de table ex docteur pour savoir où le placer dans
la base de données*/