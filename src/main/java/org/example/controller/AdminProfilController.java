package org.example.controller;


import org.example.DAO.AdminDaoImpl;
import org.example.DAO.RoleDaoImpl;
import org.example.Model.AdminEntity;
import org.example.Model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminProfilController {
    @Autowired
    private RoleDaoImpl roleDao;

    @Autowired
    private AdminDaoImpl adminDao;

    @RequestMapping(value = "Profil")
    public String adminProfil(Model model){
        model.addAttribute("admin", HomeController.user);
        return "AdminProfil";
    }
    @RequestMapping(value = "EditAdminProfil")
    public String editProfil(Model model){
        model.addAttribute("admin", HomeController.user);
        return "editAdminProfil";
    }

    @RequestMapping(value = "editAdminProfil", method = RequestMethod.POST)
    public String editP(HttpServletRequest req, Model model){
        int id = Integer.parseInt(req.getParameter("id"));
        String fn = req.getParameter("firstName");
        String ln = req.getParameter("lastName");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        int phone = Integer.parseInt(req.getParameter("phone"));

        RoleEntity role = roleDao.getRoleById(1);
        AdminEntity adminEntity = new AdminEntity(id,fn,ln,email,pass,phone, role);
        adminEntity.showUser();
        adminDao.updateAdmin(adminEntity);
        HomeController.user = adminEntity;
        model.addAttribute("admin", adminEntity);
        return "AdminProfil";
    }
}
