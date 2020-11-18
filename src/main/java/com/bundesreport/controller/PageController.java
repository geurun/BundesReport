package com.bundesreport.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
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
	MessageSource msgSrc;

	@Autowired
	NoteService noteService;

	protected Model createLayout(Model model, Authentication auth) {
		User user = null;
		List<Note> noteList = new ArrayList<>();
		if (Objects.nonNull(auth)) {
			user = (User) auth.getPrincipal();
			noteList = noteService.findByReceiver(user);
		}
		model.addAttribute("sidebar", new SidebarBean(msgSrc, user));
		model.addAttribute("topbar", new TopbarBean(msgSrc, user, noteList));
		return model;
	}
}
