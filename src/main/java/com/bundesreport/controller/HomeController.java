package com.bundesreport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends PageController {

	@RequestMapping(value = "/")
	public String home(Model model) {
		model = createSidebar(model);
		return "home";
	}
}
