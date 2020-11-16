package com.bundesreport.component;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Note;
import com.bundesreport.domain.User;
import com.bundesreport.util.MessageUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteBean {

	public NoteBean(MessageSource messageSource, User user, Note message) {
		// ToDo: Selected Locale
		Locale locale = Locale.ROOT;

		if (user != null) {
			this.user = user;
			locale = user.getLanguageStatus().getLocale();
		}

		this.message = message;

		MessageUtil msgUtil = new MessageUtil();
		title = msgUtil.getMessage(messageSource, "note.title", locale);
		noteTitle = msgUtil.getMessage(messageSource, "note.noteTitle", locale);
		noteContent = msgUtil.getMessage(messageSource, "note.noteContent", locale);
		sender = msgUtil.getMessage(messageSource, "note.sender", locale);
		receiver = msgUtil.getMessage(messageSource, "note.receiver", locale);
		date = msgUtil.getMessage(messageSource, "note.date", locale);
		btnList = msgUtil.getMessage(messageSource, "note.list", locale);
		btnSend = msgUtil.getMessage(messageSource, "note.send", locale);
		btnReply = msgUtil.getMessage(messageSource, "note.reply", locale);
		btnDelete = msgUtil.getMessage(messageSource, "note.delete", locale);
	}

	private User user;

	private Note message;

	private String receiverName;

	private String title;

	private String noteTitle;

	private String noteContent;

	private String sender;

	private String receiver;

	private String date;

	private String btnList;

	private String btnSend;

	private String btnReply;

	private String btnDelete;
}
