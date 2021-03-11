package org.example.Repository;

import org.example.HibernateUtil;
import org.example.Model.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserRepository {
    Session session;
    public UserEntity getUserByEmail(String email){
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query =  session.createQuery("from UserEntity where email=: email");
        query.setParameter("email", email);
        try {
            UserEntity userEntity = (UserEntity) query.getSingleResult();
            return userEntity;
        }
        catch (Exception e){
            return null;
        }
    }
}
