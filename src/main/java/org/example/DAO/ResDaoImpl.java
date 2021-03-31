package org.example.DAO;

import org.example.HibernateUtil;
import org.example.Model.ResEntity;
import org.example.Model.RoleEntity;
import org.example.Model.UserEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResDaoImpl implements ResDAO{
    Session session;
    @Override
    public void addRes(ResEntity ResEntity) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(ResEntity);
        session.getTransaction().commit();
        System.out.println("add Res");
    }

    @Override
    public ResEntity getResById(int id) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        ResEntity resEntity = session.find(ResEntity.class, id);
        session.getTransaction().commit();
        return resEntity;
    }

    @Override
    public List<ResEntity> getAllRes() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        List<ResEntity> listRes = session.createQuery("From ResEntity order by id desc ").list();
        session.getTransaction().commit();
        return listRes;
    }

    @Override
    public void deleteRes(int id) {
        ResEntity resEntity;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        resEntity = session.find(ResEntity.class, id);
        if (resEntity != null){
            session.delete(resEntity);
            session.flush();
            System.out.println("delete res");
        }else{
            System.out.println("res does not exist");
        }
        session.getTransaction().commit();
    }

    @Override
    public ResEntity updateRes(ResEntity reser) {
        ResEntity resEntity;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        resEntity = session.find(ResEntity.class, reser.getId());
        if (resEntity != null){
            resEntity.setConfirmation(reser.isConfirmation());
            resEntity.setDateRes(reser.getDateRes());
            resEntity.setTypeRes(reser.getTypeRes());
            resEntity.setUser(reser.getUser());
            System.out.println("Res update");
        }else{
            System.out.println("Res does not exist");
        }
        session.getTransaction().commit();
        return resEntity;
    }
}
