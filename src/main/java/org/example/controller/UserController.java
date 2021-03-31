package org.example.controller;

import org.example.DAO.DemandeDaoImpl;
import org.example.DAO.RoleDaoImpl;
import org.example.DAO.StudentDaoImpl;
import org.example.DAO.UserDaoImpl;
import org.example.Model.DemandeEntity;
import org.example.Model.StudentEntity;
import org.example.Model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private RoleDaoImpl roleDao;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private DemandeDaoImpl demandeDao;

    @Autowired
    private StudentDaoImpl studentDao;

    @RequestMapping(value = "Users")
    public String users(Model model){
        model.addAttribute("admin", HomeController.user);
        List<UserEntity> userList = userDao.getAllUsers();
        model.addAttribute("userList", userList);
        model.addAttribute("msg", "");
        return "AdminUsers";
    }


    @RequestMapping(value = "deleteUser",method = RequestMethod.POST)
    public String deleteUser(HttpServletRequest req, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        userDao.deleteUser(id);
        model.addAttribute("admin", HomeController.user);
        List<UserEntity> userList = userDao.getAllUsers();
        model.addAttribute("userList", userList);
        model.addAttribute("msg", "The Delete has been completed successfully");
        return "AdminUsers";
    }

    @RequestMapping(value = "allDemande")
    public String alldemande(Model model){
        model.addAttribute("admin", HomeController.user);
        List<DemandeEntity> listDamande = demandeDao.getAllDemandes();
        model.addAttribute("demandeList", listDamande);
        return "Alldemande";
    }

    @RequestMapping(value = "deleteDemande",method = RequestMethod.POST)
    public String deleteDemande(HttpServletRequest req){
        int id = Integer.parseInt(req.getParameter("id"));
        demandeDao.deleteDemande(id);
        return "redirect:/allDemande";
    }

    @RequestMapping(value = "AcceptDemande",method = RequestMethod.POST)
    public String acceptDemande(HttpServletRequest req, Model model){
        model.addAttribute("admin", HomeController.user);
        int id = Integer.parseInt(req.getParameter("id"));
        StudentEntity studentEntity = new StudentEntity();
        DemandeEntity demandeEntity = demandeDao.getDemandeById(id);
        studentEntity.setFirstName(demandeEntity.getFirstName());
        studentEntity.setLastName(demandeEntity.getLastName());
        studentEntity.setEmail(demandeEntity.getEmail());
        studentEntity.setPassword(demandeEntity.getPassword());
        studentEntity.setPhone(demandeEntity.getPhone());
        studentEntity.setRole(roleDao.getRoleById(2));
        studentEntity.setReservationMax(3);
        studentDao.addStudent(studentEntity);
        demandeDao.deleteDemande(id);
        return "redirect:/allDemande";
    }

}
