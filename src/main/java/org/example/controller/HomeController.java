package org.example.controller;

import java.io.IOException;
import java.util.List;

import org.example.DAO.DemandeDaoImpl;
import org.example.DAO.RoleDaoImpl;
import org.example.Model.DemandeEntity;
import org.example.Model.RoleEntity;
import org.example.Model.UserEntity;
import org.example.Repository.DemandeRepository;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller

public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DemandeRepository demandeRepository;

	public static UserEntity user;

	private DemandeEntity demandeEntity;

	@RequestMapping(value="/login")
	public String test(Model model) throws IOException{
		user = new UserEntity();
		model.addAttribute("user", user);
		model.addAttribute("msg", "");
		return "Login";
	}

	@RequestMapping(value="/signUp")
	public String signUp(Model model) throws IOException{
		demandeEntity = new DemandeEntity();
		model.addAttribute("demande", demandeEntity);
		model.addAttribute("msg", "");
		return "SignUp";
	}

	@PostMapping(value = "/Login")
	public String login(@ModelAttribute UserEntity userEntity, Model model){
		user =userRepository.getUserByEmail(userEntity.getEmail());
		demandeEntity = demandeRepository.getUserByEmail(userEntity.getEmail());
		if (demandeEntity != null){
			model.addAttribute("msg", "Your request is being processed");
			user = new UserEntity();
			model.addAttribute("user", user);
			return "Login";
		}else{
			if (user != null && user.getPassword().equals(userEntity.getPassword())) {
				if (user.getRole().getRoleName().equals("Admin")) {
					return "redirect:/Profil";
				} else if (user.getRole().getRoleName().equals("Apprenant")) {
					return "redirect:/Student";
				}
			}else{
				user = new UserEntity();
				model.addAttribute("user", user);
				model.addAttribute("msg", "Email or Password incorrect");
				return "Login";
			}
		}
		return "redirect:/login";
	}





}
