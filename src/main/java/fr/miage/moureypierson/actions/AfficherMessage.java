package fr.miage.moureypierson.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by nitix on 05/02/17.
 */
public class AfficherMessage extends ActionSupport implements SessionAware {

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
        return SUCCESS;
    }
}
