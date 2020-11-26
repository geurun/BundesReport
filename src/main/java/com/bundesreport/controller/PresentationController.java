package com.bundesreport.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PresentationController extends PageController {

	@RequestMapping(value = "/presentation")
	public String presentation(Model model, Authentication auth) {
		model = createLayout(model, auth);
		return "presentation/presentation";
	}
}
