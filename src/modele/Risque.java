package modele;

public class Risque {
    private int nRisque;
    private int niveau;

    // Constructeur
    public Risque(int nRisque, int niveau) {
        this.nRisque = nRisque;
        this.niveau = niveau;
    }

    public int getnRisque() {
        return nRisque;
    }

    public void setnRisque(int nRisque) {
        this.nRisque = nRisque;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
}
