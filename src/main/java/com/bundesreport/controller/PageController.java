package com.bundesreport.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/")
	public String home() {
		Locale.setDefault(Locale.ROOT);
		System.out.println(messageSource.getMessage("title", new String[] { "title" }, Locale.getDefault()));
		return "home.html";
	}
}
