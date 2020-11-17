package com.bundesreport.controller;

import java.util.Locale;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bundesreport.config.AjaxResponseBody;
import com.bundesreport.domain.User;
import com.bundesreport.dto.UserForm;
import com.bundesreport.service.UserService;
import com.bundesreport.util.MessageUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignController extends PageController {

	private final UserService userService;

	@GetMapping(value = "/signin")
	public String signin(Model model, Authentication auth) {
		if (Objects.nonNull(auth)) {
			model = createLayout(model, (User) auth.getPrincipal());
			model.addAttribute("userForm", new UserForm());
			return "signin";
		}
		model = createLayout(model, null);
		model.addAttribute("userForm", new UserForm());
		return "signin";
	}

	@GetMapping(value = "/signup")
	public String signup(Model model, Authentication auth) {
		if (Objects.nonNull(auth)) {
			model = createLayout(model, (User) auth.getPrincipal());
			model.addAttribute("userForm", new UserForm());
			return "signup";
		}
		model = createLayout(model, null);
		model.addAttribute("userForm", new UserForm());
		return "signup";
	}

	@PostMapping(value = "/signup/check")
	public ResponseEntity<?> userValidationViaAjax(@RequestBody UserForm userForm) {

		// ToDo: Use selected Locale
		Locale locale = Locale.ROOT;
		MessageUtil util = new MessageUtil();

		AjaxResponseBody result = new AjaxResponseBody();

		// ID check
		if (Objects.nonNull(userService.findByUserName(userForm.getUserName()))) {
			result.getMsgs().add(util.getMessage(messageSource, "signup.error.id", locale));
		}

		// Email check
		if (userService.findByEmail(userForm.getEmail()).size() > 0) {
			result.getMsgs().add(util.getMessage(messageSource, "signup.error.email", locale));
		}

		return ResponseEntity.ok(result);
	}

	@PostMapping("/signup")
	public String signup(UserForm form) {
		User user = userService.save(form);
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
		return "redirect:/";
	}
}
