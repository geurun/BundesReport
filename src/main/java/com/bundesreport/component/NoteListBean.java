package com.bundesreport.component;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Note;
import com.bundesreport.domain.User;
import com.bundesreport.util.MessageUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteListBean {

	public NoteListBean(MessageSource messageSource, User user, List<Note> messages) {
		// ToDo: Selected Locale
		Locale locale = Locale.ROOT;

		if (user != null) {
			this.user = user;
			locale = user.getLanguageStatus().getLocale();
		}

		this.messages = messages;

		MessageUtil msgUtil = new MessageUtil();
		title = msgUtil.getMessage(messageSource, "note.list.title", locale);
		noteTitle = msgUtil.getMessage(messageSource, "note.list.noteTitle", locale);
		noteContent = msgUtil.getMessage(messageSource, "note.list.noteContent", locale);
		sender = msgUtil.getMessage(messageSource, "note.list.sender", locale);
		date = msgUtil.getMessage(messageSource, "note.list.date", locale);
		readed = msgUtil.getMessage(messageSource, "note.list.readed", locale);
		btnPrevious = msgUtil.getMessage(messageSource, "note.list.paging.previous", locale);
		btnNext = msgUtil.getMessage(messageSource, "note.list.paging.next", locale);
		btnDelete = msgUtil.getMessage(messageSource, "note.list.delete", locale);

//				note.list.readed.true = gelesen
//				note.list.readed.false = nicht lesen

	}

	private User user;

	private List<Note> messages;

	private String title;

	private String noteTitle;

	private String noteContent;

	private String sender;

	private String date;

	private String readed;

	private String paging;

	private String btnPrevious;

	private String btnNext;

	private String btnDelete;
}
