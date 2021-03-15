package org.example.controller;

import org.example.DAO.RoleDaoImpl;
import org.example.DAO.TypeResDaompl;
import org.example.DAO.UserDaoImpl;
import org.example.Model.RoleEntity;
import org.example.Model.TypeResEntity;
import org.example.Model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private RoleDaoImpl roleDao;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private TypeResDaompl typeResDaompl;

    @RequestMapping(value = "/index")
    public String usersList(Model model){
        List<RoleEntity>  roleList  =  roleDao.getAllRoles();
        model.addAttribute("roleList", roleList);
        return "AdminRole";
    }

    @RequestMapping(value = "Users")
    public String users(Model model){
        List<UserEntity> userList = userDao.getAllUsers();
        model.addAttribute("userList", userList);
        return "AdminUsers";
    }

    @RequestMapping(value = "Reservation")
    public String reservation(Model model){
        List<TypeResEntity> typeResEntities = typeResDaompl.getAllTypeRes();
        model.addAttribute("typeRes", typeResEntities);
        return "AdminReservation";
    }
}
