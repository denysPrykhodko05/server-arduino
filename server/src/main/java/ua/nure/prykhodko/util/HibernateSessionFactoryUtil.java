package ua.nure.prykhodko.util;

import java.util.Objects;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.nure.prykhodko.entity.Guests;
import ua.nure.prykhodko.entity.User;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {

    }

    public static SessionFactory getSessionFactory() {
        if (Objects.isNull(sessionFactory)) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Guests.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }
}
