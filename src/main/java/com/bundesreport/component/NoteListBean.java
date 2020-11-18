package com.bundesreport.component;

import java.util.List;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Note;
import com.bundesreport.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteListBean extends MessageBean {

	public NoteListBean(MessageSource msgSrc, User user, List<Note> messages) {
		super(msgSrc, user);
		this.messages = messages;

		title = msgUtil.getMessage("note.list.title");
		noteTitle = msgUtil.getMessage("note.list.noteTitle");
		noteContent = msgUtil.getMessage("note.list.noteContent");
		sender = msgUtil.getMessage("note.list.sender");
		date = msgUtil.getMessage("note.list.date");
		readed = msgUtil.getMessage("note.list.readed");
		btnPrevious = msgUtil.getMessage("note.list.paging.previous");
		btnNext = msgUtil.getMessage("note.list.paging.next");
		btnDelete = msgUtil.getMessage("note.list.delete");
	}

	private List<Note> messages;
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
