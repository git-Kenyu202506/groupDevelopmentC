package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	@Autowired
	private HttpSession session;

	//削除するID確認
	@PostMapping("/delete_check")
	public String delete_check(@Validated Employee employee,
			BindingResult bindingResult, HttpSession session, Model m) {

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

		//		if (!employee.isIdPatternValid()) {
		//			model.addAttribute("idFormError", "入力されたIDの情報が存在しません");
		//			return "deleteForm";
		//		}
		// ログインしているユーザーの情報は削除できない ログイン中のid情報を取得し

		//		if (service.loginUserDeleteError(employee.getId(),sessionUser.getId())) {
		//			m.addAttribute("recordEmptyDeleteError", "ログイン中のユーザーは削除できません。");
		//			return "deleteForm";
		//		}
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

}
