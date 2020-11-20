package com.bundesreport.component;

import java.util.Objects;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.User;
import com.bundesreport.service.PostService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileBean extends MessageBean {

	public ProfileBean(MessageSource msgSrc, User user, PostService postService) {
		super(msgSrc, user);
		if (Objects.nonNull(user)) {
			this.postCount = postService.countByUser(user);
//			this.postLikeCount = postLikeCount;
//			this.commentCount = commentCount;
//			this.commentLikeCount = commentLikeCount;
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
}
