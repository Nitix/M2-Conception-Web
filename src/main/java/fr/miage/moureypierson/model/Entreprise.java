package fr.miage.moureypierson.model;

import javax.persistence.Entity;

/**
 * Created by nitix on 12/11/16.
 */
@Entity
public class Entreprise extends Abonne {

    private String raisonSociale;

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }
}
