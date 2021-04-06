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
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleDaoImpl roleDAO;


    //return role page
    @RequestMapping(value = "/index")
    public String usersList(Model model, HttpSession session){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            List<RoleEntity> roleList = roleDAO.getAllRoles();
            model.addAttribute("roleList", roleList);
            return "AdminRole";
        }
        else{
            return "redirect:/login";
        }
    }


    //form save role
    @RequestMapping(value = "RoleForme", method = RequestMethod.GET)
    public String formRole(Model model, HttpSession session){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            model.addAttribute("role", new RoleEntity());
            return "RoleForm";
        }
        else{
            return "redirect:/login";
        }
    }

    //save role
    @RequestMapping(value = "SaveRole", method = RequestMethod.POST)
    public String SaveRole(RoleEntity role, Model model, HttpSession session){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            roleDAO.addRole(role);
            return "redirect:/index";
        }
        else{
            return "redirect:/login";
        }
    }


    //delete role
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteRole(HttpServletRequest req, HttpSession session ){
        if (session.getAttribute("email") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            roleDAO.deleteRole(id);
            return "redirect:/index";
        }
        else{
            return "redirect:/login";
        }
    }

    //form update
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String updateForm(Model model, HttpServletRequest req, HttpSession session){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            int id = Integer.parseInt(req.getParameter("id"));
            RoleEntity roleEntity = roleDAO.getRoleById(id);
            model.addAttribute("role", roleEntity);
            return "UpdateRole";
        }
        else{
            return "redirect:/login";
        }
    }


    //update
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String updateRole(HttpServletRequest req,HttpSession session, Model model){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            int id = Integer.parseInt(req.getParameter("id"));
            String roleName = req.getParameter("roleName");
            RoleEntity roleEntity = new RoleEntity(id, roleName);
            roleDAO.updateRole(roleEntity);
            return "redirect:/index";
        }
        else{
            return "redirect:/login";
        }
    }


}
