package com.bundesreport.controller;

import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bundesreport.domain.User;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GameController extends PageController {

	@RequestMapping(value = "/game/rain")
	public String rain(Model model, Authentication auth) {
		if (Objects.nonNull(auth)) {
			model = createLayout(model, (User) auth.getPrincipal());
			return "games/rain";
		}
		model = createLayout(model, null);
		return "games/rain";
	}
}
