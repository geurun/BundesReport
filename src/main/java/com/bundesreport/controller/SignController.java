package com.bundesreport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bundesreport.dto.UserForm;
import com.bundesreport.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignController extends PageController {

	private final UserService userService;

	@GetMapping(value = "/signin")
	public String signin(Model model) {
		model = createSidebar(model);
		model.addAttribute("userForm", new UserForm());
		return "signin";
	}

	@GetMapping(value = "/signup")
	public String signup(Model model) {
		model = createSidebar(model);
		model.addAttribute("userForm", new UserForm());
		return "signup.html";
	}

	@PostMapping("/signup")
	public String signup(UserForm form) {
		userService.createUser(form);
		return "redirect:/";
	}
}
