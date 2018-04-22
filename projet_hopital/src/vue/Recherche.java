/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import modele.gestionBDD;

/**
 *
 * @author Steven
 */
public class Recherche extends gestionBDD implements ActionListener, ItemListener, DocumentListener {
    
    JFrame panneau = new JFrame();
    private CustomComboBox combo;
    
    
    JPanel recherche;
    JPanel barrerecherche;
    JPanel checkbox;
    JPanel top;
    JPanel resultat;
    String[] nom_champs; String[][] lignes;
    String[] nom_champs_coches;
    
    //tableau affichant les résultats
    JTable resultats=new JTable();
    
    public Recherche() throws ClassNotFoundException, SQLException
    {
        super();
        
        combo = new CustomComboBox();
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
        
        
        
        top.add(recherche);
        top.add(barrerecherche);
        resultats=new JTable();
        
        afficherTables();
                    
        
      //  afficherLignes(combo.getSelectedItem().toString(), retourLignes());
        
        
        
        
        //Ajout : Steven
        
        panneau.setTitle("Hopital");
        panneau.setLayout(new BorderLayout());
        panneau.getContentPane().add(top, BorderLayout.NORTH);
     //   panneau.getContentPane().add(fenetreLignes, BorderLayout.CENTER);
     panneau.add(new JScrollPane(resultats), BorderLayout.CENTER);
        
        panneau.setSize(1800,1000);
        panneau.setResizable(false);
        panneau.setLocationRelativeTo(null);
        panneau.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panneau.setVisible(true);
    }
    
    public void afficherTables()
    {
        for (String table : local.tables)
        {
            combo.addItem(table);
        }
    }
    
     public void afficherLignes(String nomTable, ArrayList champsChoisis) {
        try {
            
            
            
            
            
            ArrayList<String> liste_champs;
            
            String requeteWhere = " where "; // requete "... WHERE"
            
            for(int i=0;i<champs_recherche.size();i++)
            {
                if(!"".equals(champs_recherche.get(i).getText()))
                {
                    champsCoches.get(i).setSelected(true);
                }
            }
            
            champsChoisis=retourLignes();
            
            

            // recupérér les résultats de la table selectionnee
            liste_champs = local.getChamps(nomTable);

            //On assigne au tableau d'objet nom_champs les noms des champs
            nom_champs= new String[liste_champs.size()];
            nom_champs= liste_champs.toArray(nom_champs);
            
            //On assigne à la jtable les champs cochés
            nom_champs_coches= new String[champsChoisis.size()];
            nom_champs_coches=(String[]) champsChoisis.toArray(nom_champs_coches);
            
            // afficher les champs de la table selectionnee 
            champs_table.clear();
            for (String liste1 : liste_champs) 
            {
                champs_table.add(liste1);
                
            }
            
            

           // On ajoute les conditions avec les barres de recherche
            for(int i=0; i<champs_recherche.size();i++)
            {
                if(champs_recherche.get(i).getId()!=null && !"".equals(champs_recherche.get(i).getText()))
                {
                    requeteWhere+= champs_recherche.get(i).getId() + " like " + "'" + champs_recherche.get(i).getText() + "%' and ";         
                }
            }
            
            // si il y a du texte dans les champs de recherche, on enleve le " and "
            if(!" where ".equals(requeteWhere)){
            requeteWhere=requeteWhere.substring(0,requeteWhere.length()-5);
            requeteWhere+=";";
            }
            
            
            // recuperer la liste de la table sélectionnée
            String requeteSelectionnee = "select";
            for(int i=0; i<champsChoisis.size();i++)
            {
                requeteSelectionnee += " " + champsChoisis.get(i);
                if(i+1<champsChoisis.size())
                {
                    requeteSelectionnee+= ", ";
                }
            }
            requeteSelectionnee+= " from " + nomTable ;
            
            //Si il y a du texte dans les champs de recherche
            if(!" where ".equals(requeteWhere))
                System.out.println(requeteSelectionnee+=requeteWhere);
            
            liste_champs = local.remplirChampsRequete(requeteSelectionnee);

            // afficher les lignes de la requete selectionnee a partir de la liste
            for (String liste1 : liste_champs) {
                table.add(liste1);
            }
            
            lignes=local.getChampsRequete(requeteSelectionnee);
            
            for(int i=0;i<lignes[0].length;i++)
            {
                System.out.println(i);
            }
            
            if(lignes.length!=0)
            {
            DefaultTableModel table = new DefaultTableModel(lignes, nom_champs_coches);
            resultats.setModel(table);
                     resultats.setVisible(true);
            }
            else
            {
                DefaultTableModel table = new DefaultTableModel();
            resultats.setModel(table);
                     resultats.setVisible(true);
            }

           panneau.revalidate();

        } catch (SQLException e) {
            // afficher l'erreur dans les résultats
            
            e.printStackTrace();

        }
    }
    
