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

	public PostBean(MessageSource msgSrc, User user, Post post, String category) {
		super(msgSrc, user);
		this.post = post;

		if (Objects.nonNull(category)) {
			this.category = CategoryType.valueOf(category.toUpperCase());
		}

		if (Objects.isNull(this.category) && Objects.nonNull(post)) {
			this.category = post.getCategory();
		}

		if (Objects.nonNull(user) && Objects.nonNull(post)) {
			if (user.getUsername().equals(post.getUser().getUsername())) {
				this.hasPermit = 1;
			}
		}

		setTitle(getMsgUtil().getMessage(this.category.getMessageCode()));
		postTitle = getMsgUtil().getMessage("post.postTitle");
		postContent = getMsgUtil().getMessage("post.postContent");
		createdUser = getMsgUtil().getMessage("post.createdUser");
		createdDate = getMsgUtil().getMessage("post.createdDate");
		updatedDate = getMsgUtil().getMessage("post.updatedDate");
		btnList = getMsgUtil().getMessage("post.btnList");
		btnSave = getMsgUtil().getMessage("post.btnSave");
		btnModify = getMsgUtil().getMessage("post.btnModify");
		btnDelete = getMsgUtil().getMessage("post.btnDelete");
		btnMessageSend = getMsgUtil().getMessage("post.note.send");
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
	private String btnMessageSend;
	private int hasPermit = 0;
}
