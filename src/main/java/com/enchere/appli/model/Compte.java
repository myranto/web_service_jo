package com.enchere.appli.model;

import com.enchere.appli.dao.CompteDAO;

public class Compte {

    private int id;
    private Utilisateur utilisateur;
    private boolean isBlocked;
    private double valeur_actuel;

    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Utilisateur return the utilisateur
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * @param utilisateur the utilisateur to set
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     * @return boolean return the isBlocked
     */
    public boolean isIsBlocked() {
        return isBlocked;
    }

    /**
     * @param isBlocked the isBlocked to set
     */
    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    /**
     * @return double return the valeur_actuel
     */
    public double getValeur_actuel() {
        return valeur_actuel;
    }

    /**
     * @param valeur_actuel the valeur_actuel to set
     */
    public void setValeur_actuel(double valeur_actuel) {
        this.valeur_actuel = valeur_actuel;

    }

    public Compte() {
    }

    public Compte updateSolde() throws Exception {

        CompteDAO.updateCompte(this.getId(), this.getValeur_actuel());
        return this;

    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", utilisateur='" + getUtilisateur() + "'" +
                ", isBlocked='" + isIsBlocked() + "'" +
                ", valeur_actuel='" + getValeur_actuel() + "'" +
                "}";
    }

    public void retrait(double montant) throws Exception
    {
        if(montant> this.getValeur_actuel())
        {
            throw new Exception("");
        }
        else
        {
            double after_retrait = this.getValeur_actuel() - montant;
            this.setValeur_actuel(after_retrait);
            this.updateSolde();
        }
    }
}
