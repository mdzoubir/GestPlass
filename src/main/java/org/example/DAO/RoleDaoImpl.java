package org.example.DAO;

import org.example.HibernateUtil;
import org.example.Model.RoleEntity;
import org.hibernate.Session;
import java.util.List;

public class RoleDaoImpl implements RoleDAO{
    Session session;
    @Override
    public void addRole(RoleEntity role) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(role);
        session.getTransaction().commit();
        System.out.println("add role");
    }

    @Override
    public RoleEntity getRoleById(int id) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        RoleEntity role = session.find(RoleEntity.class, id);
        session.getTransaction().commit();
        return role;
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        List<RoleEntity> listRole = session.createQuery("From RoleEntity ").list();
        session.getTransaction().commit();
        return listRole;
    }

    @Override
    public void deleteRole(int id) {
        RoleEntity role;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        role = session.find(RoleEntity.class, id);
        if (role != null){
            session.delete(role);
            session.flush();
            System.out.println("delete role");
        }else{
            System.out.println("role does not exist");
        }
        session.getTransaction().commit();
    }

    @Override
    public RoleEntity updateRole(RoleEntity role) {
        RoleEntity roleEntity;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        roleEntity = session.find(RoleEntity.class, role.getId());
        if (roleEntity != null){
            roleEntity.setRoleName(role.getRoleName());
            System.out.println("Role update");
        }else{
            System.out.println("Role does not exist");
        }
        session.getTransaction().commit();
        return roleEntity;
    }
}
