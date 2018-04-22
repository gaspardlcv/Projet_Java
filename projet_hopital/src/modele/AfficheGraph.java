/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Pierre
 */
public class AfficheGraph {

    /**
     * Récupère les données retournées par CreaGraph puis créé et retourne un
     * graphique en conséquence.
     *
     * @param titre titre du graphique
     * @param table la table dans laquelle aller chercher les informations
     * @param c1 le nom de la premiere colonne de la table qui nous interesse
     * @param c2 le nom de la deuxieme colonne de la table qui nous interesse
     * @return le diagramme créé en format graphique ChartPanel que l'on peut
     * réutiliser en swing
     */
    public ChartPanel affiche(String titre, String table, String c1, String c2) {
        CreaGraph g = new CreaGraph();
        DefaultPieDataset d = g.connection(table, c1, c2);
        JFreeChart chart = ChartFactory.createPieChart(
                titre, // chart title           
                d, // data           
                true, // include legend          
                true,
                false);
        ChartPanel c = new ChartPanel(chart);
        return c;
    }
}
