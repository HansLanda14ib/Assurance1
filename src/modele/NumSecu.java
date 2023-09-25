package modele;

public class NumSecu {
    private int nNumSecu;
    private int sexe;
    private int anneeNaissance;
    private int moisNaissance;
    private int departement;
    private int commune;
    private int ordre;
    private int cle;

    public NumSecu(int nNumSecu, int sexe, int anneeNaissance, int moisNaissance, int departement, int commune, int ordre, int cle) {
        this.nNumSecu = nNumSecu;
        this.sexe = sexe;
        this.anneeNaissance = anneeNaissance;
        this.moisNaissance = moisNaissance;
        this.departement = departement;
        this.commune = commune;
        this.ordre = ordre;
        this.cle = cle;
    }

    public NumSecu(int sexe, int anneeNaissance, int moisNaissance, int departement, int commune, int ordre, int cle) {
        this.sexe = sexe;
        this.anneeNaissance = anneeNaissance;
        this.moisNaissance = moisNaissance;
        this.departement = departement;
        this.commune = commune;
        this.ordre = ordre;
        this.cle = cle;
    }

    public int getnNumSecu() {
        return nNumSecu;
    }

    public void setnNumSecu(int nNumSecu) {
        this.nNumSecu = nNumSecu;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public int getAnneeNaissance() {
        return anneeNaissance;
    }

    public void setAnneeNaissance(int anneeNaissance) {
        this.anneeNaissance = anneeNaissance;
    }

    public int getMoisNaissance() {
        return moisNaissance;
    }

    public void setMoisNaissance(int moisNaissance) {
        this.moisNaissance = moisNaissance;
    }

    public int getDepartement() {
        return departement;
    }

    public void setDepartement(int departement) {
        this.departement = departement;
    }

    public int getCommune() {
        return commune;
    }

    public void setCommune(int commune) {
        this.commune = commune;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public int getCle() {
        return cle;
    }

    public void setCle(int cle) {
        this.cle = cle;
    }
}
