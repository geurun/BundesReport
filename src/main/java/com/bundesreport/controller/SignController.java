package com.bundesreport.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bundesreport.domain.User;
import com.bundesreport.dto.UserForm;
import com.bundesreport.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignController extends PageController {

	private final UserService userService;

	@GetMapping(value = "/signin")
	public String signin(Model model, Authentication authentication) {
		if (authentication != null) {
			model = createLayout(model, (User) authentication.getPrincipal());
			model.addAttribute("userForm", new UserForm());
			return "signin";
		}
		model = createLayout(model, null);
		model.addAttribute("userForm", new UserForm());
		return "signin";
	}

	@GetMapping(value = "/signup")
	public String signup(Model model, Authentication authentication) {
		if (authentication != null) {
			model = createLayout(model, (User) authentication.getPrincipal());
			model.addAttribute("userForm", new UserForm());
			return "signup";
		}
		model = createLayout(model, null);
		model.addAttribute("userForm", new UserForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(UserForm form) {
		User user = userService.createUser(form);
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
		return "redirect:/";
	}
}
