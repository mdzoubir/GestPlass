package org.example;
import org.example.DAO.AdminDaoImpl;
import org.example.DAO.RoleDaoImpl;
import org.example.DAO.StudentDaoImpl;
import org.example.DAO.UserDaoImpl;
import org.example.Model.*;
import org.example.Repository.ResRepository;
import org.example.Repository.UserRepository;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.util.Date;
import java.util.List;

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
//        RoleEntity roleEntity = roleDao.getRoleById(1);
////
//        AdminEntity studentEntity = new AdminEntity("kamal", "kamal", "kamal@gmail.com", "123", 2121121212, roleEntity);
//        AdminDaoImpl studentDao = new AdminDaoImpl();
//        studentDao.addAdmin(studentEntity);



    }
}