package vuecontrole;

import exceptions.ClientException;
import exceptions.NumSecuException;
import metier.RequeteAssurance;
import modele.Client;
import modele.NumSecu;
import modele.Risque;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class AjoutClient extends JFrame {
    private RequeteAssurance rq;
    private Client client;
    private JPanel addClientPanel;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField numsecuField;
    private JTextField telephoneField;
    private JTextField revenuField;
    private JComboBox<Integer> risqueComboBox;
    private JButton saveClientButton;
    private JLabel nomC;
    private JLabel prenomC;
    private JLabel NumSecuC;
    private JLabel teleC;
    private JLabel revenuC;
    private JLabel nRisqueC;

    public AjoutClient(Client client) {
        this();
        this.client = client;
        remplirFenetre();
    }

    public AjoutClient() throws HeadlessException {
        initialisationFenetre();
        initConnexion();
        remplirComboBoxRisques();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        saveClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (control()) {
                    if (client == null) {
                        if (sauvegarderClient()) {
                            viderChamps();
                        }
                    } else {
                        if (actualiserClient()) {
                            client = null;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(AjoutClient.this,
                            "Veuillez remplir tous les champs.",
                            "Champs obligatoires manquants",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void initialisationFenetre() {
        setTitle("Gestion des clients");
        setSize(800, 600);
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.lightGray);
        getContentPane().add(addClientPanel);

    }

    private void initConnexion() {
        //tester la cnx : question 3
        try {
            rq = RequeteAssurance.getInstance();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Erreur de connexion",
                    JOptionPane.ERROR_MESSAGE);
            dispose();

        }
    }

    private void remplirComboBoxRisques() {
        DefaultComboBoxModel<Integer> risqueModel = new DefaultComboBoxModel<>();
        try {
            List<Risque> risques = rq.ensRisques();
            for (Risque risque : risques) {
                risqueModel.addElement(risque.getNiveau());
            }
        } catch (SQLException e) {
            // "affichez un message d'erreur"
        }
        risqueComboBox.setModel(risqueModel);

    }

    //question 16
    private boolean control() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String numSecu = numsecuField.getText();
        String telephone = telephoneField.getText();
        String revenu = revenuField.getText();
        if (nom.isEmpty() || prenom.isEmpty() || numSecu.isEmpty() || telephone.isEmpty() || revenu.isEmpty()) {
            return false;
        }
        return true;
    }

    //question 17
    private boolean sauvegarderClient() {
        try {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            NumSecu numSecu = new NumSecu(numsecuField.getText());
            int risque = (Integer) risqueComboBox.getSelectedItem();
            String telephone = telephoneField.getText();
            double revenu = Double.parseDouble(revenuField.getText());
            Client client = new Client(nom, prenom, numSecu, risque, telephone, revenu);
            rq.ajouteClient(client);
            JOptionPane.showMessageDialog(this, "Le client a été sauvegardé avec succès.",
                    "Sauvegarde réussie",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (NumSecuException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Erreur : Numéro de securité",
                    JOptionPane.ERROR_MESSAGE);


        } catch (ClientException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Erreur : Client",
                    JOptionPane.ERROR_MESSAGE);


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Erreur : base de données SQL",
                    JOptionPane.ERROR_MESSAGE);

        }
        return false;
    }

    public void viderChamps() {
        nomField.setText("");
        prenomField.setText("");
        telephoneField.setText("");
        numsecuField.setText("");
        revenuField.setText("");
        risqueComboBox.setSelectedIndex(0);
    }

    //Question 20
    private void remplirFenetre() {
        nomField.setText(client.getNom());
        prenomField.setText(client.getPrenom());
        telephoneField.setText(client.getTelephone());
        numsecuField.setText(client.getNumSecu().toString());
        revenuField.setText(String.valueOf(client.getRevenu()));
        System.out.print(client.getRisque());
        risqueComboBox.setSelectedIndex(client.getRisque() - 1);
        numsecuField.setEditable(false);
        saveClientButton.setText("Mettre à jour");
        setTitle("Mise à jour du client");
    }

    private boolean actualiserClient() {
        try {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            int risque = (Integer) risqueComboBox.getSelectedItem();
            String telephone = telephoneField.getText();
            double revenu = Double.parseDouble(revenuField.getText());
            rq.miseAJourClient(client, nom, prenom, telephone, revenu, risque);
            JOptionPane.showMessageDialog(this, "Le client a été mist à jour avec succès.",
                    "Sauvegarde réussie",
                    JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Erreur : base de données SQL",
                    JOptionPane.ERROR_MESSAGE);

        }
        return false;

    }
}
