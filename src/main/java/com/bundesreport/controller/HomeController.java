package com.bundesreport.controller;

import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bundesreport.domain.User;

@Controller
public class HomeController extends PageController {

	@RequestMapping(value = "/")
	public String home(Model model, Authentication auth) {
		if (Objects.nonNull(auth)) {
			model = createLayout(model, (User) auth.getPrincipal());
			return "home";
		}
		model = createLayout(model);
		return "home";
	}
}
