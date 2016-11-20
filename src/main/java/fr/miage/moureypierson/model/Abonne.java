package fr.miage.moureypierson.model;

import fr.miage.moureypierson.config.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by nitix on 12/11/16.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Abonne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Password of the abonne
     */
    private String mdp;

    /**
     * Login of the abonne
     */
    @Column(unique = true)
    private String login;

    @ManyToMany
    private Set<Annuaire> abonnements = new LinkedHashSet<>();

    public Abonne() {

    }

    public Abonne(String mdp, String login) {
        this.mdp = mdp;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Annuaire> getAbonnements() {
        return abonnements;
    }

    public void setAbonnements(Set<Annuaire> abonnements) {
        this.abonnements = abonnements;
    }

    public void addAbonnement(Annuaire annuaire) {
        this.abonnements.add(annuaire);
    }

    public static Abonne findById(long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return (Abonne) session.get(Abonne.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static Abonne findByLogin(String login) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Abonne.class);
            criteria.add(Restrictions.eq("login", login));
            return (Abonne) criteria.uniqueResult();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
