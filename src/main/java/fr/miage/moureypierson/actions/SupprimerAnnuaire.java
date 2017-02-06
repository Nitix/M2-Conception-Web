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
public class SupprimerAnnuaire extends ActionSupport implements SessionAware {

    private final List<Annuaire> annuaires;
    private Map<String, Object> session;
    private Long id;

    private boolean isFirst = false;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public SupprimerAnnuaire() {
        this.annuaires = Annuaire.findAll();
        this.annuaires.remove(new Annuaire().setId(1));
    }

    @Override
    public String execute() throws Exception {
        if(session.get("login") == null) {
            return LOGIN;
        }
        if(isFirst)
            return SUCCESS;
        Annuaire annuaire = new Annuaire();
        annuaire.setId(id);
        Session session = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(annuaire);
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
        addActionMessage("Annuaire supprimé");
        return SUCCESS;
    }

    @Override
    public void validate() {
        if(id == null) {
            isFirst = true;
            return;
        }
        if (this.id == 1) {
            addFieldError("id", "Impossible de supprimer le premier annuaire");
        }
    }

    public void setId(String id) {
        this.id = Long.valueOf(id);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Annuaire> getAnnuaires() {
        return annuaires;
    }
}
