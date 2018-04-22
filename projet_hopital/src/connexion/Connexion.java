/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

/*
 * 
 * Librairies importées
 */
import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * Connexion a votre BDD locale ou à distance sur le serveur de l'ECE via le tunnel SSH
 * 
 * @author segado
 */
public class Connexion {

    /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat
     * requete
     */
    private Connection conn;
    private Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    /**
     * ArrayList public pour les tables
     */
    public ArrayList<String> tables = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de sélection
     */
    public ArrayList<String> requetes = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de MAJ
     */
    public ArrayList<String> requetesMaj = new ArrayList<>();

    /**
     * Constructeur avec 3 paramètres : nom, login et password de la BDD locale
     *
     * @param nameDatabase
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connexion(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost/" + nameDatabase;

        //création d'une connexion JDBC à la base 
        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
    }

    /**
     * Constructeur avec 4 paramètres : username et password ECE, login et
     * password de la BDD à distance sur le serveur de l'ECE
     * @param usernameECE
     * @param passwordECE
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connexion(String usernameECE, String passwordECE, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException {
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // Connexion via le tunnel SSH avec le username et le password ECE
        SSHTunnel ssh = new SSHTunnel(usernameECE, passwordECE);

        if (ssh.connect()) {
            System.out.println("Connexion reussie");

            // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
            String urlDatabase = "jdbc:mysql://localhost:3305/" + usernameECE;

            //création d'une connexion JDBC à la base
            conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

            // création d'un ordre SQL (statement)
            stmt = conn.createStatement();

        }
    }

    /**
     * Méthode qui ajoute la table en parametre dans son ArrayList
     *
     * @param table
     */
    public void ajouterTable(String table) {
        tables.add(table);
    }

    /**
     * Méthode qui ajoute la requete de selection en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequete(String requete) {
        requetes.add(requete);
    }

    /**
     * Méthode qui ajoute la requete de MAJ en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequeteMaj(String requete) {
        requetesMaj.add(requete);
    }

    /**
     * Méthode qui retourne l'ArrayList des champs de la table en parametre
     *
     * @param table
     * @return
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsTable(String table) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = champs + " " + rsetMeta.getColumnLabel(i + 1);
        }

        // ajouter un "\n" à la ligne des champs
        champs = champs + "\n";

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);

        // Retourner l'ArrayList
        return liste;
    }
    //Ajout kevin
    public ArrayList recupChampsTable(String table) throws SQLException {
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery("select * from " + table);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = rsetMeta.getColumnLabel(i + 1);
             // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);
        }

       

        // Retourner l'ArrayList
        return liste;
    }
    
    //AJout : steven
    //Methode pour retourner le nom des champs (malade, docteur etc.)
    public ArrayList getChamps(String table) throws SQLException
    {
        String sqlQuery = "select * from "+table;
        if("docteur".equals(table))
        {
            sqlQuery = "select docteur.numero, docteur.specialite, employe.nom, employe.prenom, employe.adresse, employe.tel from " + table;
            sqlQuery+=" inner join employe on employe.numero=docteur.numero";
        }
        if("chambre".equals(table))
        {
            sqlQuery = "select chambre.code_service, chambre.no_chambre, chambre.surveillant, chambre.nb_lits, hospitalisation.no_malade, hospitalisation.lit from " + table;
            sqlQuery+=" INNER JOIN hospitalisation ON (chambre.no_chambre=hospitalisation.no_chambre AND chambre.code_service=hospitalisation.code_service)";
        }
        if("hospitalisation".equals(table))
        {
            sqlQuery = "select hospitalisation.no_malade, hospitalisation.code_service, hospitalisation.no_chambre, hospitalisation.lit, malade.nom, malade.prenom, malade.adresse, malade.tel, malade.mutuelle from " + table;
            sqlQuery+=" INNER JOIN malade ON (malade.numero=hospitalisation.no_malade)";
        }
         if("infirmier".equals(table))
        {
            sqlQuery = "select infirmier.numero, infirmier.code_service, infirmier.rotation, infirmier.salaire, employe.nom, employe.prenom, employe.adresse, employe.tel from " + table;
            sqlQuery+=" inner join employe on employe.numero=infirmier.numero";
        }
         
         if("service".equals(table))
        {
            sqlQuery = "select service.code, service.nom, service.batiment, service.directeur, employe.nom, employe.prenom from " + table;
            sqlQuery+= " inner join employe on employe.numero=service.directeur";
        }
        // r
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(sqlQuery);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();
        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        for (int i = 0; i < nbColonne; i++) 
        {
            liste.add(rsetMeta.getColumnLabel(i + 1));
        }
        return liste;
    }
    

    /**
     * Methode qui retourne l'ArrayList des champs de la requete en parametre
     * @param requete
     * @return 
     * @throws java.sql.SQLException
     */
    public ArrayList remplirChampsRequete(String requete) throws SQLException {
        
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();

        
ArrayList<String> liste = new ArrayList<>();
        // tant qu'il reste une ligne 
        while (rset.next()) {
            String champs;
            champs = rset.getString(1); // ajouter premier champ
            // Concatener les champs de la ligne separes par ,
            for (int i = 1; i < nbColonne; i++) {
                champs = champs + "," + rset.getString(i + 1);
                liste.add(rset.getString(i+1));
            }
            
            // ajouter un "\n" à la ligne des champs
            champs = champs + "\n";

            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs);
        }
        
         // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();
        String[][] resultatsRequete=new String[liste.size()][nbColonne];
        
        for (int i = 0;i < liste.size(); i++)
            {
                while (rset.next()) {

                    String champs;
                for(int k=0;k<nbColonne;k++)
                {
                    champs = rset.getString(k+1);
                    resultatsRequete[i][k]=champs;
                   // System.out.print("Case " + i + k +" du tableau " +resultatsRequete[i][k]+ " ");
                }
                i++;
            }
            }
        

        // Retourner l'ArrayList
        return liste;
    
    }
    
    public String[][] getChampsRequete(String requete) throws SQLException {
        System.out.println(requete);
        // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();

        // calcul du nombre de colonnes du resultat
        int nbColonne = rsetMeta.getColumnCount();
        

        
ArrayList<String> liste = new ArrayList<>();
int nbLigne=0;
        // tant qu'il reste une ligne 
        while (rset.next()) {
            String champs;
            champs = rset.getString(1); // ajouter premier champ
            // Concatener les champs de la ligne separes par ,
            for (int i = 1; i < nbColonne; i++) {
                champs = champs + "," + rset.getString(i + 1);
                liste.add(rset.getString(i+1));
            }
            nbLigne++;
            // ajouter un "\n" à la ligne des champs
            champs = champs + "\n";

            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs);
        }
        
         // récupération de l'ordre de la requete
        rset = stmt.executeQuery(requete);

        // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();
        String[][] resultatsRequete=new String[nbLigne][nbColonne];
        System.out.println(nbLigne);
        for (int i = 0;i < nbLigne; i++)
            {
                while (rset.next()) {

                    String champs;
                for(int k=0;k<nbColonne;k++)
                {
                    champs = rset.getString(k+1);
                    resultatsRequete[i][k]=champs;
                   // System.out.print("Case " + i + k +" du tableau " +resultatsRequete[i][k]+ " ");
                }
                i++;
            }
            }
        

       
        // Retourner l'ArrayList
        return resultatsRequete;
    }
    

    /**
     * Méthode qui execute une requete de MAJ en parametre
     * @param requeteMaj
     * @throws java.sql.SQLException
     */
    public void executeUpdate(String requeteMaj) throws SQLException {
        stmt.executeUpdate(requeteMaj);
    }
    
    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * @return the stmt
     */
    public Statement getStmt() {
        return stmt;
    }

    /**
     * @param stmt the stmt to set
     */
    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    /**
     * @return the rset
     */
    public ResultSet getRset() {
        return rset;
    }

    /**
     * @param rset the rset to set
     */
    public void setRset(ResultSet rset) {
        this.rset = rset;
    }

    /**
     * @return the rsetMeta
     */
    public ResultSetMetaData getRsetMeta() {
        return rsetMeta;
    }

    /**
     * @param rsetMeta the rsetMeta to set
     */
    public void setRsetMeta(ResultSetMetaData rsetMeta) {
        this.rsetMeta = rsetMeta;
    }

    /**
     * @return the tables
     */
    public ArrayList<String> getTables() {
        return tables;
    }

    /**
     * @param tables the tables to set
     */
    public void setTables(ArrayList<String> tables) {
        this.tables = tables;
    }

    /**
     * @return the requetes
     */
    public ArrayList<String> getRequetes() {
        return requetes;
    }

    /**
     * @param requetes the requetes to set
     */
    public void setRequetes(ArrayList<String> requetes) {
        this.requetes = requetes;
    }

    /**
     * @return the requetesMaj
     */
    public ArrayList<String> getRequetesMaj() {
        return requetesMaj;
    }

    /**
     * @param requetesMaj the requetesMaj to set
     */
    public void setRequetesMaj(ArrayList<String> requetesMaj) {
        this.requetesMaj = requetesMaj;
    }
}
