package com.bundesreport.component;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

import com.bundesreport.domain.Comment;
import com.bundesreport.domain.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentListBean extends MessageBean {

	public CommentListBean(MessageSource msgSrc, Authentication auth, Post post, List<Comment> comments) {
		super(msgSrc, auth);
		this.post = post;
		this.comments = comments;
		commentHeader = getMsgUtil().getMessage("comment.commentHeader");
		commentContent = getMsgUtil().getMessage("comment.commentContent");
		createdDate = getMsgUtil().getMessage("comment.createdDate");
		btnSave = getMsgUtil().getMessage("comment.btnSave");
		btnModify = getMsgUtil().getMessage("comment.btnModify");
		btnDelete = getMsgUtil().getMessage("comment.btnDelete");
		confirmTitle = getMsgUtil().getMessage("comment.confirm.title");
		confirmContent = getMsgUtil().getMessage("comment.confirm.content");
		confirmCancel = getMsgUtil().getMessage("comment.confirm.cancel");
		confirmApply = getMsgUtil().getMessage("comment.confirm.apply");
	}

	private List<Comment> comments;
	private Post post;
	private String commentHeader;
	private String commentContent;
	private String createdUser;
	private String createdDate;
	private String btnSave;
	private String btnModify;
	private String btnDelete;
	private int hasPermit = 0;
	private String confirmTitle;
	private String confirmContent;
	private String confirmCancel;
	private String confirmApply;
}
