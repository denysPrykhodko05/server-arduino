package ua.nure.serverarduino.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.nure.serverarduino.dao.UserDAO;
import ua.nure.serverarduino.models.User;
import ua.nure.serverarduino.util.HibernateSessionFactoryUtil;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findById (String id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User WHERE id=:uid");
        query.setParameter("uid", id);
        User user = (User) query.uniqueResult();
        return user;
    }
}
