package fr.miage.moureypierson.actions;

import com.opensymphony.xwork2.ActionSupport;
import fr.miage.moureypierson.config.HibernateUtil;
import fr.miage.moureypierson.model.Abonne;
import fr.miage.moureypierson.model.Annuaire;
import fr.miage.moureypierson.model.Entreprise;
import fr.miage.moureypierson.model.Particulier;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Map;

/**
 * Created by nitix on 26/01/17.
 */
public class Inscription extends ActionSupport implements SessionAware{

    private final List<Annuaire> annuaires;
    private String login = "";
    private String password = "";
    private boolean entreprise = false;
    private String nom = "";
    private String prenom = "";
    private String raison = "";
    private Map<String, Object> session;
    private String annuaire;

    public Inscription() {
        this.annuaires = Annuaire.findAll();
        this.annuaires.remove(new Annuaire().setId(1));
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.replaceAll("(^\\h*)|(\\h*$)", "");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.replaceAll("(^\\h*)|(\\h*$)", "");
    }

    public boolean getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(boolean entreprise) {
        this.entreprise = entreprise;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom.replaceAll("(^\\h*)|(\\h*$)", "");
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom.replaceAll("(^\\h*)|(\\h*$)", "");
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison.replaceAll("(^\\h*)|(\\h*$)", "");
    }

    @Override
    public String execute() throws Exception {
        Abonne abonne;
        System.out.println(entreprise);
        if (entreprise) {
            abonne = new Entreprise().setRaisonSociale(raison);
        } else {
            abonne = new Particulier()
                    .setNom(nom)
                    .setPrenom(prenom);
        }
        abonne.setLogin(login);
        abonne.setMdp(BCrypt.hashpw(password, BCrypt.gensalt()));

        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Annuaire annuaire = Annuaire.findById(1L);
            annuaire.addAbonne(abonne);
            session.persist(abonne);
            session.update(annuaire);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            addActionError("Erreur non attendue, la base de données est peut-être hors service");
            return ERROR;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        this.session.put("login", abonne.getId());
        return SUCCESS;

    }

    @Override
    public void validate() {
        if (this.login.isEmpty()) {
            addFieldError("login", "Login vide");
        } else {
            Abonne abonne = Abonne.findByLogin(login);
            if(abonne != null) {
                addFieldError("login", "Ce login est déjà utilisé");
            }
        }
        if (this.password.isEmpty()) {
            addFieldError("password", "Mot de passe vide");
        }
        if (entreprise) {
            if (this.raison.isEmpty()) {
                addFieldError("raison", "La raison sociale est vide");
            }
        } else {
            if (this.nom.isEmpty()) {
                addFieldError("nom", "Le nom est vide");
            }
            if (this.prenom.isEmpty()) {
                addFieldError("prenom", "Le prénom est vide");
            }
        }
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public List<Annuaire> getAnnuaires() {
        return annuaires;
    }

    public void setAnnuaire(String annuaire) {
        this.annuaire = annuaire;
    }

    public String getAnnuaire() {
        return annuaire;
    }
}
