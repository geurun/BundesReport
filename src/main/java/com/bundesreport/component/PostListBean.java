package com.bundesreport.component;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.type.CategoryType;
import com.bundesreport.util.MessageUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostListBean {

	public PostListBean(MessageSource messageSource, User user, List<Post> posts, CategoryType category) {
		// ToDo: Selected Locale
		Locale locale = Locale.ROOT;

		if (user != null) {
			this.user = user;
			locale = user.getLanguageStatus().getLocale();
		}

		this.posts = posts;
		this.category = category;

		MessageUtil msgUtil = new MessageUtil();
		title = msgUtil.getMessage(messageSource, category.getMessageCode(), locale);
		number = msgUtil.getMessage(messageSource, "post.list.number", locale);
		postTitle = msgUtil.getMessage(messageSource, "post.list.postTitle", locale);
		createdUser = msgUtil.getMessage(messageSource, "post.list.createdUser", locale);
		createdDate = msgUtil.getMessage(messageSource, "post.list.createdDate", locale);
		write = msgUtil.getMessage(messageSource, "post.list.write", locale);
	}

	private User user;

	private List<Post> posts;

	private CategoryType category;

	private String title;

	private String number;

	private String postTitle;

	private String createdUser;

	private String createdDate;

	private String write;
}
