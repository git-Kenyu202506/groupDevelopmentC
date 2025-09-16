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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Employee;
import com.example.demo.service.DeleteService;

// http://localhost:8080/employee/deleteForm

@Controller
@RequestMapping("/employee")
public class DeleteController {

	@Autowired
	private DeleteService service;

	@RequestMapping("/deleteForm")
	public String deleteFrom(HttpSession session, Model m) {

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		return "deleteForm";
	}

	//削除するID確認
	@PostMapping("/delete_check")
	public String delete_check(@Validated Employee employee,
			BindingResult bindingResult, HttpSession session, Model m) {

		Integer loginUserId = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		//入力エラーがあれば入力フォームに転移
		if (bindingResult.hasErrors()) {
			return "deleteForm";
		}
		if (!service.nullDeleteError(employee)) {
			m.addAttribute("idnull", "IDを入力してください");
			return "deleteForm";
		}
		// 入力されたIDが存在しないエラー
		if (!service.recordEmptyDeleteError(employee)) {
			m.addAttribute("recordEmptyDeleteError", "該当のレコードがありません。");
			return "deleteForm";
		}

		// ログインしているユーザーの情報は削除できない ログイン中のid情報を取得し
		if (loginUserId != null && service.loginUserDeleteError(employee.getId(), loginUserId)) {
			m.addAttribute("recordEmptyDeleteError", "ログイン中のユーザーは削除できません。");
			return "deleteForm";
		}
		return "delete_check";
	}

	// 削除完了画面に転移
	@PostMapping("/delete")
	public String deleteEmployee(Model m, HttpSession session, @RequestParam("id") String id) {

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		int numId = Integer.parseInt(id);
		service.delete(numId);
		m.addAttribute("msg", "社員情報の削除が完了しました");
		return "delete_result";
	}

	// メインメニュー画面に転移(菅原さんファイル参照)
	@GetMapping("/backToMainMenu")
	public String backToMainMenu(Model m, HttpSession session) {

		//ログイン情報のセッションを取得
		Integer id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		return "mainMenu";
	}

	@PostMapping("/backDeleteForm")
	public String backdeleteForm(@ModelAttribute("Employee") Employee employee, Model m) {
		m.addAttribute("Employee", employee);
		return "deleteForm";
	}

	// メインメニュー画面に転移(菅原さんファイル参照)
	@RequestMapping("/backMainMenu")
	public String backMainMenu(Model m, HttpSession session) {

		//ログイン情報のセッションを取得
		Integer id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		return "mainMenu";
	}
	@RequestMapping("/seachEmployee")
	public String seachEmployee(HttpSession session, Model m) {

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		return "seachEmployee";
	}

}
