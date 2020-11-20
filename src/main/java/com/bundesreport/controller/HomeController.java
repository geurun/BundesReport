package com.bundesreport.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bundesreport.component.HomeBean;
import com.bundesreport.domain.User;
import com.bundesreport.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController extends PageController {

	@Autowired
	private final PostService postService;

	@RequestMapping(value = "/")
	public String home(Model model, Authentication auth) {
		model = createLayout(model, auth);
		User user = null;
		if (Objects.nonNull(auth)) {
			user = (User) auth.getPrincipal();
		}
		model.addAttribute("bean", new HomeBean(msgSrc, user, postService));
		return "home";
	}
}
