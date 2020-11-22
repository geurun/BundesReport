package com.bundesreport.component;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;

import com.bundesreport.domain.Note;
import com.bundesreport.service.NoteService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteListBean extends MessageBean {

	public NoteListBean(MessageSource msgSrc, Authentication auth, NoteService noteService) {
		super(msgSrc, auth);
		this.messages = noteService.findByReceiver(getUser());

		setTitle(getMsgUtil().getMessage("note.list.title"));
		noteTitle = getMsgUtil().getMessage("note.list.noteTitle");
		noteContent = getMsgUtil().getMessage("note.list.noteContent");
		sender = getMsgUtil().getMessage("note.list.sender");
		date = getMsgUtil().getMessage("note.list.date");
		readed = getMsgUtil().getMessage("note.list.readed");
		readedTrue = getMsgUtil().getMessage("note.list.readed.true");
		readedFalse = getMsgUtil().getMessage("note.list.readed.false");
		btnPrevious = getMsgUtil().getMessage("note.list.paging.previous");
		btnNext = getMsgUtil().getMessage("note.list.paging.next");
		btnDelete = getMsgUtil().getMessage("note.list.delete");
		btnMessageSend = getMsgUtil().getMessage("note.list.note.send");
	}

	private List<Note> messages;
	private String noteTitle;
	private String noteContent;
	private String sender;
	private String date;
	private String readed;
	private String readedTrue;
	private String readedFalse;
	private String paging;
	private String btnPrevious;
	private String btnNext;
	private String btnDelete;
	private String btnMessageSend;
}
