package com.bundesreport.component;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Note;
import com.bundesreport.domain.User;
import com.bundesreport.util.MessageUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopbarBean {

	public TopbarBean(MessageSource messageSource, User user) {
		// ToDo: Selected Locale
		Locale locale = Locale.ROOT;

		if (user != null) {
			this.user = user;
			locale = user.getLanguageStatus().getLocale();
		}

		MessageUtil msgUtil = new MessageUtil();
		messageCenter = msgUtil.getMessage(messageSource, "topbar.messageCenter", locale);
		readMore = msgUtil.getMessage(messageSource, "topbar.readMore", locale);
		signin = msgUtil.getMessage(messageSource, "topbar.button.signin", locale);
		signup = msgUtil.getMessage(messageSource, "topbar.button.signup", locale);
		profile = msgUtil.getMessage(messageSource, "topbar.button.profile", locale);
		signout = msgUtil.getMessage(messageSource, "topbar.button.signout", locale);

		// ToDo: Get Note (New 5)
		// Notes findBy
		// Make Link
		// messageCount = list.size();
		if (messageCount > 99) {
			messageCount = 99;
		}
	}

	private User user;

	private List<Note> messages;

	private int messageCount;

	private String messageCenter;

	private String readMore;

	private String signin;

	private String signup;

	private String profile;

	private String signout;

	private String menu;
}
