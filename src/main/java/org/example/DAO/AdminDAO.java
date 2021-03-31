package org.example.DAO;

import org.example.Model.AdminEntity;
import org.example.Model.DemandeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface AdminDAO {
    public  void addAdmin(AdminEntity admin);
    public AdminEntity getAdminById(int id);
    public List<AdminEntity> getAllAdmins();
    public void deleteAdmin(int id);
    public AdminEntity updateAdmin(AdminEntity admin);

}
