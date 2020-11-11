package com.bundesreport.component;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.User;
import com.bundesreport.util.MessageUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SidebarBean {

	public SidebarBean(MessageSource messageSource, User user) {
		// ToDo: Selected Locale
		Locale locale = Locale.ROOT;

		if (user != null) {
			this.user = user;
			locale = user.getLanguageStatus().getLocale();
		}

		MessageUtil msgUtil = new MessageUtil();
		title = msgUtil.getMessage(messageSource, "title", locale);
		home = msgUtil.getMessage(messageSource, "menu.home", locale);
		menu = msgUtil.getMessage(messageSource, "menu.menu", locale);
		board = msgUtil.getMessage(messageSource, "menu.board", locale);
		freeBoard = msgUtil.getMessage(messageSource, "menu.freeBoard", locale);
		abroadBoard = msgUtil.getMessage(messageSource, "menu.abroadBoard", locale);
		livingQA = msgUtil.getMessage(messageSource, "menu.livingQA", locale);
		fleaMarket = msgUtil.getMessage(messageSource, "menu.fleaMarket", locale);
		jobSearch = msgUtil.getMessage(messageSource, "menu.jobSearch", locale);
		club = msgUtil.getMessage(messageSource, "menu.club", locale);
		eventNotification = msgUtil.getMessage(messageSource, "menu.eventNotification", locale);
		recipe = msgUtil.getMessage(messageSource, "menu.recipe", locale);
		gallery = msgUtil.getMessage(messageSource, "menu.gallery", locale);
	}

	private User user;

	private String title;

	private String home;

	private String menu;

	private String board;

	private String freeBoard;

	private String abroadBoard;

	private String livingQA;

	private String fleaMarket;

	private String jobSearch;

	private String club;

	private String eventNotification;

	private String recipe;

	private String gallery;
}
