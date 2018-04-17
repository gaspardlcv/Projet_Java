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

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import modele.*;

public class BDDgestion_vue extends gestionBDD implements ActionListener, ItemListener{

    ArrayList<JPanel> docteurs;
    JFrame panneau = new JFrame();
    JTextField texte= new JTextField();
    
    
    // Ajout : Steven
    JButton connect; // bouton pour se connecter
    private JComboBox combo;
    java.util.List<JCheckBox> champsCoches= new ArrayList();
    
    JPanel recherche;
    JPanel barrerecherche;
    JPanel checkbox;
    JPanel top;
    JPanel resultat;
    
    private final JTextArea fenetreLignes, fenetreRes;
    // Ajout : Steven
    
            
    public BDDgestion_vue() throws ClassNotFoundException, SQLException
    {
        super();
        
        //Ajout : Steven
        connect = new JButton("Connexion");
        connect.addActionListener(this);
        
        combo = new JComboBox();
        combo.setPreferredSize(new Dimension(100, 20));
        combo.addItemListener(this);
        
        recherche = new JPanel();
        resultat = new JPanel();
        barrerecherche = new JPanel();
        checkbox=new JPanel();
        top = new JPanel();
        
        top.setLayout(new GridLayout(2,1));
        
        barrerecherche.add(combo);
        barrerecherche.add(checkbox);
        
        recherche.add(connect);
        top.add(recherche);
        top.add(barrerecherche);
        fenetreLignes = new JTextArea();
        fenetreRes = new JTextArea();
        
        afficherTables();
                    
        afficherLignes(combo.getSelectedItem().toString());
        
        //Ajout : Steven
        
        panneau.setTitle("Hopital");
        panneau.setLayout(new BorderLayout());
        panneau.getContentPane().add(top, BorderLayout.NORTH);
        panneau.getContentPane().add(fenetreLignes, BorderLayout.CENTER);
        panneau.setSize(1200,800);
        panneau.setResizable(false);
        panneau.setLocationRelativeTo(null);
        panneau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panneau.setVisible(true);
        System.out.println("zff");
        
    }
    public void display()
    {
        /*for(String elem : this.getTable()){
        texte.setText(elem);  
        docteurs.add(texte);
        }*/
    }
    
    
    // AJout : steven
    
    public void afficherTables() {
        for (String table : local.tables) {
            combo.addItem(table);
        }
    }
    
    public void afficherLignes(String nomTable) {
        try {
            
            ArrayList<String> liste;
            
            champs_table=new ArrayList<>();
            checkbox.removeAll();
            champsCoches.clear();

            // effacer les résultats
            fenetreLignes.removeAll();

            // recupérér les résultats de la table selectionnee
            liste = local.remplirChampsTable(nomTable);

            // afficher les champs de la table selectionnee 
            fenetreLignes.setText("");
            for (String liste1 : liste) 
            {
                fenetreLignes.append(liste1);
                
            }
            
            for(int i=0;i<local.getChamps(nomTable).size();i++)
            {
                 champs_table.add(local.getChamps(nomTable).get(i).toString());
                 champsCoches.add(new JCheckBox(champs_table.get(i).toString()));
                 champsCoches.get(i).setVisible(true);
                 champsCoches.get(i).addItemListener(this);
                 checkbox.add(champsCoches.get(i));
            }
            
            //On remplit les checkbox restant par du vide
            for(int i=local.getChamps(nomTable).size();i<6;i++)
            {
                champsCoches.add(new JCheckBox());
                champsCoches.get(i).setVisible(false);
            }

            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select * from " + nomTable + ";";
            liste = local.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            for (String liste1 : liste) {
                fenetreLignes.append(liste1);
            }

        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            fenetreRes.setText("");
            fenetreRes.append("Echec table SQL");
            e.printStackTrace();

        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    // AJout : steven
    @Override
    @SuppressWarnings("CallToThreadDumpStack")
    public void itemStateChanged(ItemEvent evt) {
        // sélection d'une requete et afficher ses résultats
        if (evt.getSource() == combo) {
            // recuperer la liste des lignes de la requete selectionnee
            afficherLignes(combo.getSelectedItem().toString());
            
        } 
        
        if(evt.getSource() == champsCoches.get(0))
        {
            System.out.println("1");
        }
        if(evt.getSource() == champsCoches.get(1))
        {
            System.out.println("2");
        }
        if(evt.getSource() == champsCoches.get(2))
        {
            System.out.println("3");
        }
        if(evt.getSource() == champsCoches.get(3))
        {
            System.out.println("4");
        }
        if(evt.getSource() == champsCoches.get(4))
        {
            System.out.println("5");
        }
        if(evt.getSource() == champsCoches.get(5))
        {
            System.out.println("6");
        }
    }
    //Ajout :steven
    
    
}
