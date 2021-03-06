package org.example.controller;


import org.example.DAO.TypeResDaompl;
import org.example.Model.RoleEntity;
import org.example.Model.TypeResEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class TypeResController {

    @Autowired
    private TypeResDaompl typeResDaompl;

    @RequestMapping(value = "Reservation")
    public String reservation(Model model, HttpSession session){
        if (session.getAttribute("email") != null){
            model.addAttribute("admin", HomeController.user);
            List<TypeResEntity> typeResEntities = typeResDaompl.getAllTypeRes();
            model.addAttribute("typeRes", typeResEntities);
            model.addAttribute("msg", "");
            return "AdminReservation";
        }else{
            return "redirect:/login";
        }

    }

    @RequestMapping(value = "TypeResForm", method = RequestMethod.GET)
    public String getTypeRes(Model model, HttpSession session){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            model.addAttribute("typeRes", new TypeResEntity());
            return "TypeResForm";
        }
        else{
            return "redirect:/login";
        }
    }
    @RequestMapping(value = "SaveTypeRes", method = RequestMethod.POST)
    public String SaveTyeRes(@ModelAttribute TypeResEntity typeResEntity, Model model, HttpSession session){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            typeResDaompl.addTypeRes(typeResEntity);
            List<TypeResEntity> typeResEntities = typeResDaompl.getAllTypeRes();
            model.addAttribute("typeRes", typeResEntities);
            model.addAttribute("msg", "The Registration has been completed successfully");
            return "AdminReservation";
        }
        else{
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "deleteTypeRes", method = RequestMethod.POST)
    public String deleteTypeRes(HttpServletRequest req,Model model, HttpSession session){
        if (session.getAttribute("email") != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            typeResDaompl.deleteTypeRes(id);
            List<TypeResEntity> typeResEntities = typeResDaompl.getAllTypeRes();
            model.addAttribute("admin", HomeController.user);
            model.addAttribute("typeRes", typeResEntities);
            model.addAttribute("msg", "The Delete has been completed successfully");
            return "AdminReservation";
        }
        else{
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "updateTypeRes", method = RequestMethod.GET)
    public String updateTypeRes(Model model, HttpServletRequest req, HttpSession session){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            int id = Integer.parseInt(req.getParameter("id"));
            TypeResEntity typeResEntity = typeResDaompl.getTypeResById(id);
            model.addAttribute("typeRes", typeResEntity);
            return "UpdateTypeRes";
        }
        else{
            return "redirect:/login";
        }
    }


    @RequestMapping(value = "editTypeRes", method = RequestMethod.POST)
    public String updateRole(HttpServletRequest req, Model model, HttpSession session){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            int id = Integer.parseInt(req.getParameter("id"));
            String typeRes = req.getParameter("typeRes");
            int nomberClass = Integer.parseInt(req.getParameter("nomberClass"));
            TypeResEntity typeResEntity = new TypeResEntity(id, typeRes, nomberClass);
            typeResDaompl.updateTypeRes(typeResEntity);
            List<TypeResEntity> typeResEntities = typeResDaompl.getAllTypeRes();
            model.addAttribute("typeRes", typeResEntities);
            model.addAttribute("msg", "The update has been completed successfully");
            return "AdminReservation";
        }
        else{
            return "redirect:/login";
        }
    }
}
