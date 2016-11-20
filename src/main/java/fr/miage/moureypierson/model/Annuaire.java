package fr.miage.moureypierson.model;

import fr.miage.moureypierson.config.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
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
    private List<Abonne> abonnes;


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

    public List<Abonne> getAbonnes() {
        return abonnes;
    }

    public Annuaire setAbonnes(List<Abonne> abonnes) {
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
            Annuaire annuaire = (Annuaire) session.get(Annuaire.class, id);
            Hibernate.initialize(annuaire.getAbonnes());
            return annuaire;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static List<Annuaire> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Annuaire.class);
            return (List<Annuaire>) criteria.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
