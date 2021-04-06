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
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ResDaoImpl resDao;

    @Autowired
    private ResRepository resRepository;

    @Autowired
    private TypeResDaompl typeResDaompl;


    //Historique de reservation dans l'admin
    @RequestMapping(value = "HistoriqueReservaton")
    public String HisReservation(Model model, HttpSession session){
        if (session.getAttribute("email") != null){
            model.addAttribute("admin", HomeController.user);
            List<ResEntity> resEntities = resDao.getAllRes();
            model.addAttribute("Reservation", resEntities);
            List<TypeResEntity> type = typeResDaompl.getAllTypeRes();
            model.addAttribute("typeList", type);
            return "HistReservation";
        }
        else{
            return "redirect:/login";
        }
    }


    //delete reservation in user dashbord
    @RequestMapping(value = "deleteReservation", method =  RequestMethod.POST)
    public String deleteRes(HttpServletRequest request){
        int id  = Integer.parseInt(request.getParameter("id"));
        resDao.deleteRes(id);
        return "redirect:/HistoriqueReservaton";
    }


    //recherch for reservation
    @RequestMapping(value = "SearchDate", method =RequestMethod.POST)
    public String RechDate(HttpServletRequest request, Model model, HttpSession session){
        if (session.getAttribute("email") != null) {
            model.addAttribute("admin", HomeController.user);
            List<TypeResEntity> typeResEntities = typeResDaompl.getAllTypeRes();
            model.addAttribute("typeList", typeResEntities);
            String date = request.getParameter("dateRes");
            String type = request.getParameter("typeRes");
            if (!date.equals("") && type.equals("")) {
                System.out.println(date);
                List<ResEntity> resEntities = resRepository.getResByDate(date);
                model.addAttribute("Reservation", resEntities);
                return "HistReservation";
            } else if (!type.equals("") && date.equals("")) {
                System.out.println(type);
                List<ResEntity> resEntities = resRepository.getResByTypeRes(type);
                model.addAttribute("Reservation", resEntities);
                return "HistReservation";
            } else if (!type.equals("") && !date.equals("")) {
                System.out.println(date + type);
                List<ResEntity> resEntities = resRepository.getResByTypeAndDate(date, type);
                model.addAttribute("Reservation", resEntities);
                return "HistReservation";
            } else {
                return "redirect:/HistoriqueReservaton";
            }
        }else{
            return "redirect:/login";
        }
    }


    //modfier la reservation (Admin : true)
    @RequestMapping(value = "AccepterReservation", method =  RequestMethod.POST)
    public String AcceptRes(HttpServletRequest request){
        int id  = Integer.parseInt(request.getParameter("id"));
        ResEntity resEntity = resDao.getResById(id);
        resEntity.setConfirmation(true);
        resEntity.setId(id);
        resDao.updateRes(resEntity);
        return "redirect:/HistoriqueReservaton";
    }

    //modfier la reservation (Admin : false)
    @RequestMapping(value = "RefuserReservation", method =  RequestMethod.POST)
    public String ResuseRes(HttpServletRequest request){
        int id  = Integer.parseInt(request.getParameter("id"));
        ResEntity resEntity = resDao.getResById(id);
        resEntity.setConfirmation(false);
        resEntity.setId(id);
        resDao.updateRes(resEntity);
        return "redirect:/HistoriqueReservaton";
    }


}
