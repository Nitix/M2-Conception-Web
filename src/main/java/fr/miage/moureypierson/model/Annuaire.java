package fr.miage.moureypierson.model;

import fr.miage.moureypierson.config.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by nitix on 12/11/16.
 */
@Entity
public class Annuaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    private String nom;

    @ManyToMany
    private Set<Abonne> abonnes = new LinkedHashSet<>();

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

    public static Annuaire findById(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return (Annuaire) session.get(Annuaire.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
