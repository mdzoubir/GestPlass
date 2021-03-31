package org.example.Repository;

import org.example.HibernateUtil;
import org.example.Model.ResEntity;
import org.example.Model.StudentEntity;
import org.example.Model.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
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
        return list;
    }
    public List<ResEntity> getResByUser(UserEntity userEntity){
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Query query = session.createQuery("From ResEntity where user.id=:userId order by id desc ");
        query.setParameter("userId", userEntity.getId());
        List<ResEntity> list = query.list();
        return list;
    }


}
