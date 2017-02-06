package fr.miage.moureypierson.model;

import fr.miage.moureypierson.config.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

    private Boolean administrator = false;

    @ManyToOne
    private Annuaire abonnement;

    @ManyToMany(mappedBy = "destinataires", cascade = CascadeType.ALL)
    private List<Message> messages = new LinkedList<>();

    public Abonne() {

    }

    public Abonne(String mdp, String login) {
        this.mdp = mdp;
        this.login = login;
    }

    public static Abonne findById(Long id) {
        if (id == null)
            return null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Abonne abonne = (Abonne) session.get(Abonne.class, id);
            Hibernate.initialize(abonne.getMessages());
            return abonne;
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

    public Annuaire getAbonnement() {
        return abonnement;
    }

    public void setAbonnements(Annuaire abonnement) {
        this.abonnement = abonnement;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getName() {
        if (this instanceof Particulier) {
            return ((Particulier) this).getNom() + " " + ((Particulier) this).getPrenom();
        } else {
            return ((Entreprise) this).getRaisonSociale();
        }
    }


    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abonne abonne = (Abonne) o;
        return Objects.equals(id, abonne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
