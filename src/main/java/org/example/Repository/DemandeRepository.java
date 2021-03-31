package org.example.Repository;

import org.example.HibernateUtil;
import org.example.Model.DemandeEntity;
import org.example.Model.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

@Component
public class DemandeRepository {
    Session session;
    public DemandeEntity getUserByEmail(String email){
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query =  session.createQuery("from DemandeEntity where email=: email");
        query.setParameter("email", email);
        try {
            DemandeEntity demandeEntity = (DemandeEntity) query.getSingleResult();
            return demandeEntity;
        }
        catch (Exception e){
            return null;
        }
    }
}
