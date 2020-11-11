package com.bundesreport.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.bundesreport.component.SidebarBean;
import com.bundesreport.component.TopbarBean;
import com.bundesreport.domain.User;

@Controller
public class PageController {

	@Autowired
	MessageSource messageSource;

	protected Model createLayout(Model model, User user) {
		// ToDo: Use User's initial Language or Selected Language
		SidebarBean sidebar = new SidebarBean(messageSource, Locale.ROOT);
		TopbarBean topbar = new TopbarBean(messageSource, null);
		if (user != null) {
			sidebar = new SidebarBean(messageSource, Locale.ROOT);
			topbar = new TopbarBean(messageSource, user);
		}
		model.addAttribute("sidebar", sidebar);
		model.addAttribute("topbar", topbar);
		return model;
	}
}
