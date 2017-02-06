package fr.miage.moureypierson.actions;

import com.opensymphony.xwork2.ActionSupport;
import fr.miage.moureypierson.model.Abonne;
import fr.miage.moureypierson.model.Message;

import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nitix on 05/02/17.
 */
public class Accueil extends ActionSupport implements SessionAware {

    private Abonne user;
    private Map<String, Object> session;
    private List<Message> messages;

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public String execute() throws Exception {
        this.user = Abonne.findById((Long) session.get("login"));
        if(session.get("login") == null) {
            return LOGIN;
        }
        messages = user.getMessages();
        return SUCCESS;
    }


    public List<Message> getMessages() {
        return messages;
    }

    public Abonne getUser() {
        return user;
    }
}
