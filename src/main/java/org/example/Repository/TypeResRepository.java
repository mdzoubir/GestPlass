package org.example.Repository;

import org.example.HibernateUtil;
import org.example.Model.ResEntity;
import org.example.Model.TypeResEntity;
import org.example.Model.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;


@Component
public class TypeResRepository {
    Session session;
    public TypeResEntity getTypeResbyName(String name){
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query =  session.createQuery("from TypeResEntity where typeRes=: name");
        query.setParameter("name", name);
        try {
            TypeResEntity typeResEntity = (TypeResEntity) query.getSingleResult();
            return typeResEntity;
        }
        catch (Exception e){
            return null;
        }
    }
}
