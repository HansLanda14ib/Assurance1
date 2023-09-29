package vuecontrole;

import metier.RequeteAssurance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Accueil extends JFrame {

    private JPanel panelmain;
    private JButton clientButton;

    public Accueil() throws HeadlessException {
        initialisationFenetre();
        initConnexion();

        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestionClients gestionClientsWindow = null;
                try {
                    gestionClientsWindow = new GestionClients();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                gestionClientsWindow.setVisible(true);
            }
        });
    }

    private void initConnexion() {
        //tester la cnx : question 3
       /* try {
            rq = new RequeteAssurance();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Erreur de connexion",
                    JOptionPane.ERROR_MESSAGE);
            dispose();

        } */
    }

    private void initialisationFenetre() {
        setTitle("Bienvenue dans l'application de gestion des contrats");
        setSize(500, 300);
        setLocationRelativeTo(null); // Centre la fenêtre sur l'écran
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.lightGray);
        getContentPane().add(panelmain);
        //getContentPane().add(button1);
    }

    public static void main(String[] args) {
        // Crée une instance de la classe Accueil (fenêtre)
        Accueil fenetre = new Accueil();
        fenetre.setVisible(true); // Affiche la fenêtre
    }
}
