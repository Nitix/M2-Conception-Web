package fr.miage.moureypierson.model;

import fr.miage.moureypierson.config.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by nitix on 12/11/16.
 */
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String objet;

    @Column(columnDefinition="Text")
    private String corps;

    @ManyToOne
    private Abonne expediteur;

    @ManyToMany
    private List<Abonne> destinataires = new LinkedList<>();

    private String file;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }
}
