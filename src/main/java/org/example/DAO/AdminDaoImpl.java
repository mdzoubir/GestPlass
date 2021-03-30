package org.example.DAO;


import org.example.HibernateUtil;
import org.example.Model.AdminEntity;
import org.example.Model.RoleEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminDaoImpl implements AdminDAO{
    Session session;
    @Override
    public void addAdmin(AdminEntity admin) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(admin);
        session.getTransaction().commit();
        System.out.println("add admin");
    }

    @Override
    public AdminEntity getAdminById(int id) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        AdminEntity role = session.find(AdminEntity.class, id);
        session.getTransaction().commit();
        return role;
    }

    @Override
    public List<AdminEntity> getAllAdmins() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        List<AdminEntity> listAdmin = session.createQuery("From AdminEntity ").list();
        session.getTransaction().commit();
        return listAdmin;
    }

    @Override
    public void deleteAdmin(int id) {
        AdminEntity admin;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        admin = session.find(AdminEntity.class, id);
        if (admin != null){
            session.delete(admin);
            session.flush();
            System.out.println("delete admin");
        }else{
            System.out.println("admin does not exist");
        }
        session.getTransaction().commit();
    }

    @Override
    public AdminEntity updateAdmin(AdminEntity admin) {
        AdminEntity adminEntity;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        adminEntity = session.find(AdminEntity.class, admin.getId());
        if (adminEntity != null){
            adminEntity.setFirstName(admin.getFirstName());
            adminEntity.setLastName(admin.getLastName());
            adminEntity.setEmail(admin.getEmail());
            adminEntity.setPassword(admin.getPassword());
            adminEntity.setFirstName(admin.getFirstName());
            adminEntity.setPhone(admin.getPhone());
            adminEntity.setRole(admin.getRole());
            System.out.println("admin update");
        }else{
            System.out.println("admin does not exist");
        }
        session.getTransaction().commit();
        return adminEntity;
    }
}
