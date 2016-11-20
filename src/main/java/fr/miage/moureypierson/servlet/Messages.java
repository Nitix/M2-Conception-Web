package fr.miage.moureypierson.servlet;

import fr.miage.moureypierson.config.HibernateUtil;
import fr.miage.moureypierson.model.Abonne;
import fr.miage.moureypierson.model.Annuaire;
import fr.miage.moureypierson.model.Message;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nitix on 20/11/16.
 */
@WebServlet(name = "messages", urlPatterns = {"/messages.jsp", "/afficher-message.jsp"})
public class Messages extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Abonne abonne = Abonne.findById((Long) request.getSession().getAttribute("login"));
        if(abonne == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        String id = request.getParameter("id");
        if(id != null) {
            Message m = Message.findById(Long.parseLong(id));
            if(m == null) {
                getServletContext().getRequestDispatcher("/WEB-INF/messages.jsp").forward(request, response);
                return;
            }
            getServletContext().getRequestDispatcher("/WEB-INF/afficher-message.jsp").forward(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/messages.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Abonne abonne = Abonne.findById((Long) request.getSession().getAttribute("login"));
        if(abonne == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        String destinataire = request.getParameter("destinataire");
        String message_content = request.getParameter("message_content");
        String objet = request.getParameter("objet");
        Annuaire annuaire = Annuaire.findById(Long.parseLong(destinataire));
        Message message = new Message();
        message.setExpediteur(abonne);
        message.setDestinataires(annuaire.getAbonnes());
        message.setCorps(message_content);
        message.setObjet(objet);
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
            getServletContext().getRequestDispatcher("/WEB-INF/messages.jsp").forward(request, response);
            return;
        }finally {
            if (session != null) {
                session.close();
            }
        }
        getServletContext().getRequestDispatcher("/WEB-INF/afficher-message.jsp?id=" + message.getId()).forward(request, response);
    }
}
