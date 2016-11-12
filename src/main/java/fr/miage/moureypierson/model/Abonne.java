package fr.miage.moureypierson.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Created by nitix on 12/11/16.
 */
@Entity
public abstract class Abonne {

    @Id
    @GeneratedValue(generator = "increment")
    private long id;

    /**
     * Password of the abonne
     */
    private String mdp;

    /**
     * Login of the abonne
     */
    private String login;

    @ManyToMany
    private Set<Annuaire> abonnements;

    public Abonne() {

    }

    public Abonne(String mdp, String login) {
        this.mdp = mdp;
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public Abonne setId(long id) {
        this.id = id;
        return this;
    }

    public String getMdp() {
        return mdp;
    }

    public Abonne setMdp(String mdp) {
        this.mdp = mdp;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Abonne setLogin(String login) {
        this.login = login;
        return this;
    }

    public Set<Annuaire> getAbonnements() {
        return abonnements;
    }

    public Abonne setAbonnements(Set<Annuaire> abonnements) {
        this.abonnements = abonnements;
        return this;
    }

    public void addAbonnement(Annuaire annuaire) {
        this.abonnements.add(annuaire);
    }
}
