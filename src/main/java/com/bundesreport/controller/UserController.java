package com.bundesreport.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bundesreport.component.UserProfileBean;
import com.bundesreport.domain.User;
import com.bundesreport.dto.UserForm;
import com.bundesreport.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController extends PageController {

	private final UserService userService;

	@GetMapping(value = "/user/profile")
	public String userProfile(Model model, Authentication auth) {
		if (auth != null) {
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
		User user = userService.updateUser(form);
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
		return "redirect:/user/profile";
	}

	@GetMapping(value = "/user/withdrawal")
	public String userProfileDelete(Model model, Authentication auth) {
		if (auth != null) {
			User user = (User) auth.getPrincipal();
			userService.deleteUser(user);
		}
		return "redirect:/signout";
	}
}
