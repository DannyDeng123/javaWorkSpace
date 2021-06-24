package com.deng.booklist.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	public String registerPage(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "register";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/register")
	public String registerPost(@Valid UserForm userForm, BindingResult result, Model model) {
		
		if(!userForm.confirmPassword()) {
			result.rejectValue("confirmPassword", "confirm", "兩次密碼必須相同");
		}
		if(result.hasErrors()) {
			List<FieldError> errList = result.getFieldErrors();
			for(FieldError err : errList) {
				System.out.println(err.getField() + " : " + err.getDefaultMessage() + " : " + err.getCode());
			}
			return "register";
		}
		User user = userForm.convertToUser();
		userRepository.save(user);
		return "redirect:/login";
	}
	
	@GetMapping("/exception")
	public String testException() {
		throw new RuntimeException("測試異常處理");
	}
}
