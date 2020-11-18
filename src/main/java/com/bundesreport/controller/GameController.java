package com.bundesreport.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameController extends PageController {

	@RequestMapping(value = "/game/rain")
	public String rain(Model model, Authentication auth) {
		createLayout(model, auth);
		return "games/rain";
	}
}
