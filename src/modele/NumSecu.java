package modele;

import exceptions.NumSecuException;

public class NumSecu {
    private int nNumSecu;
    private int sexe;
    private int anneeNaissance;
    private int moisNaissance;
    private int departement;
    private int commune;
    private int ordre;
    private int cle;

    public NumSecu(int nNumSecu, int sexe, int anneeNaissance, int moisNaissance, int departement, int commune, int ordre, int cle) throws NumSecuException {
        validate(sexe, anneeNaissance, moisNaissance, departement, commune, ordre, cle);
        this.nNumSecu = nNumSecu;
        this.sexe = sexe;
        this.anneeNaissance = anneeNaissance;
        this.moisNaissance = moisNaissance;
        this.departement = departement;
        this.commune = commune;
        this.ordre = ordre;
        this.cle = cle;
    }

    public NumSecu(int sexe, int anneeNaissance, int moisNaissance, int departement, int commune, int ordre, int cle) throws NumSecuException {
        validate(sexe, anneeNaissance, moisNaissance, departement, commune, ordre, cle);
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

    public void validate(int sexe, int anneeNaissance, int moisNaissance, int departement, int commune, int ordre, int cle) throws NumSecuException {
        long n = numSecuSansCle(sexe, anneeNaissance, moisNaissance, departement, commune, ordre);
        long cleCalculee = 97 - (n % 97);
        if (sexe != 1 && sexe != 2) {
            throw new NumSecuException("Invalid sexe value: " + sexe);
        }

        if (anneeNaissance < 0 || anneeNaissance > 99) {
            throw new NumSecuException("Invalid anneeNaissance value: " + anneeNaissance);
        }

        if (moisNaissance < 1 || moisNaissance > 12) {
            throw new NumSecuException("Invalid moisNaissance value: " + moisNaissance);
        }

        if (departement < 1 || departement > 95) {
            throw new NumSecuException("Invalid departement value: " + departement);
        }

        if (commune < 1 || commune > 990) {
            throw new NumSecuException("Invalid commune value: " + commune);
        }

        if (ordre < 1 || ordre > 999) {
            throw new NumSecuException("Invalid ordre value: " + ordre);
        }

        if (cle < 1 || cle > 97 || cle != cleCalculee) {
            throw new NumSecuException("Invalid cle value: " + cle);
        }
    }

    public long numSecuSansCle(int sexe, int anneeNaissance, int moisNaissance, int departement, int commune, int ordre) {

        String formattedSexe = String.format("%d", sexe);
        String formattedAnneeNaissance = String.format("%02d", anneeNaissance);
        String formattedMoisNaissance = String.format("%02d", moisNaissance);
        String formattedDepartement = String.format("%02d", departement);
        String formattedCommune = String.format("%03d", commune);
        String formattedOrdre = String.format("%03d", ordre);

        String numSecuSansCle = formattedSexe + formattedAnneeNaissance + formattedMoisNaissance +
                formattedDepartement + formattedCommune + formattedOrdre;
        return Long.parseLong(numSecuSansCle);
    }

    @Override
    public String toString() {
        return "" + sexe + anneeNaissance + moisNaissance + departement + commune + ordre + "" + cle;

    }
}
