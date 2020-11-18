package com.bundesreport.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bundesreport.component.NoteBean;
import com.bundesreport.component.NoteListBean;
import com.bundesreport.domain.Note;
import com.bundesreport.domain.User;
import com.bundesreport.dto.NoteForm;
import com.bundesreport.service.NoteService;
import com.bundesreport.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoteController extends PageController {

	@Autowired
	private final NoteService noteService;

	@Autowired
	private final UserService userService;

	@GetMapping(value = "/note/list")
	public String list(Model model, Authentication auth) {
		model = createLayout(model, auth);
		if (Objects.nonNull(auth)) {
			User user = (User) auth.getPrincipal();
			model.addAttribute("bean", new NoteListBean(msgSrc, user, noteService.findByReceiver(user)));
		}
		return "note/list";
	}

	@GetMapping(value = "/note/{id}")
	public String view(@PathVariable("id") Long id, Model model, Authentication auth) {
		model = createLayout(model, auth);
		if (Objects.nonNull(auth)) {
			Optional<Note> note = noteService.findById(id);
			if (Objects.isNull(note)) {
				return "redirect:/note/list";
			}
			User user = (User) auth.getPrincipal();
			if (!user.getUsername().equals(note.get().getReceiver().getUsername())) {
				return "redirect:/note/list";
			}
			model.addAttribute("bean", new NoteBean(msgSrc, user, note.get()));
		}
		return "note/view";
	}

	@GetMapping(value = "/note/new/{receiverId}")
	public String write(@PathVariable("receiverId") Long receiverId, Model model, Authentication auth) {
		model = createLayout(model, auth);
		if (Objects.nonNull(auth)) {
			User user = (User) auth.getPrincipal();
			model.addAttribute("bean", new NoteBean(msgSrc, user, null));
			model.addAttribute("noteForm", new NoteForm(userService.findById(receiverId)));
		}
		return "note/write";
	}

	@PostMapping(value = "/note/new")
	public String write(NoteForm form, Authentication auth) {
		form.setSender((User) auth.getPrincipal());
		noteService.save(form);
		return "redirect:/note/list";
	}

	@GetMapping(value = "/note/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model, Authentication auth) {
		Optional<Note> note = noteService.findById(id);
		if (Objects.nonNull(note)) {
			noteService.delete(note.get());
		}
		return "redirect:/note/list";
	}
}
