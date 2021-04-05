package org.example.controller;

import org.example.DAO.DemandeDaoImpl;
import org.example.DAO.RoleDaoImpl;
import org.example.DAO.StudentDaoImpl;
import org.example.DAO.UserDaoImpl;
import org.example.Model.DemandeEntity;
import org.example.Model.StudentEntity;
import org.example.Model.UserEntity;
import org.example.Repository.ResRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private ResRepository resRepository;

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "Users")
    public String users(Model model, HttpSession session){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            List<UserEntity> userList = userDao.getAllUsers();
            model.addAttribute("userList", userList);
            model.addAttribute("msg", "");
            return "AdminUsers";
        }
        else{
            return "redirect:/login";
        }
    }


    @RequestMapping(value = "deleteUser",method = RequestMethod.POST)
    public String deleteUser(HttpServletRequest req, HttpSession session){
        if (session.getAttribute("email") != null) {
            int id = Integer.parseInt(req.getParameter("id"));

            //set value
            DemandeEntity demandeEntity = new DemandeEntity();
            StudentEntity studentEntity = studentDao.getStudentById(id);
            demandeEntity.setFirstName(studentEntity.getFirstName());
            demandeEntity.setLastName(studentEntity.getLastName());
            demandeEntity.setEmail(studentEntity.getEmail());
            demandeEntity.setPassword(studentEntity.getPassword());
            demandeEntity.setPhone(studentEntity.getPhone());

            //convert to demande
            demandeDao.addDemande(demandeEntity);

            //delete all reservation user
            resRepository.deleteResByUser(studentEntity);

            //delete user
            studentDao.deleteStudent(id);
            return "redirect:/Users";
        }
        else{
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "allDemande")
    public String alldemande(Model model, HttpSession session){
        if (session.getAttribute("email") != null){
            model.addAttribute("admin", HomeController.user);
            List<DemandeEntity> listDamande = demandeDao.getAllDemandes();
            model.addAttribute("demandeList", listDamande);
            return "Alldemande";
        }
        else{
            return "redirect:/login";
        }
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


        //send mail
        String email = studentEntity.getEmail();
        String subject = "request to join in ResApp";
        String message = "your request has been accepted";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom("ResApp");
        mailSender.send(simpleMailMessage);
        return "redirect:/allDemande";
    }

}
