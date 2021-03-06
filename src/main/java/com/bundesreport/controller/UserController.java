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

import com.bundesreport.component.ProfileBean;
import com.bundesreport.config.AjaxResponse;
import com.bundesreport.domain.User;
import com.bundesreport.dto.UserForm;
import com.bundesreport.service.CommentService;
import com.bundesreport.service.PostService;
import com.bundesreport.service.UserService;
import com.bundesreport.util.MsgUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController extends PageController {

	@Autowired
	private final UserService userService;

	@Autowired
	private final PostService postService;

	@Autowired
	private final CommentService commentService;

	@GetMapping(value = "/user/profile")
	public String userProfile(Model model, Authentication auth) {
		if (Objects.nonNull(auth)) {
			model = createLayout(model, auth);
			model.addAttribute("bean", new ProfileBean(msgSrc, auth, postService, commentService));
			model.addAttribute("userForm", ((User) auth.getPrincipal()).toUserForm());
		}
		return "/user/profile";
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
		MsgUtil util = new MsgUtil(msgSrc);
		AjaxResponse result = new AjaxResponse();

		if (Objects.nonNull(auth)) {
			User user = (User) auth.getPrincipal();
			if (user.getEmail().equals(userForm.getEmail())) {
				return ResponseEntity.ok(result);
			}
		}

		// Email check
		if (userService.findByEmail(userForm.getEmail()).size() > 0) {
			result.getMsgs().add(util.getMessage("user.error.email"));
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
