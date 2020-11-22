package com.bundesreport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.bundesreport.component.SidebarBean;
import com.bundesreport.component.TopbarBean;
import com.bundesreport.dto.SearchForm;
import com.bundesreport.service.NoteService;

@Controller
public class PageController {

	@Autowired
	MessageSource msgSrc;

	@Autowired
	NoteService noteService;

	protected Model createLayout(Model model, Authentication auth) {
		model.addAttribute("sidebar", new SidebarBean(msgSrc, auth));
		model.addAttribute("topbar", new TopbarBean(msgSrc, auth, noteService));
		model.addAttribute("searchForm", new SearchForm());
		return model;
	}
}
