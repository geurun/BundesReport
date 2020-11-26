package com.bundesreport.component;

import java.util.Objects;

import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

import com.bundesreport.domain.Post;
import com.bundesreport.type.CategoryType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBean extends MessageBean {

	public PostBean(MessageSource msgSrc, Authentication auth, Post post, String category) {
		super(msgSrc, auth);
		this.post = post;

		if (Objects.nonNull(category)) {
			this.category = category;
		}

		if (Objects.isNull(this.category) && Objects.nonNull(post)) {
			this.category = post.getCategory().getLowerString();
		}

		if (Objects.nonNull(getUser()) && Objects.nonNull(post)) {
			if (getUser().getUsername().equals(post.getUser().getUsername())) {
				this.hasPermit = 1;
			}
		}

		setTitle(getMsgUtil().getMessage(CategoryType.valueOf(this.category.toUpperCase()).getMessageCode()));
		postTitle = getMsgUtil().getMessage("post.postTitle");
		postContent = getMsgUtil().getMessage("post.postContent");
		createdUser = getMsgUtil().getMessage("post.createdUser");
		createdDate = getMsgUtil().getMessage("post.createdDate");
		viewCount = getMsgUtil().getMessage("post.viewCount");
		btnList = getMsgUtil().getMessage("post.btnList");
		btnSave = getMsgUtil().getMessage("post.btnSave");
		btnModify = getMsgUtil().getMessage("post.btnModify");
		btnDelete = getMsgUtil().getMessage("post.btnDelete");
		btnMessageSend = getMsgUtil().getMessage("post.note.send");
		confirmTitle = getMsgUtil().getMessage("post.confirm.title");
		confirmContent = getMsgUtil().getMessage("post.confirm.content");
		confirmCancel = getMsgUtil().getMessage("post.confirm.cancel");
		confirmApply = getMsgUtil().getMessage("post.confirm.apply");
	}

	private Post post;
	private String category;
	private String postTitle;
	private String postContent;
	private String createdUser;
	private String createdDate;
	private String viewCount;
	private String btnList;
	private String btnSave;
	private String btnModify;
	private String btnDelete;
	private String btnMessageSend;
	private int hasPermit = 0;
	private int hasLike = 0;
	private String confirmTitle;
	private String confirmContent;
	private String confirmCancel;
	private String confirmApply;
}
