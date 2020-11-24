package com.bundesreport.controller;

import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bundesreport.domain.User;

@Controller
public class PresentationController extends PageController{
	
	@RequestMapping(value = "/presentation")
	public String presentation(Model model, Authentication auth) {
		model = createLayout(model, auth);
		User user = null;
		if (Objects.nonNull(auth)) {
			user = (User) auth.getPrincipal();
		}
		
		return "presentation/presentation";
	}
}
