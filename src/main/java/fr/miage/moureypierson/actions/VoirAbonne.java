package fr.miage.moureypierson.actions;

import com.opensymphony.xwork2.ActionSupport;
import fr.miage.moureypierson.config.HibernateUtil;
import fr.miage.moureypierson.model.Abonne;
import fr.miage.moureypierson.model.Annuaire;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by nitix on 10/02/17.
 */
public class VoirAbonne  extends ActionSupport implements SessionAware{
    private List<Abonne> abonnes;
    private Map<String, Object> session;


    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        if(session.get("login") == null) {
            return LOGIN;
        }
        Abonne abonne = Abonne.findById((Long) session.get("login"));
        this.abonnes = new LinkedList<>(abonne.getAbonnement().getAbonnes());
        return SUCCESS;
    }

    public List<Abonne> getAbonnes() {
        return abonnes;
    }
}
