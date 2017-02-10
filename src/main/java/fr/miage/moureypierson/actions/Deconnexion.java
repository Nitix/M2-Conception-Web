package fr.miage.moureypierson.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by nitix on 10/02/17.
 */
public class Deconnexion extends ActionSupport implements SessionAware
{
    private Map<String, Object> session;

    public String execute() {
        this.session.clear();
        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
