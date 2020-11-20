package com.bundesreport.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bundesreport.config.AjaxResponse;
import com.bundesreport.domain.User;
import com.bundesreport.dto.UserForm;
import com.bundesreport.service.UserService;
import com.bundesreport.util.MsgUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignController extends PageController {

	@Autowired
	private final UserService userService;

	@GetMapping(value = "/signin")
	public String signin(Model model, Authentication auth) {
		model = createLayout(model, auth);
		model.addAttribute("userForm", new UserForm());
		return "/user/signin";
	}

	@GetMapping(value = "/signup")
	public String signup(Model model, Authentication auth) {
		model = createLayout(model, auth);
		model.addAttribute("userForm", new UserForm());
		return "/user/signup";
	}

	@PostMapping(value = "/signup/check")
	public ResponseEntity<?> userValidationViaAjax(@RequestBody UserForm userForm) {
		MsgUtil util = new MsgUtil(msgSrc);
		AjaxResponse result = new AjaxResponse();

		// ID check
		if (Objects.nonNull(userService.findByUsername(userForm.getUsername()))) {
			result.getMsgs().add(util.getMessage("signup.error.id"));
		}

		// Email check
		if (userService.findByEmail(userForm.getEmail()).size() > 0) {
			result.getMsgs().add(util.getMessage("signup.error.email"));
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
