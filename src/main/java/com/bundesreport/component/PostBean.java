package com.bundesreport.component;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.util.MessageUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBean {

	
	public PostBean(MessageSource messageSource, User user, Post post){
		Locale locale = Locale.ROOT;
		
		if (user != null) {
			this.user = user;
			locale = user.getLanguageStatus().getLocale();		
		}	
		
		this.post = post;
		
		MessageUtil msgUtil = new MessageUtil();
		number = msgUtil.getMessage(messageSource, "post.number", locale);
		createdUser = msgUtil.getMessage(messageSource, "post.createdUser", locale);
		postTitle = msgUtil.getMessage(messageSource, "post.postTitle", locale);
		postContent = msgUtil.getMessage(messageSource, "post.postContent", locale);
		createdDate = msgUtil.getMessage(messageSource, "post.createdDate", locale);
		updatedDate = msgUtil.getMessage(messageSource, "post.updatedDate", locale);
		create = msgUtil.getMessage(messageSource, "post.create", locale);
		update = msgUtil.getMessage(messageSource,"post.update", locale);
		delete = msgUtil.getMessage(messageSource,"post.delete", locale);
	}

	private User user;
	
	private Post post;
	
	private String number;
	
	private String createdUser;
	
	private String postTitle;
	
	private String postContent;
	
	private String createdDate;
	
	private String updatedDate;
	
	private String create;
	
	private String update;
	
	private String delete;

}
