/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cogPursuit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public int creerPartieSolo(String pseudo) {
        this.openConnexion();
        int idJoueur = -1;
        int idPartie = -1;
    
        try {
            // 1. Vérifier si le joueur existe
            PreparedStatement checkStmt = this.con.prepareStatement(
                "SELECT id_joueur FROM Joueur WHERE nom = ?"
            );
            checkStmt.setString(1, pseudo);
            ResultSet checkRs = checkStmt.executeQuery();
    
            if (checkRs.next()) {
                // Joueur existe déjà
                idJoueur = checkRs.getInt("id_joueur");
            } else {
                // Joueur n'existe pas, on l'insère
                PreparedStatement insertStmt = this.con.prepareStatement(
                    "INSERT INTO Joueur (nom) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                insertStmt.setString(1, pseudo);
                insertStmt.executeUpdate();
                ResultSet rs1 = insertStmt.getGeneratedKeys();
                if (rs1.next()) {
                    idJoueur = rs1.getInt(1);
                }
                insertStmt.close();
            }
    
            // 2. Créer la partie
            PreparedStatement stmt2 = this.con.prepareStatement(
                "INSERT INTO Partie (nom, date_creation, statut) VALUES (?, NOW(), 'en cours')",
                Statement.RETURN_GENERATED_KEYS);
            stmt2.setString(1, "Partie de " + pseudo);
            stmt2.executeUpdate();
            ResultSet rs2 = stmt2.getGeneratedKeys();
            if (rs2.next()) {
                idPartie = rs2.getInt(1);
            }
    
            // 3. Lier le joueur à la partie
            PreparedStatement stmt3 = this.con.prepareStatement(
                "INSERT INTO Joueur_Partie (id_joueur, id_partie, score, position) VALUES (?, ?, ?, ?)");
            stmt3.setInt(1, idJoueur);
            stmt3.setInt(2, idPartie);
            stmt3.setInt(3, 0); // score initial
            stmt3.setInt(4, 0); // position initiale
            stmt3.executeUpdate();
    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnexion();
        }
    
        return idPartie;
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

//    public void testBDD() {
//        this.openConnexion();
//        System.out.println("salut ça marche");
//        this.closeConnexion();
//        System.out.println("Salut c'est fermé");
//    }
    
    public ArrayList<String> recuperationQuestion(int diff, String theme) {

        ArrayList<String> res = new ArrayList<>();

        try {
            this.openConnexion();
            Statement stmt = this.con.createStatement();
            String query = "";
            //Partie qui permet de charger la question voulue
            switch (diff) {
                case 1:
                    query = "SELECT q.id_question, q.texte AS enonce, q.choix_A, q.choix_B, q.choix_C, q.choix_D, q.reponse_correcte, t.nom AS theme FROM Question q JOIN Theme t ON q.id_theme = t.id_theme WHERE q.difficulte = 'facile' AND t.nom = '" + theme + "' ORDER BY RAND() LIMIT 1;";
                    break;
                case 2:
                    query = "SELECT q.id_question, q.texte AS enonce, q.choix_A, q.choix_B, q.choix_C, q.choix_D, q.reponse_correcte, t.nom AS theme FROM Question q JOIN Theme t ON q.id_theme = t.id_theme WHERE q.difficulte = 'moyenne' AND t.nom = '" + theme + "' ORDER BY RAND() LIMIT 1;";
                    break;
                case 3:
                    query = "SELECT q.id_question, q.texte AS enonce, q.choix_A, q.choix_B, q.choix_C, q.choix_D, q.reponse_correcte, t.nom AS theme FROM Question q JOIN Theme t ON q.id_theme = t.id_theme WHERE q.difficulte = 'difficile' AND t.nom = '" + theme + "' ORDER BY RAND() LIMIT 1;";
                    break;
                default:
                    throw new AssertionError();
            }
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                res.add(String.valueOf(rs.getInt("id_question")));
                res.add(rs.getString("enonce"));
                res.add(rs.getString("choix_A"));
                res.add(rs.getString("choix_B"));
                res.add(rs.getString("choix_C"));
                res.add(rs.getString("choix_D"));
                res.add(rs.getString("q.reponse_correcte"));
                res.add(rs.getString("theme"));
            }

            // test du retour de l'array list
//            for (int i = 0; i < resultatsString.size(); i++) {
//                System.out.println(resultatsString.get(i));
//            }

            stmt.close();
        } catch (SQLException e) {
            System.out.println("Probleme avec la requete");
        } finally {
            this.closeConnexion();
        }
        return res;
    }

    /**
     * Permet l'ajout d'un joueur dans la BDD
     *
     * @param pseudo le pseudo du joueur
     */
    public void addJoueurBDD(String pseudo) {
        this.openConnexion();
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO Joueur (nom) VALUES (\"" + pseudo + "\");");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBdd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnexion();
        }
    }

    /**
     * Permet de supprimer un joueur dans la BDD en fonction de son pseudo.
     *
     * @param pseudo le pseudo du joueur à supprimer
     */
    public void delJoueurBDD(String pseudo) {
        this.openConnexion();
        try {
            String query = "DELETE FROM Joueur WHERE nom = ?";
            PreparedStatement stmt = this.con.prepareStatement(query);
            stmt.setString(1, pseudo);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBdd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnexion();
        }
    }

    public void terminerPartie(int idPartie) {
        this.openConnexion();
        try {
            String query = "UPDATE Partie SET statut = ?, date_fini = CURRENT_TIMESTAMP WHERE id_partie = ?";
            PreparedStatement stmt = this.con.prepareStatement(query);
            stmt.setString(1, "terminée");
            stmt.setInt(2, idPartie);
            stmt.executeUpdate();
            stmt.close();
            System.out.println("Partie marquée comme terminée en base.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la partie !");
            e.printStackTrace();
        } finally {
            this.closeConnexion();
        }
    }

    public void mettreAJourScore(int idPartie, String pseudo, int score, int position) {
        this.openConnexion();
        try {
            // Récupérer l'id_joueur à partir du pseudo
            int idJoueur = -1;
            PreparedStatement stmt1 = this.con.prepareStatement(
                "SELECT id_joueur FROM Joueur WHERE nom = ?"
            );
            stmt1.setString(1, pseudo);
            ResultSet rs = stmt1.executeQuery();
            if (rs.next()) {
                idJoueur = rs.getInt("id_joueur");
            }
            stmt1.close();
    
            if (idJoueur != -1) {
                PreparedStatement stmt2 = this.con.prepareStatement(
                    "UPDATE Joueur_Partie SET score = ?, position = ? WHERE id_joueur = ? AND id_partie = ?"
                );
                stmt2.setInt(1, score);
                stmt2.setInt(2, position);
                stmt2.setInt(3, idJoueur);
                stmt2.setInt(4, idPartie);
                stmt2.executeUpdate();
                stmt2.close();
    
                System.out.println("✅ Score et position mis à jour pour " + pseudo);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnexion();
        }
    }

    /**
     * Récupère la liste des joueurs avec leur score total pour le classement.
     *
     * @return une liste de chaînes contenant le pseudo et le score total.
     */
    public ArrayList<String> getClassementJoueursBDD() {
        this.openConnexion();
        ArrayList<String> res = new ArrayList<>();
        try {
            Statement stmt = this.con.createStatement();

            // Jointure entre Joueur et Joueur_Partie, regroupée par joueur
            String query = """
                SELECT j.nom, SUM(jp.score) AS score_total
                FROM Joueur j
                JOIN Joueur_Partie jp ON j.id_joueur = jp.id_joueur
                GROUP BY j.id_joueur
                ORDER BY score_total DESC;
            """;

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String nom = rs.getString("nom");
                int score = rs.getInt("score_total");
                res.add("Pseudo : " + nom + " | Score total : " + score);
            }

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionBdd.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.closeConnexion();
        }
        return res;
    }



}
