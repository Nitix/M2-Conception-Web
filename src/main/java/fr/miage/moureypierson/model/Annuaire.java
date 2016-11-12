package fr.miage.moureypierson.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by nitix on 12/11/16.
 */
@Entity
public class Annuaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    private String nom;

    @ManyToMany
    private Set<Abonne> abonnes;

    public long getId() {
        return id;
    }

    public Annuaire setId(long id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Annuaire setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public Set<Abonne> getAbonnes() {
        return abonnes;
    }

    public Annuaire setAbonnes(Set<Abonne> abonnes) {
        this.abonnes = abonnes;
        return this;
    }

    public void addAbonne(Abonne abonne) {
        this.abonnes.add(abonne);
    }
}
