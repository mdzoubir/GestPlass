package org.example.controller;

import org.example.DAO.*;
import org.example.Model.*;
import org.example.Repository.ResRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private RoleDaoImpl roleDao;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private ResDaoImpl resDao;

    @Autowired
    private ResRepository resRepository;

    @Autowired
    private TypeResDaompl typeResDaompl;

    @RequestMapping(value = "/index")
    public String usersList(Model model){
        model.addAttribute("admin", HomeController.user);
        List<RoleEntity>  roleList  =  roleDao.getAllRoles();
        model.addAttribute("roleList", roleList);
        return "AdminRole";
    }

    @RequestMapping(value = "Users")
    public String users(Model model){
        model.addAttribute("admin", HomeController.user);
        List<UserEntity> userList = userDao.getAllUsers();
        model.addAttribute("userList", userList);
        return "AdminUsers";
    }

    @RequestMapping(value = "Reservation")
    public String reservation(Model model){
        model.addAttribute("admin", HomeController.user);
        List<TypeResEntity> typeResEntities = typeResDaompl.getAllTypeRes();
        model.addAttribute("typeRes", typeResEntities);
        return "AdminReservation";
    }


    @RequestMapping(value = "HistoriqueReservaton")
    public String HisReservation(Model model){
        model.addAttribute("admin", HomeController.user);
        List<ResEntity> resEntities = resDao.getAllRes();
        model.addAttribute("Reservation", resEntities);
        return "HistReservation";


    }

    @RequestMapping(value = "deleteReservation", method =  RequestMethod.POST)
    public String deleteRes(HttpServletRequest request){
        int id  = Integer.parseInt(request.getParameter("id"));
        resDao.deleteRes(id);
        return "redirect:/HistoriqueReservaton";
    }

    @RequestMapping(value = "SearchDate", method =RequestMethod.POST)
    public String RechDate(HttpServletRequest request, Model model){
        model.addAttribute("admin", HomeController.user);
        String date = request.getParameter("dateRes");
        if (!date.equals("")){
            List<ResEntity> resEntities = resRepository.getResByDate(date);
            model.addAttribute("Reservation", resEntities);
            return "HistReservation";
        }else{
            return "redirect:/HistoriqueReservaton";
        }

    }

    @RequestMapping(value = "AccepterReservation", method =  RequestMethod.POST)
    public String AcceptRes(HttpServletRequest request){
        int id  = Integer.parseInt(request.getParameter("id"));
        ResEntity resEntity = resDao.getResById(id);
        resEntity.setConfirmation(true);
        resEntity.setId(id);
        resDao.updateRes(resEntity);
        return "redirect:/HistoriqueReservaton";
    }


}
