package com.bundesreport.component;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SidebarBean extends MessageBean {

	public SidebarBean(MessageSource msgSrc, User user) {
		super(msgSrc, user);

		title = msgUtil.getMessage("title");
		home = msgUtil.getMessage("menu.home");
		menu = msgUtil.getMessage("menu.menu");
		board = msgUtil.getMessage("menu.board");
		freeBoard = msgUtil.getMessage("menu.freeBoard");
		abroadBoard = msgUtil.getMessage("menu.abroadBoard");
		livingQA = msgUtil.getMessage("menu.livingQA");
		fleaMarket = msgUtil.getMessage("menu.fleaMarket");
		jobSearch = msgUtil.getMessage("menu.jobSearch");
		club = msgUtil.getMessage("menu.club");
		eventNotification = msgUtil.getMessage("menu.eventNotification");
		recipe = msgUtil.getMessage("menu.recipe");
		gallery = msgUtil.getMessage("menu.gallery");
		event = msgUtil.getMessage("menu.event");
		miniGame = msgUtil.getMessage("menu.miniGame");
		rain = msgUtil.getMessage("menu.rain");
	}

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
	private String event;
	private String miniGame;
	private String rain;
}
