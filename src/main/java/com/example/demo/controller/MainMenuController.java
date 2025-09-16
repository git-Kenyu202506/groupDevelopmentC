package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Employee;

@Controller
public class MainMenuController {

	@Autowired
	private HttpSession session;

	@RequestMapping("/employee/mainMenu")
	public String showMainMenu(Model m) {

		//ログイン情報のセッションを取得
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("id", id);
		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		return "mainMenu";
	}

	//社員情報登録画面へ遷移
	@GetMapping("/employee/register")
	public String showRegister() {
		return "register";
	}

	//社員情報削除（入力）画面へ遷移
	@GetMapping("/employee/deleteForm")
	public String deleteFrom(HttpSession session, Model m) {

		//Integer id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		m.addAttribute("Employee", new Employee());

		return "deleteForm";
	}

	//社員情報更新（社員ID入力）画面
	@GetMapping("/employee/update")
	public String showUpdate() {
		return "updateInsert";
	}
}
