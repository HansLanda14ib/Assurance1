import metier.RequeteAssurance;
import modele.Client;
import modele.NumSecu;
import modele.Risque;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Créez une instance de la classe RequeteAssurance
        RequeteAssurance requeteAssurance = new RequeteAssurance();
       /* try {
            List<Risque> risques = requeteAssurance.ensRisques();
            for (Risque risque : risques) {
                System.out.println("nRisque: " + risque.getnRisque() + ", Niveau: " + risque.getNiveau());
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des risques.");
            e.printStackTrace();
        } finally {
            // Fermez la connexion lorsque vous avez terminé
            requeteAssurance.closeConnection();
        }
        */
        /*
        try {
            List<Client> clients = requeteAssurance.ensClients("ga");

            // Affichez la liste des clients triée par nom
            for (Client client : clients) {
                System.out.println(client.getNom() + "|" + client.getPrenom() + "|" + client.getNumSecu().getSexe() + "|" + client.getRisque().getNiveau());
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des clients.");
            e.printStackTrace();
        } finally {
            // Fermez la connexion lorsque vous avez terminé
            requeteAssurance.closeConnection();
        }
        */

        /*
        try {
            // Créez un objet NumSecu avec les données appropriées
            NumSecu numSecu = new NumSecu(2, 83, 8, 6, 712, 784, 39); // Exemple de données

            // Appelez la méthode ajouteNumSecu() pour ajouter le numéro de sécurité sociale
            int cleGeneree = requeteAssurance.ajouteNumSecu(numSecu);

            if (cleGeneree > 0) {
                System.out.println("Numéro de sécurité sociale ajouté avec succès. Clé générée : " + cleGeneree);
            } else {
                System.err.println("Échec de l'ajout du numéro de sécurité sociale.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout du numéro de sécurité sociale.");
            e.printStackTrace();
        } finally {
            // Fermez la connexion lorsque vous avez terminé
            requeteAssurance.closeConnection();
        }
         */


        NumSecu numSecu = new NumSecu(2, 83, 8, 6, 712, 784, 20);
        Client client = new Client("hamza", "lola", numSecu, 1, "0684545778", 9000);
        try {
            boolean insertionReussie = requeteAssurance.ajouteClient(client);
            if (insertionReussie) {
                System.out.println("Ajout reussi");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion.");
            e.printStackTrace();
        }
    }

}
