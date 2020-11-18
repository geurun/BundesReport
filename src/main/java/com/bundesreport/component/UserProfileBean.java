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

		title = msgUtil.getMessage("user.title");
		myPostCount = msgUtil.getMessage("user.myPostCount");
		myPostLikeCount = msgUtil.getMessage("user.myPostLikeCount");
		myCommentCount = msgUtil.getMessage("user.myCommentCount");
		myCommentLikeCount = msgUtil.getMessage("user.myCommentLikeCount");
		modifyUserInfo = msgUtil.getMessage("user.modifyUserInfo");
		userName = msgUtil.getMessage("user.username");
		password = msgUtil.getMessage("user.password");
		email = msgUtil.getMessage("user.email");
		languageStatus = msgUtil.getMessage("user.languageStatus");
		withdrawal = msgUtil.getMessage("user.withdrawal");
		save = msgUtil.getMessage("user.save");
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
