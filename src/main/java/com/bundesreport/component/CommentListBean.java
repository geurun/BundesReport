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
public class CommentListBean extends MessageBean {

	public CommentListBean(MessageSource msgSrc, User user, Post post, List<Comment> comments) {
		super(msgSrc, user);
		this.comments = comments;
		this.post = post;

		commentHeader = getMsgUtil().getMessage("comment.commentHeader");
		commentContent = getMsgUtil().getMessage("comment.commentContent");
		createdDate = getMsgUtil().getMessage("comment.createdDate");
		updatedDate = getMsgUtil().getMessage("comment.updatedDate");
		btnSave = getMsgUtil().getMessage("comment.btnSave");
		btnModify = getMsgUtil().getMessage("comment.btnModify");
		btnDelete = getMsgUtil().getMessage("comment.btnDelete");

	}

	private List<Comment> comments;
	private Post post;
	private String commentHeader;
	private String commentContent;
	private String createdUser;
	private String createdDate;
	private String updatedDate;
	private String btnSave;
	private String btnModify;
	private String btnDelete;
	private int hasPermit = 0;
}
