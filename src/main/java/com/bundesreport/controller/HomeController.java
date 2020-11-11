package com.bundesreport.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bundesreport.domain.User;

@Controller
public class HomeController extends PageController {

	@RequestMapping(value = "/")
	public String home(Model model, Authentication authentication) {
		if (authentication != null) {
			model = createLayout(model, (User) authentication.getPrincipal());
			return "home";
		}
		model = createLayout(model, null);
		return "home";
	}
}
