package fr.miage.moureypierson.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nitix on 12/11/16.
 */
@Entity
public class Message {

    @Id
    @GeneratedValue(generator = "increment")
    private long id;

    private String objet;

    private String corps;

    @ManyToOne
    private Abonne expediteur;

    @OneToMany
    private Set<Abonne> destinataires;

    public long getId() {
        return id;
    }

    public Message setId(long id) {
        this.id = id;
        return this;
    }

    public String getObjet() {
        return objet;
    }

    public Message setObjet(String objet) {
        this.objet = objet;
        return this;
    }

    public String getCorps() {
        return corps;
    }

    public Message setCorps(String corps) {
        this.corps = corps;
        return this;
    }

    public Abonne getExpediteur() {
        return expediteur;
    }

    public Message setExpediteur(Abonne expediteur) {
        this.expediteur = expediteur;
        return this;
    }

    public Set<Abonne> getDestinataires() {
        return destinataires;
    }

    public Message setDestinataires(Set<Abonne> destinataires) {
        this.destinataires = destinataires;
        return this;
    }

    public void addDestinataire(Abonne abonne){
        this.destinataires.add(abonne);
    }
}
