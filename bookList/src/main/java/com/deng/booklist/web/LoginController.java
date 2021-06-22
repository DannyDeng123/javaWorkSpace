package com.deng.booklist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.deng.booklist.entity.User;
import com.deng.booklist.form.UserForm;
import com.deng.booklist.repository.UserRepository;

@Controller
public class LoginController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/register")
	public String registerPost(UserForm userForm) {
		User user = userForm.convertToUser();
		userRepository.save(user);
		return "redirect:/login";
	}
	
}
