package com.pos.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class StartController {

	@GetMapping("/")
	public String doStart() {
		return "/register/login";
	}
	
	@GetMapping("/signup")
	public String doSignUp() {
		return "/register/signup";
	}
}
