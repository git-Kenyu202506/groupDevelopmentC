package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@RequestMapping("/someDeleteForm")
	public String showSomeDeleteForm(@RequestParam(value = "id", required = false) List<Integer> ids, Model m,
			HttpSession session) {

		if (ids == null) {
			ids = new ArrayList<>();
		}

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");
		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		m.addAttribute("Employee", new Employee());
		m.addAttribute("ids", ids);

		return "someDeleteForm";
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

	@PostMapping("/employee/someDelete_check")
	public String someDelete_check(@RequestParam(value = "id", required = false) List<Integer> ids,
			HttpSession session, Model m) {

		Integer loginUserId = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		m.addAttribute("ids", ids);

		if (ids == null) {
			ids = new ArrayList();
		}

		ids.removeIf(id -> id == null || id == 0);

		if (ids == null || ids.isEmpty()) {
			m.addAttribute("idNull", "IDを一つ以上入力してください");
			return "someDeleteForm";
		}

		Set<Integer> uniqueIds = new HashSet<>();

		for (Integer id : ids) {
			Employee employee = new Employee();
			employee.setId(id);

			if (!uniqueIds.add(id)) {
				m.addAttribute("duplicateError", "同じIDが複数入力されています: " + id);
				return "someDeleteForm";
			}

			if (!service.recordEmptyDeleteError(employee)) {
				m.addAttribute("recordEmptyDeleteError", "レコードに存在しないIDが含まれています:" + id);
				return "someDeleteForm";
			}

			if (loginUserId != null && id.equals(loginUserId)) {
				m.addAttribute("loginUserDeleteError", "ログイン中のユーザーは削除できません。");
				return "someDeleteForm";
			}
		}
		return "someDelete_check";
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

	@PostMapping("/employee/someDelete")
	public String deleteSomeEmployee(@RequestParam("id") List<Integer> ids,
			Model m, HttpSession session) {

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		for (Integer id : ids) {
			service.delete(id);
		}
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
	public String backdeleteForm(@ModelAttribute("Employee") Employee employee, Model m, HttpSession session) {

		Integer id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		m.addAttribute("Employee", employee);
		return "deleteForm";
	}

	@PostMapping("/employee/backSomeDeleteForm")
	public String backsomedeleteForm(@RequestParam("id") List<Integer> ids,
			Model m, HttpSession session) {

		Integer id = (Integer) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);
		m.addAttribute("ids", ids);
		return "someDeleteForm";
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
	public String searchEmployee(HttpSession session, Model m) {

		String name = (String) session.getAttribute("name");
		String loginDateTime = (String) session.getAttribute("loginDateTime");

		m.addAttribute("name", name);
		m.addAttribute("loginDateTime", loginDateTime);

		return "searchEmployee";
	}

}
