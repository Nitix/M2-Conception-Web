package fr.miage.moureypierson.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    public Entreprise setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }


}
