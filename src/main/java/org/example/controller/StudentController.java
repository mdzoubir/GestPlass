package org.example.controller;

import org.example.DAO.*;
import org.example.Model.*;
import org.example.Repository.ResRepository;
import org.example.Repository.TypeResRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
public class StudentController {

    @Autowired
    private StudentDaoImpl studentDao;

    @Autowired
    private TypeResDaompl typeResDaompl;

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private RoleDaoImpl roleDao;

    @Autowired
    private TypeResRepository typeResRepository;

    @Autowired
    private ResDaoImpl resDao;

    @Autowired
    private ResRepository resRepository;

    @RequestMapping(value = "editProfil", method = RequestMethod.GET)
    public String getStudent(HttpServletRequest req, Model model){
        model.addAttribute("student", HomeController.user);
        return "editStudent";
    }

    @RequestMapping(value = "editProfil", method = RequestMethod.POST)
    public String editStudent(@ModelAttribute StudentEntity studentEntity){
        studentEntity.setReservationMax(3);
        RoleEntity roleEntity = roleDao.getRoleById(2);
        studentEntity.setRole(roleEntity);
        studentDao.updateStudent(studentEntity);
        HomeController.user= studentEntity;
        return "redirect:/Student";
    }


    @RequestMapping(value = "Reserver", method = RequestMethod.GET)
    public String getResFrom(@ModelAttribute StudentEntity studentEntity, Model model){
        int id = studentEntity.getId();
        model.addAttribute("userId", id);
        List<TypeResEntity> typeResList = typeResDaompl.getAllTypeRes();
        model.addAttribute("typeList", typeResList);
        ResEntity resEntity = new ResEntity();
        model.addAttribute("student", HomeController.user);
        model.addAttribute("resEntity", resEntity);
        return "AddReservation";
    }

    @RequestMapping(value = "Reserver", method = RequestMethod.POST)
    public String setRes( HttpServletRequest request){
        String date = request.getParameter("dateRes");
        String typeRes = request.getParameter("typeRes");

        //get  type Res
        TypeResEntity typeResEntity = typeResRepository.getTypeResbyName(typeRes);

        if(typeResEntity.getNomberClass() > resRepository.getResByDate(date).size()){
            ResEntity resEntity = new ResEntity(HomeController.user, date, true, typeResEntity);
            resDao.addRes(resEntity);
        }else{
            ResEntity resEntity = new ResEntity(HomeController.user, date, false, typeResEntity);
            resDao.addRes(resEntity);
        }

        return "redirect:/Student";
    }

}
