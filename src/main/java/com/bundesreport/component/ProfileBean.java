package com.bundesreport.component;

import java.util.Objects;

import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

import com.bundesreport.domain.Comment;
import com.bundesreport.domain.Post;
import com.bundesreport.service.CommentService;
import com.bundesreport.service.PostService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileBean extends MessageBean {

	public ProfileBean(MessageSource msgSrc, Authentication auth, PostService postService,
			CommentService commentService) {
		super(msgSrc, auth);
		if (Objects.nonNull(getUser())) {
			for (Post post : postService.findByUser(getUser())) {
				this.postCount++;
				this.postLikeCount += post.getLikes().size();
			}
			for (Comment comment : commentService.findByUser(getUser())) {
				this.commentCount++;
				this.commentLikeCount += comment.getLikes().size();
			}
		}

		setTitle(getMsgUtil().getMessage("user.title"));
		myPostCount = getMsgUtil().getMessage("user.myPostCount");
		myPostLikeCount = getMsgUtil().getMessage("user.myPostLikeCount");
		myCommentCount = getMsgUtil().getMessage("user.myCommentCount");
		myCommentLikeCount = getMsgUtil().getMessage("user.myCommentLikeCount");
		modifyUserInfo = getMsgUtil().getMessage("user.modifyUserInfo");
		username = getMsgUtil().getMessage("user.username");
		password = getMsgUtil().getMessage("user.password");
		email = getMsgUtil().getMessage("user.email");
		languageStatus = getMsgUtil().getMessage("user.languageStatus");
		withdrawal = getMsgUtil().getMessage("user.withdrawal");
		save = getMsgUtil().getMessage("user.save");
		confirmTitle = getMsgUtil().getMessage("user.confirm.title");
		confirmContent = getMsgUtil().getMessage("user.confirm.content");
		confirmCancel = getMsgUtil().getMessage("user.confirm.cancel");
		confirmApply = getMsgUtil().getMessage("user.confirm.apply");
	}

	private int postCount;
	private int postLikeCount;
	private int commentCount;
	private int commentLikeCount;
	private String myPostCount;
	private String myPostLikeCount;
	private String myCommentCount;
	private String myCommentLikeCount;
	private String modifyUserInfo;
	private String username;
	private String password;
	private String email;
	private String languageStatus;
	private String withdrawal;
	private String save;
	private String confirmTitle;
	private String confirmContent;
	private String confirmCancel;
	private String confirmApply;
}
