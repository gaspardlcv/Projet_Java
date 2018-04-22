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
import javax.swing.JTable;

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
    private ArrayList<String> tables = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de sélection
     */
    private ArrayList<String> requetes = new ArrayList<>();
    /**
     * ArrayList public pour les requêtes de MAJ
     */
    private ArrayList<String> requetesMaj = new ArrayList<>();

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
        String urlDatabase = "jdbc:mysql://localhost:3308/" + nameDatabase;

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
            String urlDatabase = "jdbc:mysql://localhost:3308/" + usernameECE;

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
        getTables().add(table);
    }

    /**
     * Méthode qui ajoute la requete de selection en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequete(String requete) {
        getRequetes().add(requete);
    }

    /**
     * Méthode qui ajoute la requete de MAJ en parametre dans son
     * ArrayList
     *
     * @param requete
     */
    public void ajouterRequeteMaj(String requete) {
        getRequetesMaj().add(requete);
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
        setRset(getStmt().executeQuery("select * from " + table));

        // récupération du résultat de l'ordre
        setRsetMeta(getRset().getMetaData());

        // calcul du nombre de colonnes du resultat
        int nbColonne = getRsetMeta().getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = champs + " " + getRsetMeta().getColumnLabel(i + 1);
        }

        // ajouter un "\n" à la ligne des champs
        champs = champs + "\n";

        // ajouter les champs de la ligne dans l'ArrayList
        liste.add(champs);

        // Retourner l'ArrayList
        return liste;
    }
    
    public ArrayList recupChampsTable(String table) throws SQLException {
        // récupération de l'ordre de la requete
        setRset(getStmt().executeQuery("select * from " + table));

        // récupération du résultat de l'ordre
        setRsetMeta(getRset().getMetaData());

        // calcul du nombre de colonnes du resultat
        int nbColonne = getRsetMeta().getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        String champs = "";
        // Ajouter tous les champs du resultat dans l'ArrayList
        for (int i = 0; i < nbColonne; i++) {
            champs = getRsetMeta().getColumnLabel(i + 1);
            
            // ajouter les champs de la ligne dans l'ArrayList
             liste.add(champs);
        }

        // Retourner l'ArrayList
        return liste;
    }
    
    
    
    public String[] tab_champs(ArrayList<String> liste){
        
        String tab[] = new String[liste.size()];
        
        for(int i=0; i<liste.size(); i++)
        {
            tab[i] = liste.get(i);
        }
   
        return tab;
        
    }
            
    
    //AJout : steven
    //Methode pour retourner le nom des champs (malade, docteur etc.)
    public ArrayList getChamps(String table) throws SQLException
    {
        // récupération de l'ordre de la requete
        setRset(getStmt().executeQuery("select * from " + table));

        // récupération du résultat de l'ordre
        setRsetMeta(getRset().getMetaData());

        // calcul du nombre de colonnes du resultat
        int nbColonne = getRsetMeta().getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<>();
        for (int i = 0; i < nbColonne; i++) 
        {
            liste.add(getRsetMeta().getColumnLabel(i + 1));
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
        setRset(getStmt().executeQuery(requete));

        // récupération du résultat de l'ordre
        setRsetMeta(getRset().getMetaData());

        // calcul du nombre de colonnes du resultat
        int nbColonne = getRsetMeta().getColumnCount();

        // creation d'une ArrayList de String
        ArrayList<String> liste;
        liste = new ArrayList<String>();

        // tant qu'il reste une ligne 
        while (getRset().next()) {
            String champs;
            champs = getRset().getString(1); // ajouter premier champ

            // Concatener les champs de la ligne separes par ,
            for (int i = 1; i < nbColonne; i++) {
                champs = champs + "," + getRset().getString(i + 1);
            }

            // ajouter un "\n" à la ligne des champs
            champs = champs + "\n";

            // ajouter les champs de la ligne dans l'ArrayList
            liste.add(champs);
        }

        // Retourner l'ArrayList
        return liste;
    }

    /**
     * Méthode qui execute une requete de MAJ en parametre
     * @param requeteMaj
     * @throws java.sql.SQLException
     */
    public void executeUpdate(String requeteMaj) throws SQLException {
        getStmt().executeUpdate(requeteMaj);
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
