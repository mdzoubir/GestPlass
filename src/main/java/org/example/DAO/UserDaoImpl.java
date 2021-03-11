package org.example.DAO;

import org.example.HibernateUtil;
import org.example.Model.UserEntity;
import org.hibernate.Session;

import java.util.List;

public class UserDaoImpl implements UserDAO{
    Session session;
    @Override
    public void addUser(UserEntity user) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        System.out.println("add user");
    }

    @Override
    public UserEntity getUserById(int id) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        UserEntity user = session.find(UserEntity.class, id);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        List<UserEntity> userList = session.createQuery("From UserEntity ").list();
        session.getTransaction().commit();
        return userList;
    }

    @Override
    public void deleteUser(int id) {
        UserEntity user;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        user = session.find(UserEntity.class, id);
        if (user != null){
            session.delete(user);
            session.flush();
            System.out.println("delete user");
        }else{
            System.out.println("user does not exist");
        }
        session.getTransaction().commit();
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        UserEntity userEntity;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        userEntity = session.find(UserEntity.class, user.getId());
        if (userEntity != null){
            userEntity.setFirstName(user.getFirstName());
            userEntity.setLastName(user.getLastName());
            userEntity.setEmail(user.getEmail());
            userEntity.setPassword(user.getPassword());
            userEntity.setPhone(user.getPhone());
            userEntity.setRole(user.getRole());
            System.out.println("User update");
        }else{
            System.out.println("User does not exist");
        }
        session.getTransaction().commit();
        return userEntity;
    }
}
