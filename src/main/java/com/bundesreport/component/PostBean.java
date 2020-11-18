package com.bundesreport.component;

import java.util.Locale;
import java.util.Objects;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.type.CategoryType;
import com.bundesreport.util.MessageUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBean {

	public PostBean(MessageSource messageSource, User user, Post post, CategoryType category) {
		// ToDo: Selected Locale
		Locale locale = Locale.ROOT;

		if (user != null) {
			this.user = user;
			locale = user.getLanguageStatus().getLocale();
		}

		this.post = post;
		this.category = category;

		if (Objects.isNull(category) && Objects.nonNull(post)) {
			this.category = post.getCategory();
		}

		if (Objects.nonNull(user) && Objects.nonNull(post)) {
			if (user.getUsername().equals(post.getUser().getUsername())) {
				hasPermit = 1;
			}
		}

		MessageUtil msgUtil = new MessageUtil();
		title = msgUtil.getMessage(messageSource, this.category.getMessageCode(), locale);
		postTitle = msgUtil.getMessage(messageSource, "post.postTitle", locale);
		postContent = msgUtil.getMessage(messageSource, "post.postContent", locale);
		createdUser = msgUtil.getMessage(messageSource, "post.createdUser", locale);
		createdDate = msgUtil.getMessage(messageSource, "post.createdDate", locale);
		updatedDate = msgUtil.getMessage(messageSource, "post.updatedDate", locale);
		btnList = msgUtil.getMessage(messageSource, "post.btnList", locale);
		btnSave = msgUtil.getMessage(messageSource, "post.btnSave", locale);
		btnModify = msgUtil.getMessage(messageSource, "post.btnModify", locale);
		btnDelete = msgUtil.getMessage(messageSource, "post.btnDelete", locale);
	}

	private User user;

	private Post post;

	private CategoryType category;

	private String title;

	private String postTitle;

	private String postContent;

	private String createdUser;

	private String createdDate;

	private String updatedDate;

	private String btnList;

	private String btnSave;

	private String btnModify;

	private String btnDelete;

	private int hasPermit = 0;
}
