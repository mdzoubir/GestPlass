package org.example;
import org.example.DAO.RoleDaoImpl;
import org.example.DAO.UserDaoImpl;
import org.example.Model.RoleEntity;
import org.example.Model.UserEntity;
import org.example.Repository.UserRepository;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;

public class Main {


    public static void main(final String[] args) throws Exception {
//        final Session session = HibernateUtil.getSession();
//        try {
//            System.out.println("querying all the managed entities...");
//            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
//            for (EntityType<?> entityType : metamodel.getEntities()) {
//                final String entityName = entityType.getName();
//                final Query query = session.createQuery("from " + entityName);
//                System.out.println("executing: " + query.getQueryString());
//                for (Object o : query.list()) {
//                    System.out.println("  " + o);
//                }
//            }
//        } finally {
//            session.close();
//        }
//        RoleDaoImpl roleDao
//                 = new RoleDaoImpl();
//        RoleEntity roleEntity = roleDao.getRoleById(2);
//
//        UserEntity userEntity = new UserEntity("kamal", "kamal", "kamal@gmail.com", "123", 1324234850, roleEntity);
//        UserDaoImpl userDao = new UserDaoImpl();
//        userDao.addUser(userEntity);

    }
}