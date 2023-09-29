import exceptions.ClientException;
import exceptions.NumSecuException;
import metier.RequeteAssurance;
import modele.Client;
import modele.NumSecu;
import modele.Risque;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        //RequeteAssurance requeteAssurance = new RequeteAssurance();
       /* String text = "012345678912345";
        int sexe = Character.getNumericValue(text.charAt(0));
        int anneeNaissance = Integer.parseInt(text.substring(1, 3));
        int moisNaissance = Integer.parseInt(text.substring(3, 5));
        int departement = Integer.parseInt(text.substring(5, 7));
        int commune = Integer.parseInt(text.substring(7, 10));
        int ordre = Integer.parseInt(text.substring(10, 13));
        int cle = Integer.parseInt(text.substring(13));
        System.out.println(sexe);
        System.out.println(anneeNaissance);
        System.out.println(moisNaissance);
        System.out.println(departement);
        System.out.println(commune);
        System.out.println(ordre);
        System.out.println(cle); */
        //Question 7
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

        //Question 8/9
/*
        try {
            List<Client> clients = requeteAssurance.ensClients("Bo");

            // Affichez la liste des clients triée par nom
            for (Client client : clients) {
                System.out.println(client.getNom() + "|" + client.getPrenom() + "|" + client.getNumSecu().getSexe());
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des clients.");
            e.printStackTrace();
        } finally {
            // Fermez la connexion lorsque vous avez terminé
            requeteAssurance.closeConnection();
        }

*/
        //Question 10
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

        //Question 11
       /* NumSecu numSecu = new NumSecu(2, 83, 8, 6, 712, 784, 20);
        Client client = new Client("hamza", "lola", numSecu, 1, "0684545778", 9000);
        try {
            boolean insertionReussie = requeteAssurance.ajouteClient(client);
            if (insertionReussie) {
                System.out.println("Ajout reussi");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion.");
            e.printStackTrace();
        } */

        //Question 12
        /*
        try {
            Client clientASupprimer = new Client();
            clientASupprimer.setNumSecu(new NumSecu(20, 2, 82, 9, 24, 844, 22, 9));
            boolean suppressionReussie = requeteAssurance.supprimerClient(clientASupprimer);
            if (suppressionReussie) {
                System.out.println("Suppression réussie !");
            } else {
                System.out.println("La suppression a échoué.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression du client : " + e.getMessage());
        }
        */

        //Question 13
        /*
        try {
            NumSecu numSecu = new NumSecu(19, 2, 71, 7, 12, 700, 40, 1);
            Client clientExistant = new Client(19, "Curry", "Marie", numSecu, 2, "0226268234", 86600);

            boolean miseAJourReussie = requeteAssurance.miseAJourClient(clientExistant, "nouveauNom", "nouveauPrenom", "111111", 9999.0, 1);

            if (miseAJourReussie) {
                System.out.println("Mise à jour du client réussie !");
            } else {
                System.out.println("La mise à jour du client a échoué.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la mise a jour du client : " + e.getMessage());
        }
         */

        // Question 15
        /* try {
            // Test with valid values
            NumSecu validNumSecu = new NumSecu(2, 83, 8, 6, 712, 784, 39);
            System.out.println("Valid NumSecu created: " + validNumSecu.toString());

            NumSecu invalidNumSecu = new NumSecu(2, 83, 8, 6, 712, 784, 100);
            System.out.println("Invalid NumSecu created: " + validNumSecu.toString());
        } catch (NumSecuException e) {
            System.err.println("NumSecuException: " + e.getMessage());
        } */
        //Question 16
        /*
        try {
            NumSecu numSecu = new NumSecu(2, 83, 8, 6, 712, 784, 39);
            Client client = new Client("hamza", "moustaid", numSecu, 1, "0684545778", 9000);
        } catch (NumSecuException | ClientException e) {
            System.err.println("NumSecuException: " + e.getMessage());
        }
        */
    }
}
