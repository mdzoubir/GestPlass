package org.example.controller;


import org.example.DAO.RoleDaoImpl;
import org.example.Model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RoleController {

    @Autowired
    private RoleDaoImpl roleDAO;


    //save role
    @RequestMapping(value = "RoleForme", method = RequestMethod.GET)
    public String formRole(Model model){
        model.addAttribute("admin", HomeController.user);
        model.addAttribute("role", new RoleEntity());
        return "RoleForm";
    }
    @RequestMapping(value = "SaveRole", method = RequestMethod.POST)
    public String SaveRole(RoleEntity role, Model model){
        model.addAttribute("admin", HomeController.user);
        roleDAO.addRole(role);
        return "redirect:/index";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteRole(HttpServletRequest req,
                             HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        roleDAO.deleteRole(id);
        return "redirect:/index";
    }
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String updateForm(Model model, HttpServletRequest req){
        model.addAttribute("admin", HomeController.user);
        int id = Integer.parseInt(req.getParameter("id"));
        RoleEntity roleEntity = roleDAO.getRoleById(id);
        model.addAttribute("role", roleEntity);
//        roleEntity.showRole();
        return "UpdateRole";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String updateRole(HttpServletRequest req,
                             HttpServletResponse resp, Model model){
        model.addAttribute("admin", HomeController.user);

        int id = Integer.parseInt(req.getParameter("id"));
        String roleName = req.getParameter("roleName");
        RoleEntity roleEntity = new RoleEntity(id, roleName);
        roleDAO.updateRole(roleEntity);
        return "redirect:/index";
    }


}
