package ua.nure.prykhodko.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ua.nure.prykhodko.entity.User;
import ua.nure.prykhodko.util.HibernateSessionFactoryUtil;

public class UserDAO {

    public User findById(String id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User WHERE id=:uid");
        query.setParameter("uid", id);
        return (User) query.uniqueResult();
    }

    public String getIdByName(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User WHERE name=:name and surname = :surname");
        query.setParameter("name", user.getName());
        query.setParameter("surname", user.getSurname());
        return ((User) query.uniqueResult()).getId();
    }
}
