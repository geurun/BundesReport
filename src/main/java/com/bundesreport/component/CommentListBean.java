package com.bundesreport.component;

import java.util.List;
import java.util.Objects;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Comment;
import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentListBean extends MessageBean {
	
	public CommentListBean(MessageSource msgSrc, User user, Post post, Comment comment) {
		super(msgSrc, user);
		this.comment = comment;
	
		if (Objects.nonNull(user) && Objects.nonNull(comment)) {
			if (user.getUsername().equals(comment.getUser().getUsername())) {
				this.hasPermit = 1;
			}
		}
		
		commentHeader = msgUtil.getMessage("comment.commentHeader");
		createdDate = msgUtil.getMessage("comment.createdDate");
		updatedDate = msgUtil.getMessage("comment.updatedDate");
		btnSave = msgUtil.getMessage("comment.btnSave");
		btnModify = msgUtil.getMessage("comment.btnModify");
		btnDelete = msgUtil.getMessage("comment.btnDelete");
	
	}
	
	private Comment comment;
	private Post post;
	private String commentHeader;
	private String createdUser;
	private String createdDate;
	private String updatedDate;
	private String btnSave;
	private String btnModify;
	private String btnDelete;
	private int hasPermit = 0;
}

