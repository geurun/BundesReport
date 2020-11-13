package com.bundesreport.component;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.User;
import com.bundesreport.util.MessageUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileBean {

	public UserProfileBean(MessageSource messageSource, User user) {
		// ToDo: Selected Locale
		Locale locale = Locale.ROOT;

		if (user != null) {
			this.user = user;
			locale = user.getLanguageStatus().getLocale();
		}

		// ToDo: get from post
		postCount = 0;
		postLikeCount = 0;

		// ToDo: get from comment
		commentCount = 0;
		commentLikeCount = 0;

		MessageUtil msgUtil = new MessageUtil();
		title = msgUtil.getMessage(messageSource, "user.title", locale);
		myPostCount = msgUtil.getMessage(messageSource, "user.myPostCount", locale);
		myPostLikeCount = msgUtil.getMessage(messageSource, "user.myPostLikeCount", locale);
		myCommentCount = msgUtil.getMessage(messageSource, "user.myCommentCount", locale);
		myCommentLikeCount = msgUtil.getMessage(messageSource, "user.myCommentLikeCount", locale);
		modifyUserInfo = msgUtil.getMessage(messageSource, "user.modifyUserInfo", locale);
		userName = msgUtil.getMessage(messageSource, "user.username", locale);
		password = msgUtil.getMessage(messageSource, "user.password", locale);
		email = msgUtil.getMessage(messageSource, "user.email", locale);
		languageStatus = msgUtil.getMessage(messageSource, "user.languageStatus", locale);
		withdrawal = msgUtil.getMessage(messageSource, "user.withdrawal", locale);
		save = msgUtil.getMessage(messageSource, "user.save", locale);
	}

	private User user;

	private int postCount;

	private int postLikeCount;

	private int commentCount;

	private int commentLikeCount;

	private String title;

	private String myPostCount;

	private String myPostLikeCount;

	private String myCommentCount;

	private String myCommentLikeCount;

	private String modifyUserInfo;

	private String userName;

	private String password;

	private String email;

	private String languageStatus;

	private String withdrawal;

	private String save;
}
