package com.bundesreport.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bundesreport.component.SidebarBean;

@Controller
public class PageController {

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/")
	public String home(Model model) {
		model = createSidebar(model);
		return "home.html";
	}

	private Model createSidebar(Model model) {
		// ToDo: Use User's initial Language or Selected Language
		SidebarBean sidebar = new SidebarBean(messageSource, Locale.ROOT);
		model.addAttribute("sidebar", sidebar);
		return model;
	}
}
