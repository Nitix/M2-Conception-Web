package fr.miage.moureypierson.actions;

import com.opensymphony.xwork2.ActionSupport;
import fr.miage.moureypierson.model.Abonne;
import fr.miage.moureypierson.model.Annuaire;
import org.apache.struts2.interceptor.SessionAware;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Map;

/**
 * Created by nitix on 25/01/17.
 */
public class Identification extends ActionSupport implements SessionAware{

    private String login;
    private String password = "";

    private Map<String, Object> session;

    private boolean isFirst = false;
    private List<Annuaire> annuaires;

    public Identification() {
        this.annuaires = Annuaire.findAll();
        this.annuaires.remove(new Annuaire().setId(1));
    }

    public String execute() {
        if(isFirst) {
            return INPUT;
        }
        Abonne abonne = Abonne.findByLogin(login);
        System.out.println(abonne);
        if(abonne != null && BCrypt.checkpw(password, abonne.getMdp())) {
            session.put("login", abonne.getId());
            return SUCCESS;
        } else {
            addActionError("Identifiant ou mot de passe incorrect.");
            return ERROR;
        }

    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void validate() {
        if(login == null) {
            isFirst = true;
            return;
        }
        if (this.login.isEmpty()) {
            addFieldError("login", "Login vide");
        }
        if (this.password.isEmpty()) {
            addFieldError("password", "Mot de passe vide");
        }
    }

    public List<Annuaire> getAnnuaires() {
        return annuaires;
    }
}
