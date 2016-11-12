package fr.miage.moureypierson.servlet;

import fr.miage.moureypierson.config.HibernateUtil;
import fr.miage.moureypierson.model.Annuaire;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nitix on 12/11/16.
 */
@WebServlet( name="Accueil", urlPatterns = {"/"} )
public class Accueil extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.beginTransaction();
        Annuaire annuaire = new Annuaire();
        session.persist(annuaire);
        session.joinTransaction();
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }
}
