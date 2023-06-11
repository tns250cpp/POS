package com.pos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pos.web.entity.Member;
import com.pos.web.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private MemberService memberService;

	@GetMapping("")
	public String main(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(false);
	    if (session == null) {
	    	model.addAttribute("msg", "로그인 후 이용해주세요.");
			model.addAttribute("url", "/");
			return "/alert/msg";
	    }
		return "/home/main";
	}

	@GetMapping("/logout")
	public String doLogOut(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
	    if (session != null) {
	    	System.out.println("invalidate 실행전 : " + session.getAttribute("role"));
	        session.invalidate();
	    }
		return "redirect:/";
	}

	@PostMapping("/login")
	public String doLogIn(HttpServletRequest request, Model model, @RequestParam(value = "id") String id,
			@RequestParam(value = "password") String password) {
		Member member;
		try {
			member = memberService.login(id, password);
		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("msg", "로그인 정보를 다시 확인해주세요.");
			model.addAttribute("url", "/");
			return "/alert/msg";
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("role", member.getRole());

		return "/home/main";
	}

	@PostMapping("/signup")
	public String doSignUp(Model model, @RequestParam(value = "id") String id,
			@RequestParam(value = "password") String Pwd, @RequestParam(value = "confirmPassword") String confirmPwd,
			@RequestParam(value = "role") String role) {

		if (!Pwd.equals(confirmPwd)) {
			model.addAttribute("msg", "비밀번호 불일치");
			model.addAttribute("url", "/signup");
			return "/alert/msg";
		}

		try {
			memberService.regist(id, Pwd, role);

		} catch (Exception e) {
			System.out.println(e.toString());
			model.addAttribute("msg", "존재하는 아이디입니다. 다른 아이디를 선택해주세요.");
			model.addAttribute("url", "/signup");
			return "/alert/msg";
		}

		return "redirect:/";
	}
}
