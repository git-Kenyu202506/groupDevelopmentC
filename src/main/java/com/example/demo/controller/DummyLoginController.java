package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Employee;

@Controller
@RequestMapping("/employee")
public class DummyLoginController {
	
	@RequestMapping("/login_Form")
	public String loginForm(Model m) {
		m.addAttribute("Employee",new Employee());
		return "login_Form";
	}

	@PostMapping("/mainMenu")
	public String login(@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			Model m,
			HttpSession session) {
		
		LocalDateTime loginDateTime = LocalDateTime.now();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String formattedDateTime =loginDateTime.format(formatter);
		
		Employee loginUserEmployee = new Employee();
			loginUserEmployee.setId(Integer.parseInt(id));
			loginUserEmployee.setName(name);
			loginUserEmployee.setPassword(password);
	

		session.setAttribute("id", loginUserEmployee.getId());	
		session.setAttribute("name", loginUserEmployee.getName());
		session.setAttribute("loginDateTime", formattedDateTime);

		return "mainMenu";
	}

}
