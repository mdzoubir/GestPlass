package org.example.controller;

import java.io.IOException;
import java.util.List;

import org.example.DAO.RoleDaoImpl;
import org.example.Model.DemandeEntity;
import org.example.Model.RoleEntity;
import org.example.Model.UserEntity;
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
	private RoleDaoImpl roleDao;


	private UserRepository userRepository;

	public static UserEntity user;

	DemandeEntity demandeEntity;

	@RequestMapping(value="/login")
	public String test(Model model) throws IOException{
		user = new UserEntity();
		model.addAttribute("user", user);
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
	public String login(@ModelAttribute UserEntity userEntity){

		userRepository = new UserRepository();
		user =userRepository.getUserByEmail(userEntity.getEmail());
		if (user != null && user.getPassword().equals(userEntity.getPassword())) {
			if (user.getRole().getRoleName().equals("Admin")) {
				return "redirect:/Profil";
			} else if (user.getRole().getRoleName().equals("Apprenant")) {
				return "redirect:/Student";
			}
		}
		return "redirect:/login";

	}





}
