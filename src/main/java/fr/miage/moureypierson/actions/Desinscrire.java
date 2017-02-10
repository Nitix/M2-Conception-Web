package fr.miage.moureypierson.actions;

import com.opensymphony.xwork2.ActionSupport;
import fr.miage.moureypierson.config.HibernateUtil;
import fr.miage.moureypierson.model.Abonne;
import fr.miage.moureypierson.model.Annuaire;
import fr.miage.moureypierson.model.Message;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

/**
 * Created by nitix on 06/02/17.
 */
public class Desinscrire extends ActionSupport implements SessionAware{

    private Map<String, Object> session;
    private List<Message> messages;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        if(session.get("login") == null) {
            return LOGIN;
        }
        Abonne abonne = Abonne.findById((Long) this.session.get("login"));

        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(abonne);
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


        this.session.clear();
        addActionMessage("Vous êtes bien désinscrit");
        return SUCCESS;
    }


    public List<Message> getMessages() {
        return messages;
    }
}
