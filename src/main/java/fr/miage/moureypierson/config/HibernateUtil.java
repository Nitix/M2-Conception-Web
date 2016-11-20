package fr.miage.moureypierson.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by nitix on 12/11/16.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        // A SessionFactory is set up once for an application!
        StandardServiceRegistry registry = null;
        try {
            Configuration configuration = new Configuration().configure();
            registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(registry);
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            if(registry != null)
                StandardServiceRegistryBuilder.destroy( registry );
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
