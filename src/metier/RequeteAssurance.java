package metier;

import exceptions.ClientException;
import exceptions.NumSecuException;
import modele.Client;
import modele.NumSecu;
import modele.Risque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequeteAssurance {
    private Connection connection;

    // Constructeur par défaut
    public RequeteAssurance() {
        // Appel de la méthode pour établir la connexion dans le constructeur
        connect();
    }

    public Connection getConnection() {
        return connection;
    }

    public List<Risque> ensRisques() throws SQLException {
        List<Risque> risques = new ArrayList<>();

        try {
            // Créez une déclaration SQL
            Statement statement = connection.createStatement();

            // Exécutez la requête SQL pour récupérer les risques
            String query = "SELECT * FROM RISQUE";
            ResultSet resultSet = statement.executeQuery(query);

            // Parcourez les résultats et ajoutez les risques à la liste
            while (resultSet.next()) {
                int nRisque = resultSet.getInt("nRisque");
                int niveau = resultSet.getInt("niveau");

                // Créez un objet Risque et ajoutez-le à la liste
                Risque risque = new Risque(nRisque, niveau);
                risques.add(risque);
            }

        } catch (SQLException e) {
            // Gérez l'exception si une erreur se produit lors de l'exécution de la requête SQL
            throw e;
        }

        return risques;
    }

    // Méthode pour récupérer la liste des clients avec leurs objets NumSecu et Risque
    public List<Client> ensClients() throws SQLException {
        List<Client> clients = new ArrayList<>();

        try {
            // Créez une déclaration SQL
            Statement statement = connection.createStatement();

            // Exécutez la requête SQL pour récupérer les clients avec les données de NumSecu et Risque
            String query = "SELECT C.nClient, C.nom, C.prenom, C.telephone, C.revenu, C.nRisque, "
                    + "N.nNumSecu, N.sexe, N.anneeNaissance, N.moisNaissance, N.departement, N.commune, N.ordre, N.cle FROM CLIENT C "
                    + "JOIN NUMSECU N ON C.nNumSecu = N.nNumSecu "
                    + "ORDER BY C.nom";


            ResultSet resultSet = statement.executeQuery(query);

            // Parcourez les résultats et créez des objets Client avec NumSecu et Risque
            while (resultSet.next()) {
                int nClient = resultSet.getInt("nClient");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String telephone = resultSet.getString("telephone");
                double revenu = resultSet.getDouble("revenu");
                int nRisque = resultSet.getInt("nRisque");
                // Créez un objet NumSecu avec les données

                NumSecu numSecu = new NumSecu(
                        resultSet.getInt("nNumSecu"),
                        resultSet.getInt("sexe"),
                        resultSet.getInt("anneeNaissance"),
                        resultSet.getInt("moisNaissance"),
                        resultSet.getInt("departement"),
                        resultSet.getInt("commune"),
                        resultSet.getInt("ordre"),
                        resultSet.getInt("cle")
                );


                // Créez un objet Client avec NumSecu et Risque
                Client client = new Client(nClient, nom, prenom, numSecu, nRisque, telephone, revenu);
                clients.add(client);
            }

        } catch (SQLException e) {
            // Gérez l'exception si une erreur se produit lors de l'exécution de la requête SQL
            throw e;
        } catch (NumSecuException | ClientException e) {
            throw new RuntimeException(e);
        }

        return clients;
    }

    // Méthode pour récupérer la liste des clients dont le nom ou le prénom contient la chaîne de caractères nomprenom
    public List<Client> ensClients(String nomprenom) throws SQLException {
        List<Client> clients = new ArrayList<>();

        try {
            String query = "SELECT C.nClient, C.nom, C.prenom, C.telephone, C.revenu, C.nRisque, "
                    + "N.nNumSecu, N.sexe, N.anneeNaissance, N.moisNaissance, N.departement, N.commune, N.ordre, N.cle, "
                    + "R.nRisque, R.niveau "
                    + "FROM CLIENT C "
                    + "JOIN NUMSECU N ON C.nNumSecu = N.nNumSecu "
                    + "JOIN RISQUE R ON C.nRisque = R.nRisque "
                    + "WHERE UPPER(nom) LIKE ? OR UPPER(prenom) LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            nomprenom = "%" + nomprenom.toUpperCase() + "%";
            preparedStatement.setString(1, nomprenom);
            preparedStatement.setString(2, nomprenom);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int nClient = resultSet.getInt("nClient");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String telephone = resultSet.getString("telephone");
                double revenu = resultSet.getDouble("revenu");
                int nRisque = resultSet.getInt("nRisque");
                NumSecu numSecu = new NumSecu(
                        resultSet.getInt("nNumSecu"),
                        resultSet.getInt("sexe"),
                        resultSet.getInt("anneeNaissance"),
                        resultSet.getInt("moisNaissance"),
                        resultSet.getInt("departement"),
                        resultSet.getInt("commune"),
                        resultSet.getInt("ordre"),
                        resultSet.getInt("cle")
                );


                Client client = new Client(nClient, nom, prenom, numSecu, nRisque, telephone, revenu);
                clients.add(client);
            }

        } catch (SQLException e) {
            throw e;
        } catch (NumSecuException | ClientException e) {
            throw new RuntimeException(e);
        }

        return clients;
    }

    // Méthode pour ajouter un numéro de sécurité sociale dans la base de données et renvoyer la clé générée automatiquement
    public int ajouteNumSecu(NumSecu s) throws SQLException {
        int cleGeneree = 0;

        try {
            String query = "INSERT INTO NUMSECU (sexe, anneeNaissance, moisNaissance, departement, commune, ordre, cle) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Créez une déclaration SQL paramétrée avec retour de clé générée automatiquement
            PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            // Configurez les paramètres de la requête avec les valeurs de l'objet NumSecu
            preparedStatement.setInt(1, s.getSexe());
            preparedStatement.setInt(2, s.getAnneeNaissance());
            preparedStatement.setInt(3, s.getMoisNaissance());
            preparedStatement.setInt(4, s.getDepartement());
            preparedStatement.setInt(5, s.getCommune());
            preparedStatement.setInt(6, s.getOrdre());
            preparedStatement.setInt(7, s.getCle());

            preparedStatement.executeUpdate();

            // Récupérez la clé générée automatiquement
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cleGeneree = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            // Gérez l'exception si une erreur se produit lors de l'ajout
            throw e;
        }

        return cleGeneree;
    }


    // Méthode privée pour établir la connexion à la base de données
    private void connect() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/tp_assurance";
        String username = "root";
        String password = "";

        try {
            // Charger le driver JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion à la base de données
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Vérifier si la connexion a été établie avec succès
            if (connection != null) {
                System.out.println("Connexion à la base de données établie avec succès.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Le pilote JDBC MySQL n'a pas été trouvé.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'établissement de la connexion à la base de données.");
            e.printStackTrace();
        }
    }

    // Méthode pour ajouter un client dans la base de données
    public boolean ajouteClient(Client c) throws SQLException {
        try {
            connection.setAutoCommit(false); // Désactiver l'auto-commit
            boolean clientExiste = clientExisteDansLaBase(c.getNumSecu().getnNumSecu());
            if (!clientExiste) {
                int cleNumSecu = ajouteNumSecu(c.getNumSecu());
                if (cleNumSecu > 0) {
                    String query = "INSERT INTO CLIENT (nom, prenom, nNumSecu, telephone, revenu, nRisque) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, c.getNom());
                    preparedStatement.setString(2, c.getPrenom());
                    preparedStatement.setInt(3, cleNumSecu); // Utilisation de la clé générée
                    preparedStatement.setString(4, c.getTelephone());
                    preparedStatement.setDouble(5, c.getRevenu());
                    preparedStatement.setInt(6, c.getRisque());
                    preparedStatement.executeUpdate();
                    connection.commit();
                    return true; // L'insertion a réussi
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
        return false;
    }

    // Question 12 : Supprimer Client
    public boolean supprimerClient(Client c) throws SQLException {
        try {
            String deleteNumSecuQuery = "DELETE FROM NUMSECU WHERE nNumSecu = ?";
            PreparedStatement deleteNumSecuStatement = connection.prepareStatement(deleteNumSecuQuery);
            deleteNumSecuStatement.setInt(1, c.getNumSecu().getnNumSecu());
            deleteNumSecuStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }
    }

    // Méthode pour mettre à jour un client existant (à l'exception du numéro de sécurité sociale)
    public boolean miseAJourClient(Client c, String nouveauNom, String nouveauPrenom, String nouveauTelephone, Double nouveauRevenu, int nouveauNRisque) throws SQLException {
        try {
            String updateClientQuery = "UPDATE CLIENT SET nom = ?, prenom = ?, telephone = ?, revenu = ?, nRisque = ? WHERE nClient = ?";
            PreparedStatement updateClientStatement = connection.prepareStatement(updateClientQuery);
            updateClientStatement.setString(1, nouveauNom);
            updateClientStatement.setString(2, nouveauPrenom);
            updateClientStatement.setString(3, nouveauTelephone);
            updateClientStatement.setDouble(4, nouveauRevenu);
            updateClientStatement.setInt(5, nouveauNRisque);
            updateClientStatement.setInt(6, c.getnClient());

            int rowsAffected = updateClientStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            connection.close();
        }

        return false;
    }

    // Méthode pour fermer la connexion à la base de données
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connexion à la base de données fermée avec succès.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la fermeture de la connexion à la base de données.");
            e.printStackTrace();
        }
    }

    // Méthode pour vérifier si un client existe dans la base de données en fonction de son numéro de sécurité sociale
    private boolean clientExisteDansLaBase(int nNumSecu) throws SQLException {
        String query = "SELECT COUNT(*) FROM CLIENT WHERE nNumSecu = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, nNumSecu);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            int count = resultSet.getInt(1);
            return count > 0; // Le client existe si le nombre de résultats est supérieur à zéro
        }

        return false;
    }
}


