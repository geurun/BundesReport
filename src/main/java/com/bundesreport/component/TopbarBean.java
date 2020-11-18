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

		messageCenter = msgUtil.getMessage("topbar.messageCenter");
		readMore = msgUtil.getMessage("topbar.readMore");
		signin = msgUtil.getMessage("topbar.button.signin");
		signup = msgUtil.getMessage("topbar.button.signup");
		profile = msgUtil.getMessage("topbar.button.profile");
		signout = msgUtil.getMessage("topbar.button.signout");
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
