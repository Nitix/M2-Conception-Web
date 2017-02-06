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
public class EnvoyerMessage extends ActionSupport implements SessionAware {

    private List<Annuaire> annuaires;

    private Map<String, Object> session;
    private String objet;
    private String contenu;

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
        annuaires = Annuaire.findAll();
        if(isFirst)
            return SUCCESS;
        Abonne abonne = Abonne.findById((Long) session.get("login"));
        Message message = new Message();
        message.setExpediteur(abonne);
        List<Abonne> dests = new LinkedList<>(abonne.getAbonnement().getAbonnes());
        message.setDestinataires(dests);
        message.setCorps(contenu);
        message.setObjet(objet);
        Session session = null;
        Transaction tx = null;

        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(message);
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
        addActionMessage("Message envoyé");
        return SUCCESS;
    }


    public List<Annuaire> getAnnuaires() {
        return annuaires;
    }


    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getObjet() {
        return objet;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getContenu() {
        return contenu;
    }

    @Override
    public void validate() {
        if(objet == null) {
            isFirst = true;
            return;
        }
        if (this.objet.isEmpty()) {
            addFieldError("objet", "Objet vide");
        }
        if (this.contenu.isEmpty()) {
            addFieldError("contenu", "Contenu vide");
        }
    }
}
