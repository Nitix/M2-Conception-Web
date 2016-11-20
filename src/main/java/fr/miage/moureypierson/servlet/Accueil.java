package fr.miage.moureypierson.servlet;

import fr.miage.moureypierson.config.HibernateUtil;
import fr.miage.moureypierson.model.Abonne;
import fr.miage.moureypierson.model.Annuaire;
import fr.miage.moureypierson.model.Entreprise;
import fr.miage.moureypierson.model.Particulier;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.security.MessageDigest;

/**
 * Created by nitix on 12/11/16.
 */
@WebServlet(name = "accueil", urlPatterns = {"/index.jsp"})
public class Accueil extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if(type.equals("inscription")){
            this.handleInsription(request, response);
        }else{
            this.handleConnection(request, response);
        }
    }

    private void handleConnection(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Abonne abonne = Abonne.findByLogin(login);
        System.out.println(abonne);
        if(abonne != null && BCrypt.checkpw(password, abonne.getMdp())) {
            request.getSession().setAttribute("login", abonne);
            getServletContext().getRequestDispatcher("/WEB-INF/messages.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        }
    }

    private void handleInsription(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        boolean entreprise = !(request.getParameter("entreprise") == null);
        Abonne abonne;
        if(entreprise) {
            String raison = request.getParameter("raison");
            abonne = new Entreprise().setRaisonSociale(raison);
        } else {
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            abonne = new Particulier()
                    .setNom(nom)
                    .setPrenom(prenom);
        }
        abonne.setLogin(login);
        abonne.setMdp(BCrypt.hashpw(password, BCrypt.gensalt()));
        Annuaire annuaire = Annuaire.findById(1L);
        abonne.addAbonnement(annuaire);
        Session session = null;
        Transaction tx = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(abonne);
            tx.commit();
        }catch (Exception e) {
            if(tx != null) tx.rollback();
            e.printStackTrace();
            getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
            return;
        }finally {
            if (session != null) {
                session.close();
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/messages.jsp").forward(request, response);
    }
}