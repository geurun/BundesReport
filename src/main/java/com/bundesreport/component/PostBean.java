package com.bundesreport.component;

import java.util.Objects;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.type.CategoryType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBean extends MessageBean {

	public PostBean(MessageSource msgSrc, User user, Post post, CategoryType category) {
		super(msgSrc, user);
		this.post = post;
		this.category = category;

		if (Objects.isNull(category) && Objects.nonNull(post)) {
			this.category = post.getCategory();
		}

		if (Objects.nonNull(user) && Objects.nonNull(post)) {
			if (user.getUsername().equals(post.getUser().getUsername())) {
				this.hasPermit = 1;
			}
		}

		title = msgUtil.getMessage(this.category.getMessageCode());
		postTitle = msgUtil.getMessage("post.postTitle");
		postContent = msgUtil.getMessage("post.postContent");
		createdUser = msgUtil.getMessage("post.createdUser");
		createdDate = msgUtil.getMessage("post.createdDate");
		updatedDate = msgUtil.getMessage("post.updatedDate");
		btnList = msgUtil.getMessage("post.btnList");
		btnSave = msgUtil.getMessage("post.btnSave");
		btnModify = msgUtil.getMessage("post.btnModify");
		btnDelete = msgUtil.getMessage("post.btnDelete");
	}

	private Post post;
	private CategoryType category;
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
