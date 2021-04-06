package org.example.controller;


import org.example.DAO.AdminDaoImpl;
import org.example.DAO.RoleDaoImpl;
import org.example.Model.AdminEntity;
import org.example.Model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminProfilController {

    @Autowired
    private RoleDaoImpl roleDao;

    @Autowired
    private AdminDaoImpl adminDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //return Admin profil
    @RequestMapping(value = "Profil")
    public String adminProfil(Model model, HttpSession session){
        if (session.getAttribute("email") != null){
            model.addAttribute("admin", HomeController.user);
            model.addAttribute("msg", "");
            return "AdminProfil";
        }else{
            return "redirect:/login";
        }

    }


    //return Admin form
    @RequestMapping(value = "EditAdminProfil")
    public String editProfil(Model model, HttpSession session){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            return "editAdminProfil";
        }else{
            return "redirect:/login";
        }
    }


    //update admin
    @RequestMapping(value = "editAdminProfil", method = RequestMethod.POST)
    public String editP(HttpServletRequest req, Model model){

        int id = Integer.parseInt(req.getParameter("id"));
        String fn = req.getParameter("firstName");
        String ln = req.getParameter("lastName");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String phone = req.getParameter("phone");

        String passEnCode = passwordEncoder.encode(pass);

        RoleEntity role = roleDao.getRoleById(1);
        AdminEntity adminEntity = new AdminEntity(id,fn,ln,email,passEnCode,phone, role);
        adminEntity.showUser();
        adminDao.updateAdmin(adminEntity);
        HomeController.user = adminEntity;
        model.addAttribute("admin", adminEntity);
        model.addAttribute("msg", "The update has been completed successfully");

        return "AdminProfil";
    }
}
