package fr.miage.moureypierson.actions;

import com.opensymphony.xwork2.ActionSupport;
import fr.miage.moureypierson.config.HibernateUtil;
import fr.miage.moureypierson.model.Abonne;
import fr.miage.moureypierson.model.Annuaire;
import fr.miage.moureypierson.model.Message;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by nitix on 05/02/17.
 */
public class EnvoyerMessage extends ActionSupport implements SessionAware, ServletContextAware {

    private List<Annuaire> annuaires;

    private Map<String, Object> session;
    private String objet;
    private String contenu;

    private boolean isFirst = false;


    private File fichier;
    private String fichierContentType;
    private String fichierFileName;
    private String filesPath;

    private ServletContext context;


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
        UUID uuid = UUID.randomUUID();

        Abonne abonne = Abonne.findById((Long) session.get("login"));
        Message message = new Message();
        message.setExpediteur(abonne);
        List<Abonne> dests = new LinkedList<>(abonne.getAbonnement().getAbonnes());
        message.setDestinataires(dests);
        message.setCorps(contenu);
        message.setObjet(objet);
        if(fichierFileName != null){
            message.setFile(uuid.toString() + "/" + fichierFileName);
            saveFile(getFichier(), fichierFileName, context.getRealPath("") + File.separator + filesPath + File.separator + uuid.toString() );
        }
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

    public void setFichier(File fichier) {
        this.fichier = fichier;
    }

    public File getFichier() {
        return fichier;
    }

    public String getFichierContentType() {
        return fichierContentType;
    }

    public void setFichierContentType(String fichierContentType) {
        this.fichierContentType = fichierContentType;
    }

    public String getFichierFileName() {
        return fichierFileName;
    }

    public void setFichierFileName(String fichierFileName) {
        this.fichierFileName = fichierFileName;
    }

    public static void saveFile(File file, String fileName, String filesDirectory) throws IOException{
        FileInputStream in = null;
        FileOutputStream out = null;

        File dir = new File (filesDirectory);
        if ( !dir.exists() )
            dir.mkdirs();

        String targetPath =  dir.getPath() + File.separator + fileName;
        File destinationFile = new File ( targetPath);
        try {
            in = new FileInputStream( file );
            out = new FileOutputStream( destinationFile );
            int c;

            while ((c = in.read()) != -1) {
                out.write(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }

    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }

    public String getFilesPath() {
        return filesPath;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.context = servletContext;
    }
}
