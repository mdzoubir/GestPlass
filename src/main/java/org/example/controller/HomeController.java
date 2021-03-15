package org.example.controller;

import java.io.IOException;

import org.example.Model.UserEntity;
import org.example.Repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {

//	@Autowired
	private UserRepository userRepository;

	UserEntity user;

	@RequestMapping(value="/")
	public String test(Model model) throws IOException{
		user = new UserEntity();
		model.addAttribute("user", user);
		return "Login";
	}

	@RequestMapping(value="/signUp")
	public String signUp(Model model) throws IOException{
//		user = new UserEntity();
//		model.addAttribute("user", user);
		return "SignUp";
	}

	@PostMapping(value = "/Login")
	public String login(@ModelAttribute UserEntity userEntity, Model model){
		System.out.println(userEntity.getEmail());
		System.out.println(userEntity.getPassword());

		userRepository = new UserRepository();
		user =userRepository.getUserByEmail(userEntity.getEmail());
		if (user != null && user.getPassword().equals(userEntity.getPassword())){
			return "redirect:/index";
		}else{
			return "redirect:/";
		}
	}
}
