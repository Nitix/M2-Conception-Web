package fr.miage.moureypierson.model;

import fr.miage.moureypierson.config.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nitix on 12/11/16.
 */
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String objet;

    private String corps;

    @ManyToOne
    private Abonne expediteur;

    @ManyToMany
    private List<Abonne> destinataires = new LinkedList<>();

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

    public List<Abonne> getDestinataires() {
        return destinataires;
    }

    public Message setDestinataires(List<Abonne> destinataires) {
        this.destinataires = destinataires;
        return this;
    }

    public void addDestinataire(Abonne abonne){
        this.destinataires.add(abonne);
    }

    public static Message findById(long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return (Message) session.get(Message.class, id);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
