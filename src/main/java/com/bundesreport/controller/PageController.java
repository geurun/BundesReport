package com.bundesreport.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.bundesreport.component.SidebarBean;
import com.bundesreport.component.TopbarBean;
import com.bundesreport.domain.Note;
import com.bundesreport.domain.User;
import com.bundesreport.service.NoteService;

@Controller
public class PageController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	NoteService noteService;

	protected Model createLayout(Model model) {
		return createLayout(model, null);
	}

	protected Model createLayout(Model model, User user) {
		List<Note> noteList = new ArrayList<>();
		if (Objects.nonNull(user)) {
			noteList = noteService.findByReceiverOrderByCreatedDateAsc(user);
		}
		// ToDo: Use User's initial Language or Selected Language
		model.addAttribute("sidebar", new SidebarBean(messageSource, user));
		model.addAttribute("topbar", new TopbarBean(messageSource, user, noteList));
		return model;
	}
}
