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
public class CreaGraph {   //se connecte à la bdd et récupère les données nécessaires dans un dataset (format utilisé pour créer les graphiques)
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
