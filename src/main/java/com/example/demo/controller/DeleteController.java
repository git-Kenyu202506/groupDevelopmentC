package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Employee;
import com.example.demo.service.DeleteService;

// http://localhost:8080/employee/deleteForm

@Controller
public class DeleteController {

	@Autowired
	private DeleteService service;

	@RequestMapping("/employee/deleteForm")
	public String showDeleteForm(Model model, HttpSession session) {

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");
		model.addAttribute("name", name);
		model.addAttribute("loginDateTime", loginDateTime);

		model.addAttribute("Employee", new Employee());

		return "deleteForm";
	}

	//削除するID確認
	@PostMapping("/employee/delete_check")
	public String delete_check(@Validated @ModelAttribute("Employee") Employee employee,
			BindingResult bindingResult, HttpSession session, Model m) {

		Integer loginUserId = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		m.addAttribute("Employee", employee);

		//入力エラーがあれば入力フォームに転移
		if (bindingResult.hasErrors()) {
			return "deleteForm";
		}
		if (!service.nullDeleteError(employee)) {
			m.addAttribute("idNull", "IDを入力してください");
			return "deleteForm";
		}
		// 入力されたIDが存在しないエラー
		if (!service.recordEmptyDeleteError(employee)) {
			m.addAttribute("recordEmptyDeleteError", "該当のレコードがありません。");
			return "deleteForm";
		}

		// ログインしているユーザーの情報は削除できない ログイン中のid情報を取得し
		if (loginUserId != null && service.loginUserDeleteError(employee.getId(), loginUserId)) {
			m.addAttribute("loginUserDeleteError", "ログイン中のユーザーは削除できません。");
			return "deleteForm";
		}
		return "delete_check";
	}

	// 削除完了画面に転移
	@PostMapping("/employee/delete")
	public String deleteEmployee(@ModelAttribute("Employee") Employee employee, Model m, HttpSession session) {

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		service.delete(employee.getId());
		m.addAttribute("msg", "社員情報の削除が完了しました");
		return "delete_result";
	}

	// メインメニュー画面に転移(菅原さんファイル参照)
	@GetMapping("/employee/backToMainMenu")
	public String backToMainMenu(Model m, HttpSession session) {

		//ログイン情報のセッションを取得
		Integer id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		return "mainMenu";
	}

	@PostMapping("/employee/backDeleteForm")
	public String backdeleteForm(@ModelAttribute("Employee") Employee employee, Model m) {
		m.addAttribute("Employee", employee);
		return "deleteForm";
	}

	// メインメニュー画面に転移(菅原さんファイル参照)
	@RequestMapping("/employee/backMainMenu")
	public String backMainMenu(Model m, HttpSession session) {

		//ログイン情報のセッションを取得
		Integer id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		return "mainMenu";
	}

	@RequestMapping("/employee/seachEmployee")
	public String seachEmployee(HttpSession session, Model m) {

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		return "seachEmployee";
	}

}
