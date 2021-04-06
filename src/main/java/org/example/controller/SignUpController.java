package org.example.controller;

import org.example.DAO.AdminDaoImpl;
import org.example.DAO.DemandeDaoImpl;
import org.example.DAO.RoleDaoImpl;
import org.example.Model.AdminEntity;
import org.example.Model.DemandeEntity;
import org.example.Model.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public
class SignUpController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DemandeDaoImpl demandeDao;


    //sign up
    @RequestMapping(value = "demande", method = RequestMethod.POST)
    public String demande(@ModelAttribute DemandeEntity demandeEntity, Model model) {
        DemandeEntity demande;
        demandeEntity.setPassword(passwordEncoder.encode(demandeEntity.getPassword()));
        demandeDao.addDemande(demandeEntity);
        demande = new DemandeEntity();
        model.addAttribute("demande", demande);
        model.addAttribute("msg", "Your registration is dene, You need to wait for your request to be accepted");
        return "SignUp";
    }

}
