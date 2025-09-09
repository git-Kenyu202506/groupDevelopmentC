package com.example.demo.controller;

import java.time.LocalDate;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.RegisterService;

// http://localhost:8080/insertForm

@Controller
@RequestMapping("/employee")
public class RegisterController {
	@Autowired
	private RegisterService service;
	@Autowired
	private HttpSession session;

	
	
	//入力された情報の確認画面
	@PostMapping("/insert_check")
	public String insert_check(@ModelAttribute("Employee") @Validated Employee employee,
			BindingResult bindingResult,
			Model m) {
		
		//ログイン中のセッションを取得 MainMenuより参照
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");
		
		m.addAttribute("id", id);
		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		if (bindingResult.hasErrors()) {
			return "insertForm";
		}
		// パスワードのフォーマット確認
		if (!service.isPasswordPatternValid(employee)) {
			m.addAttribute("passwordFormError", "パスワードは半角英数字を含めてください");
			return "insertForm";
		}
		// パスワードの一致確認
		if (!service.isPasswordMatching(employee)) {
			m.addAttribute("passwordUnmatch", "パスワードが一致しません。");
			return "insertForm";
		}
		return "insert_check";
	}

	@PostMapping("/insert")
	public String insertEmployeee(@ModelAttribute Employee employee) {
		if (employee.getStartDate() == null) {
			employee.setStartDate(LocalDate.now());
		}
		service.insert(employee);
		return "insert_result";
	}
}
