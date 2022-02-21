package se.yrgo.jumpyduke.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseService {

    public static Session getCurrentSessionFromConfig() {
        // SessionFactory in Hibernate 5 example
        Configuration config = new Configuration()
                .addAnnotatedClass(se.yrgo.jumpyduke.player.Player.class);
        config.configure();
        // local SessionFactory bean created
        SessionFactory sessionFactory = config.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }
}
