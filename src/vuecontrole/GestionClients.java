package vuecontrole;

import metier.RequeteAssurance;
import modele.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

public class GestionClients extends JFrame {
    private JPanel clientPanel;
    private JTextField searchField;
    private JList<Client> list1;
    private JButton addButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JLabel searchLabel;
    private RequeteAssurance rq;

    public GestionClients() throws SQLException {
        initialisationFenetre();
        initConnexion();
        remplirListeClients("");
        //question 7
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String filterText = searchField.getText();
                try {
                    remplirListeClients(filterText);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutClient ajoutClientWindow = null;
                ajoutClientWindow = new AjoutClient();
                ajoutClientWindow.setVisible(true);
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client clientSelectionne = list1.getSelectedValue();
                if (clientSelectionne == null) {
                    return;
                }
                AjoutClient modifierClientWindow = new AjoutClient(clientSelectionne);
                modifierClientWindow.setVisible(true);

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client clientSelectionne = list1.getSelectedValue();
                if (clientSelectionne == null) {
                    JOptionPane.showMessageDialog(GestionClients.this,
                            "Veuillez sélectionner un client à supprimer.",
                            "Aucun client sélectionné",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int choice = JOptionPane.showConfirmDialog(GestionClients.this,
                        "Voulez-vous vraiment supprimer ce client ?",
                        "Confirmation de suppression",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    try {
                        rq.supprimerClient(clientSelectionne);
                        remplirListeClients("");
                    } catch (
                            SQLException ex) {
                        JOptionPane.showMessageDialog(GestionClients.this,
                                "Erreur lors de la suppression du client : " + ex.getMessage(),
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
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

    private void initialisationFenetre() {
        setTitle("Gestion des clients");
        setSize(800, 600);
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.lightGray);
        getContentPane().add(clientPanel);

    }

    private void remplirListeClients(String nomprenom) throws SQLException {

        List<Client> clients = rq.ensClients(nomprenom);
        DefaultListModel<Client> listModel = new DefaultListModel<>();
        for (Client client : clients) {
            listModel.addElement(client);
        }
        list1.setModel(listModel);

    }
}
