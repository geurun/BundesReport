package com.bundesreport.component;

import java.util.List;
import java.util.Objects;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Note;
import com.bundesreport.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopbarBean extends MessageBean {

	public TopbarBean(MessageSource msgSrc, User user, List<Note> messages, int count) {
		super(msgSrc, user);

		if (Objects.nonNull(user)) {
			this.messages = messages;
			this.messageCount = count;
			if (messageCount > 99) {
				messageCount = 99;
			}
		}

		messageCenter = getMsgUtil().getMessage("topbar.messageCenter");
		readMore = getMsgUtil().getMessage("topbar.readMore");
		signin = getMsgUtil().getMessage("topbar.button.signin");
		signup = getMsgUtil().getMessage("topbar.button.signup");
		profile = getMsgUtil().getMessage("topbar.button.profile");
		signout = getMsgUtil().getMessage("topbar.button.signout");
	}

	private List<Note> messages;
	private int messageCount;
	private String messageCenter;
	private String readMore;
	private String signin;
	private String signup;
	private String profile;
	private String signout;
}
