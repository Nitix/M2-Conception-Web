package fr.miage.moureypierson.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by nitix on 12/11/16.
 */
@Entity
public class Particulier extends Abonne {

    private String nom;

    private String prenom;

    public String getNom() {
        return nom;
    }

    public Particulier setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public Particulier setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }
}
