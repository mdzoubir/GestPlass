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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;


@Controller
public class StudentController {

    @Autowired
    private StudentDaoImpl studentDao;

    @Autowired
    private TypeResDaompl typeResDaompl;

    @Autowired
    private RoleDaoImpl roleDao;

    @Autowired
    private TypeResRepository typeResRepository;

    @Autowired
    private ResDaoImpl resDao;

    @Autowired
    private ResRepository resRepository;

    @RequestMapping(value = "Student", method = RequestMethod.GET)
    public String Apprenant(Model model){
        model.addAttribute("user", HomeController.user);
        List<ResEntity> list = resRepository.getResByUser(HomeController.user);
        model.addAttribute("list", list);
        return "/Student";
    }

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
        LocalDate currentDate = LocalDate.now();
        model.addAttribute("date", currentDate);
        LocalDate result = currentDate.plus(1, ChronoUnit.WEEKS);
        model.addAttribute("maxDate", result);
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


    @RequestMapping(value = "UpdateRes", method = RequestMethod.GET)
    public  String getForm(Model model, HttpServletRequest request){
        int id  = Integer.parseInt(request.getParameter("id"));
        ResEntity resEntity = resDao.getResById(id);
        model.addAttribute("reservation", resEntity);

        //student
        model.addAttribute("student", HomeController.user);

        //new Date
        LocalDate currentDate = LocalDate.now();
        model.addAttribute("date", currentDate);
        LocalDate result = currentDate.plus(1, ChronoUnit.WEEKS);
        model.addAttribute("maxDate", result);

        //list type Res
        List<TypeResEntity> typeResList = typeResDaompl.getAllTypeRes();
        model.addAttribute("typeList", typeResList);

        return "UpdateRes";
    }


    @RequestMapping(value = "UpdateRes", method = RequestMethod.POST)
    public  String Editres(HttpServletRequest request){
        String date = request.getParameter("dateRes");
        String type = request.getParameter("typeRes");
        int id = Integer.parseInt(request.getParameter("id"));


        TypeResEntity typeResEntity = typeResRepository.getTypeResbyName(type);
        if(typeResEntity.getNomberClass() > resRepository.getResByDate(date).size()){
            ResEntity resEntity = new ResEntity(id, HomeController.user, date, true, typeResEntity);
            resDao.updateRes(resEntity);
        }else{
            ResEntity resEntity = new ResEntity(id, HomeController.user, date, false, typeResEntity);
            resDao.updateRes(resEntity);
        }
        return "redirect:/Student";
    }



    @RequestMapping(value = "DeleteRes", method = RequestMethod.POST)
    public String DeleteRes(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        resDao.deleteRes(id);
        return "redirect:/Student";
    }

}
