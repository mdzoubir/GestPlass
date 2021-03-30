package org.example.DAO;

import org.example.HibernateUtil;
import org.example.Model.AdminEntity;
import org.example.Model.DemandeEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemandeDaoImpl implements DemandeDAO{
    Session session;
    @Override
    public void addDemande(DemandeEntity demande) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(demande);
        session.getTransaction().commit();
        System.out.println("add demande");
    }

    @Override
    public DemandeEntity getDemandeById(int id) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        DemandeEntity demande = session.find(DemandeEntity.class, id);
        session.getTransaction().commit();
        return demande;
    }

    @Override
    public List<DemandeEntity> getAllDemandes() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        List<DemandeEntity> listDemande = session.createQuery("From DemandeEntity ").list();
        session.getTransaction().commit();
        return listDemande;
    }

    @Override
    public void deleteDemande(int id) {
        DemandeEntity demande;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        demande = session.find(DemandeEntity.class, id);
        if (demande != null){
            session.delete(demande);
            session.flush();
            System.out.println("delete demande");
        }else{
            System.out.println("demande does not exist");
        }
        session.getTransaction().commit();
    }

    @Override
    public DemandeEntity updateDemande(DemandeEntity demande) {
        DemandeEntity demandeEntity;
        session = HibernateUtil.getSession();
        session.beginTransaction();
        demandeEntity = session.find(DemandeEntity.class, demande.getId());
        if (demandeEntity != null){
            demandeEntity.setFirstName(demande.getFirstName());
            demandeEntity.setLastName(demande.getLastName());
            demandeEntity.setEmail(demande.getEmail());
            demandeEntity.setPassword(demande.getPassword());
            demandeEntity.setFirstName(demande.getFirstName());
            demandeEntity.setPhone(demande.getPhone());
            System.out.println("demande update");
        }else{
            System.out.println("demande does not exist");
        }
        session.getTransaction().commit();
        return demandeEntity;
    }
}
