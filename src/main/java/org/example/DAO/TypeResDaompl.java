package org.example.DAO;

import org.example.HibernateUtil;
import org.example.Model.TypeResEntity;
import org.example.Model.UserEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeResDaompl implements TypeResDAO{

    Session session;
    @Override
    public void addTypeRes(TypeResEntity typeResEntity) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(typeResEntity);
        session.getTransaction().commit();
        System.out.println("add typeReservation");
    }

    @Override
    public TypeResEntity getTypeResById(int id) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        TypeResEntity typeResEntity = session.find(TypeResEntity.class, id);
        session.getTransaction().commit();
        return typeResEntity;
    }

    @Override
    public List<TypeResEntity> getAllTypeRes() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        List<TypeResEntity> typeResEntity = session.createQuery("From TypeResEntity ").list();
        session.getTransaction().commit();
        return typeResEntity;
    }

    @Override
    public void deleteTypeRes(int id) {
        TypeResEntity typeResEntity;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        typeResEntity = session.find(TypeResEntity.class, id);
        if (typeResEntity != null){
            session.delete(typeResEntity);
            session.flush();
            System.out.println("delete typeRes");
        }else{
            System.out.println("typeRes does not exist");
        }
        session.getTransaction().commit();
    }

    @Override
    public TypeResEntity updateTypeRes(TypeResEntity typeResEntity) {
        TypeResEntity typeRes;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        typeRes = session.find(TypeResEntity.class, typeResEntity.getId());
        if (typeRes != null){
            typeRes.setTypeRes(typeResEntity.getTypeRes());
            typeRes.setNomberClass(typeResEntity.getNomberClass());
            System.out.println("User typeRes");
        }else{
            System.out.println("typeRes does not exist");
        }
        session.getTransaction().commit();
        return typeRes;
    }
}
