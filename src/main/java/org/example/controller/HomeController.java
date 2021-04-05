package org.example.controller;

import java.io.IOException;

import org.example.Model.DemandeEntity;
import org.example.Model.UserEntity;
import org.example.Repository.DemandeRepository;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller

public class HomeController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DemandeRepository demandeRepository;

	public static UserEntity user;

	private DemandeEntity demandeEntity;

	@RequestMapping(value="/login")
	public String test(Model model){
		user = new UserEntity();
		model.addAttribute("user", user);
		model.addAttribute("msg", "");
		return "Login";
	}

	@RequestMapping(value="/signUp")
	public String signUp(Model model){
		demandeEntity = new DemandeEntity();
		model.addAttribute("demande", demandeEntity);
		model.addAttribute("msg", "");
		return "SignUp";
	}

	@PostMapping(value = "/Login")
	public String login(@ModelAttribute UserEntity userEntity, Model model, HttpSession session){
		user =userRepository.getUserByEmail(userEntity.getEmail());
		demandeEntity = demandeRepository.getUserByEmail(userEntity.getEmail());
		if (demandeEntity != null){
			model.addAttribute("msg", "Your request is being processed");
			user = new UserEntity();
			model.addAttribute("user", user);
			return "Login";
		}else{
			if (user != null  && passwordEncoder.matches(userEntity.getPassword(), user.getPassword()) == true) {
				session.setAttribute("email", user.getEmail());
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

	@RequestMapping(value = "logout")
	public String logout(HttpSession session){
		session.removeAttribute("email");
		return "redirect:/login";
	}




}