    public void afficherChamps(String nomTable, ArrayList champsChoisis)
    {
        try {
            
            ArrayList<String> liste;
            
            champs_table=new ArrayList<>();
            checkbox.removeAll(); // pk je peux plus cocher 
            champs_recherche.clear();

            

            // recupérér les résultats de la table selectionnee
            liste = local.getChamps(nomTable);

            
            // afficher les champs de la table selectionnee
            champs_table.clear();
            for (String liste1 : liste) 
            {
                champs_table.add(liste1);
            }
            
            for(int i=0;i<local.getChamps(nomTable).size();i++)
            {
                 champs_table.add(local.getChamps(nomTable).get(i).toString());
                 champsCoches.add(new JCheckBox(champs_table.get(i).toString()));
                 champsCoches.get(i).setVisible(true);
                 champsCoches.get(i).addItemListener(this);
                 
                 champs_recherche.add(new CustomTextField());
                 champs_recherche.get(i).setPreferredSize(new Dimension(100,15));
                 champs_recherche.get(i).setId(champs_table.get(i).toString());
                 champs_recherche.get(i).setVisible(true);
                 champs_recherche.get(i).getDocument().addDocumentListener(this);
                 
                 champs_recherche.get(i).setVisible(true);
                 
                 
                 checkbox.add(champsCoches.get(i));
                 checkbox.add(champs_recherche.get(i));
                 
                // fenetreLignes.append(champs_table.get(i).toString());
            }
            
            //On remplit les checkbox restant par du vide
            for(int i=local.getChamps(nomTable).size();i<6;i++)
            {
                champsCoches.add(new JCheckBox(""));
                champsCoches.get(i).setVisible(false);
                champs_recherche.add(new CustomTextField());
                champs_recherche.get(i).setVisible(false);
            }
            
            
         /*   String[] champs = new String[champs_table.size()];
            champs = champs_table.toArray(champs);
            DefaultTableModel table = new DefaultTableModel(new Object[1][2],champs);
            resultats.setModel(table);*/
         
         DefaultTableModel table = new DefaultTableModel();
            resultats.setModel(table);
            panneau.revalidate();
            
        }catch (SQLException e) {
            // afficher l'erreur dans les résultats
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
        
        ArrayList champsChoisis = new ArrayList();
        
        Object e=evt.getSource();
        
        // sélection d'une requete et afficher ses résultats
        if (e == combo) {
            // recuperer la liste des lignes de la requete selectionnee
            champsChoisis=retourLignes();
            champsCoches.clear();
            afficherChamps(combo.getNomTable(), champsChoisis);
            nom_table=combo.getSelectedItem().toString();
        }
        
        for(int i=0;i<champsCoches.size();i++)
        {
            if(e==champsCoches.get(i))
            {
                champsChoisis=retourLignes();
            afficherLignes(combo.getNomTable(),champsChoisis);
            }
        }
        
        
    }
    //Ajout :steven

    @Override
    public void insertUpdate(DocumentEvent de) {
        ArrayList champsChoisis = new ArrayList();
        champsChoisis=retourLignes();
            afficherLignes(combo.getNomTable(),champsChoisis);
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        ArrayList champsChoisis = new ArrayList();
        champsChoisis=retourLignes();
            afficherLignes(combo.getNomTable(),champsChoisis);
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
    }
    
}
