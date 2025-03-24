/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe utilisée pour établir une connexion avec la base de données,
 * interroger la base et insérer de nouveaux tuples dans la base
 *
 * @author castagnos modifié par Jadam
 */
public class ConnexionBdd {

    private final String host, port, dbname, username, password; // données relatives à la connexion à la BDD
    private Connection con = null; // attribut récupérant la connexion à la BDD

    // constructeur de l'objet
    public ConnexionBdd() {
        this.host = "mysql-jadam579.alwaysdata.net";     //adresse
        this.port = "3306";    //port
        this.dbname = "jadam579_cogpursuit";   //nom de la bdd
        this.username = "jadam579_user";
        this.password = "bddpass123";
    }

    /*
     * Ouvre la connexion avec la base de données
     */
    private void openConnexion() {
        String connectUrl = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.dbname;
        if (this.con != null) {
            this.closeConnexion();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            this.con = DriverManager.getConnection(connectUrl, this.username, this.password);
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Cannot load db driver: com.mysql.jdbc.Driver");
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur inattendue");
            e.printStackTrace();
        }
    }

    /*
     * Ferme la connexion avec la base de données
     */
    private void closeConnexion() {
        if (this.con != null) {
            try {
                this.con.close();
                System.out.println("Database connection terminated.");
            } catch (Exception e) {
                /* ignore close errors */ }
        }
    }

    public void testBDD() {
        this.openConnexion();
        System.out.println("salut ça marche");
        this.closeConnexion();
        System.out.println("Salut c'est fermé");
    }

    public ArrayList<String> recuperationQuestion(int diff) {

        ArrayList<String> resultatsString = new ArrayList();

        try {
            this.openConnexion();
            Statement stmt = this.con.createStatement();
            String query = "";
            //Partie qui permet de charger la question voulue
            switch (diff) {
                case 1:
                    query = "SELECT q.id_question, q.texte AS enonce, q.choix_A, q.choix_B, q.choix_C, q.choix_D, q.reponse_correcte, t.nom AS theme FROM Question q JOIN Theme t ON q.id_theme = t.id_theme WHERE q.difficulte = 'facile' AND t.nom = 'Neurosciences' ORDER BY RAND() LIMIT 1;";
                    break;
                case 2:
                    query = "SELECT q.id_question, q.texte AS enonce, q.choix_A, q.choix_B, q.choix_C, q.choix_D, q.reponse_correcte, t.nom AS theme FROM Question q JOIN Theme t ON q.id_theme = t.id_theme WHERE q.difficulte = 'moyenne' AND t.nom = 'Neurosciences' ORDER BY RAND() LIMIT 1;";
                    break;
                case 3:
                    query = "SELECT q.id_question, q.texte AS enonce, q.choix_A, q.choix_B, q.choix_C, q.choix_D, q.reponse_correcte, t.nom AS theme FROM Question q JOIN Theme t ON q.id_theme = t.id_theme WHERE q.difficulte = 'difficile' AND t.nom = 'Neurosciences' ORDER BY RAND() LIMIT 1;";
                    break;
                default:
                    throw new AssertionError();
            }
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                resultatsString.add(String.valueOf(rs.getInt("id_question")));
                resultatsString.add(rs.getString("enonce"));
                resultatsString.add(rs.getString("choix_A"));
                resultatsString.add(rs.getString("choix_B"));
                resultatsString.add(rs.getString("choix_C"));
                resultatsString.add(rs.getString("choix_D"));
                resultatsString.add(rs.getString("q.reponse_correcte"));
                resultatsString.add(rs.getString("theme"));
            }

            // test du retour de l'array list
            for (int i = 0; i < resultatsString.size(); i++) {
                System.out.println(resultatsString.get(i));
            }
            
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete");
        } finally {
            this.closeConnexion();
        }
        return resultatsString;
    }

    /**
     * Permet l'ajout d'un joueur dans la BDD
     *
     * @param pseudo le pseudo du joueur
     * @param age l'âge du joueur
     */
    public void addJoueurBDD(String pseudo) {
        this.openConnexion();
        try {
            Statement stmt = this.con.createStatement();
            // comamande sql
            stmt.executeUpdate("INSERT INTO Joueur VALUES (\"" + pseudo + "\"," + 0 + "," + 0 + "," + 0 + ",);");

        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBdd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnexion();
        }
    }

    /**
     * Récupère la liste des Joueurs et leurs scores
     *
     * @return la liste des Joueurs et leurs scores
     */
    public ArrayList<String> getJoueurBDD() {
        this.openConnexion();
        ArrayList<String> res = new ArrayList();
        try {
            Statement stmt = this.con.createStatement();
            // commande sql
            ResultSet rs1 = stmt.executeQuery("SELECT Joueur.Pseudo");
            // récupération des joueurs sous forme de Liste de String
            while (rs1.next()) {
                res.add("Pseudo : " + rs1.getString("pseudo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBdd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnexion();
        }
        return res;
    }

    /**
     * Ajoute un point de victoire pour le joueur passé en paramètre
     *
     * @param pseudo nom du joueur gagnant
     */
    public void addPointVictory(String pseudo) {
        this.openConnexion();
        try {
            Statement stmt = this.con.createStatement();
            // commande update sql
            stmt.executeUpdate("UPDATE Joueur SET PuzzleFinis = PuzzleFinis+1 WHERE Pseudo = \"" + pseudo + "\";");
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBdd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnexion();
        }
    }

}
