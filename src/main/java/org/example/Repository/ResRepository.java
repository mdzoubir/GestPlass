package org.example.Repository;

import org.example.HibernateUtil;
import org.example.Model.ResEntity;
import org.example.Model.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ResRepository {
    Session session;
    public List<ResEntity> getResByDate(String date){
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("From ResEntity where dateRes=:date");
        query.setParameter("date",  date);
        List<ResEntity> list = query.list();
        session.getTransaction().commit();
        return list;
    }
    public List<ResEntity> getResByUser(UserEntity userEntity){
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("From ResEntity where user.id=:userId order by id desc ");
        query.setParameter("userId", userEntity.getId());
        List<ResEntity> list = query.list();
        session.getTransaction().commit();
        return list;
    }

    public List<ResEntity> deleteResByUser(UserEntity userEntity){
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("From ResEntity where user.id=:userId");
        query.setParameter("userId",  userEntity.getId());
        List<ResEntity> list = query.list();
        for (int i=0; i < list.size(); i++) {
            session.delete(list.get(i));
        }
        session.getTransaction().commit();
        return list;
    }


    public List<ResEntity> getResByTypeRes(String type){
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("From ResEntity where typeRes.typeRes=:type");
        query.setParameter("type",  type);
        List<ResEntity> list = query.list();
        session.getTransaction().commit();
        return list;
    }

    public List<ResEntity> getResByTypeAndDate(String date, String type){
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("From ResEntity where dateRes=:date and typeRes.typeRes=:type");
        query.setParameter("date",  date);
        query.setParameter("type",  type);
        List<ResEntity> list = query.list();
        session.getTransaction().commit();

        return list;
    }
}
