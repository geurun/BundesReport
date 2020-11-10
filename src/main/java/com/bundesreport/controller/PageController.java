package com.bundesreport.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;

import com.bundesreport.component.SidebarBean;

public class PageController {

	@Autowired
	MessageSource messageSource;

	protected Model createSidebar(Model model) {
		// ToDo: Use User's initial Language or Selected Language
		SidebarBean sidebar = new SidebarBean(messageSource, Locale.ROOT);
		model.addAttribute("sidebar", sidebar);
		return model;
	}
}
