package com.bundesreport.component;

import java.util.List;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Comment;
import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentBean extends MessageBean {
	
	public CommentBean(MessageSource msgSrc, User user, Post post, List<Comment> comments) {
		super(msgSrc, user);
		this.comments = comments;
	}
	
	private List<Comment> comments;
	private Post post;
	private String commentContent;
	private String createdDate;
	private String updatedDate;
	private String btnSave;
	private String btnModify;
	private String btnDelete;
	private int hasPermit = 0;
}

