package org.example.controller;

import org.example.DAO.DemandeDaoImpl;
import org.example.Model.DemandeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public
class SignUpController {

    @Autowired
    private DemandeDaoImpl demandeDao;


    DemandeEntity demande;


    @RequestMapping(value = "demande", method = RequestMethod.POST)
    public String demande(@ModelAttribute DemandeEntity demandeEntity, Model model) {
        demandeDao.addDemande(demandeEntity);
        demande = new DemandeEntity();
        model.addAttribute("demande", demande);
        model.addAttribute("msg", "Your registration is dene, You need to wait for your request to be accepted");
        return "SignUp";
    }
}
