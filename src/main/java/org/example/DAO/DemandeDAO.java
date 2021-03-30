package org.example.DAO;

import org.example.Model.AdminEntity;
import org.example.Model.DemandeEntity;

import java.util.List;

public interface DemandeDAO {
    public  void addDemande(DemandeEntity demande);
    public DemandeEntity getDemandeById(int id);
    public List<DemandeEntity> getAllDemandes();
    public void deleteDemande(int id);
    public DemandeEntity updateDemande(DemandeEntity demande);
}
