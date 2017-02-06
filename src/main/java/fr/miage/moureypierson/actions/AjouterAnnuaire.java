package fr.miage.moureypierson.actions;

import com.opensymphony.xwork2.ActionSupport;
import fr.miage.moureypierson.config.HibernateUtil;
import fr.miage.moureypierson.model.Abonne;
import fr.miage.moureypierson.model.Annuaire;
import fr.miage.moureypierson.model.Message;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by nitix on 05/02/17.
 */
public class AjouterAnnuaire extends ActionSupport implements SessionAware {

    private Map<String, Object> session;
    private String nom;

    private boolean isFirst = false;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        if(session.get("login") == null) {
            return LOGIN;
        }
        if(isFirst)
            return SUCCESS;
        Annuaire annuaire = new Annuaire();
        annuaire.setNom(nom);
        Session session = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(annuaire);
            tx.commit();
        }catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
            return ERROR;
        }finally {
            if (session != null) {
                session.close();
            }
        }
        addActionMessage("Annuaire créé");
        return SUCCESS;
    }

    @Override
    public void validate() {
        if(nom == null) {
            isFirst = true;
            return;
        }
        if (this.nom.isEmpty()) {
            addFieldError("nom", "Nom vide");
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
