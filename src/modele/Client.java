package modele;

import exceptions.ClientException;

public class Client {
    private int nClient;
    private String nom;
    private String prenom;
    private NumSecu numSecu; // Ajout de l'objet NumSecu
    private int risque;   // Ajout de l'objet Risque
    private String telephone;
    private double revenu;

    public Client() {
    }

    public Client(int nClient, String nom, String prenom, NumSecu numSecu, int risque, String telephone, double revenu) throws ClientException {
        validate(nom, prenom, telephone, revenu, risque, numSecu);
        this.nClient = nClient;
        this.nom = nom;
        this.prenom = prenom;
        this.numSecu = numSecu;
        this.risque = risque;
        this.telephone = telephone;
        this.revenu = revenu;
    }

    public Client(String nom, String prenom, NumSecu numSecu, int risque, String telephone, double revenu) throws ClientException {
        validate(nom, prenom, telephone, revenu, risque, numSecu);
        this.nom = nom;
        this.prenom = prenom;
        this.numSecu = numSecu;
        this.risque = risque;
        this.telephone = telephone;
        this.revenu = revenu;
    }

    public int getnClient() {
        return nClient;
    }

    public void setnClient(int nClient) {
        this.nClient = nClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public NumSecu getNumSecu() {
        return numSecu;
    }

    public void setNumSecu(NumSecu numSecu) {
        this.numSecu = numSecu;
    }

    public int getRisque() {
        return risque;
    }

    public void setRisque(int risque) {
        this.risque = risque;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public double getRevenu() {
        return revenu;
    }

    public void setRevenu(double revenu) {
        this.revenu = revenu;
    }

    private void validate(String nom, String prenom, String telephone, double revenu, int nRisque, NumSecu numSecu) throws ClientException {
        if (nom.isEmpty() || prenom.isEmpty()) {
            throw new ClientException("Le nom et le prénom ne peuvent pas être vides");
        }

        if (revenu < 0) {
            throw new ClientException("Le revenu doit être positif");
        }

        if (nRisque == 0) {
            throw new ClientException("Le risque ne peut pas être null");
        }

        if (numSecu == null) {
            throw new ClientException("Le numéro de sécurité sociale ne peut pas être null");
        }

    }
}
