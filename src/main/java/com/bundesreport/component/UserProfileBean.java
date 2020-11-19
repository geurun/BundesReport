package com.bundesreport.component;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileBean extends MessageBean {

	public UserProfileBean(MessageSource msgSrc, User user) {
		super(msgSrc, user);

		// ToDo: get from post
		postCount = 0;
		postLikeCount = 0;

		// ToDo: get from comment
		commentCount = 0;
		commentLikeCount = 0;

		setTitle(getMsgUtil().getMessage("user.title"));
		myPostCount = getMsgUtil().getMessage("user.myPostCount");
		myPostLikeCount = getMsgUtil().getMessage("user.myPostLikeCount");
		myCommentCount = getMsgUtil().getMessage("user.myCommentCount");
		myCommentLikeCount = getMsgUtil().getMessage("user.myCommentLikeCount");
		modifyUserInfo = getMsgUtil().getMessage("user.modifyUserInfo");
		userName = getMsgUtil().getMessage("user.username");
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
	private String userName;
	private String password;
	private String email;
	private String languageStatus;
	private String withdrawal;
	private String save;
}
