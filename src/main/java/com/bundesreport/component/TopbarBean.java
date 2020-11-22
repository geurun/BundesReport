package com.bundesreport.component;

import java.util.List;
import java.util.Objects;

import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

import com.bundesreport.domain.Note;
import com.bundesreport.service.NoteService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopbarBean extends MessageBean {

	public TopbarBean(MessageSource msgSrc, Authentication auth, NoteService noteService) {
		super(msgSrc, auth);

		if (Objects.nonNull(getUser())) {
			this.messages = noteService.findTop5ByReceiverOrderByCreatedDateDesc(getUser());
			this.messageCount = noteService.countByReceiverAndReadedFalse(getUser());
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
