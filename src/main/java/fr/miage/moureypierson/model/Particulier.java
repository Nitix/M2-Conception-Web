package fr.miage.moureypierson.model;

/**
 * Created by nitix on 12/11/16.
 */
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
