/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.*;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Pierre
 */
public class CreaGraph {   
    /**
     * Se connecte à la bdd et récupère les données nécessaires dans un dataset (format utilisé pour créer les graphiques).
     * @param table la table dans laquelle aller chercher les informations
     * @param c1 le nom de la premiere colonne de la table qui nous interesse
     * @param c2 le nom de la deuxieme colonne de la table qui nous interesse
     * @return les données récupérées dans un objet DefaultPieDataset propre à la bibliothèque JFreeChart
     */
    public DefaultPieDataset connection(String table, String c1, String c2) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost/hopital?autoReconnect=true&useSSL=false",
                    "root",
                    "");

            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from " + table);

            while (resultSet.next()) {
                dataset.setValue(
                        resultSet.getString(c1),
                        resultSet.getInt(c2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }
}
