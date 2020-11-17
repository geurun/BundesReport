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

import com.bundesreport.component.UserProfileBean;
import com.bundesreport.config.AjaxResponseBody;
import com.bundesreport.domain.User;
import com.bundesreport.dto.UserForm;
import com.bundesreport.service.UserService;
import com.bundesreport.util.MessageUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController extends PageController {

	private final UserService userService;

	@GetMapping(value = "/user/profile")
	public String userProfile(Model model, Authentication auth) {
		if (Objects.nonNull(auth)) {
			User user = (User) auth.getPrincipal();
			model = createLayout(model, user);
			model.addAttribute("bean", new UserProfileBean(messageSource, user));
			model.addAttribute("userForm", user.toUserForm());
			return "profile";
		}
		model = createLayout(model, null);
		return "profile";
	}

	@PostMapping(value = "/user/profile")
	public String userProfileUdate(UserForm form) {
		User user = userService.update(form);
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
		return "redirect:/user/profile";
	}

	@PostMapping(value = "/user/profile/check")
	public ResponseEntity<?> userProfileValidationViaAjax(@RequestBody UserForm userForm, Authentication auth) {

		// ToDo: Use selected Locale
		Locale locale = Locale.ROOT;
		MessageUtil util = new MessageUtil();

		AjaxResponseBody result = new AjaxResponseBody();

		if (Objects.nonNull(auth)) {
			User user = (User) auth.getPrincipal();
			if (user.getEmail().equals(userForm.getEmail())) {
				return ResponseEntity.ok(result);
			}
		}
		// Email check
		if (userService.findByEmail(userForm.getEmail()).size() > 0) {
			result.getMsgs().add(util.getMessage(messageSource, "user.error.email", locale));
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/user/withdrawal")
	public String userProfileDelete(Model model, Authentication auth) {
		if (Objects.nonNull(auth)) {
			userService.delete((User) auth.getPrincipal());
		}
		return "redirect:/signout";
	}
}
