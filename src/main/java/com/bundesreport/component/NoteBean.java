package com.bundesreport.component;

import org.springframework.context.MessageSource;

import com.bundesreport.domain.Note;
import com.bundesreport.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteBean extends MessageBean {

	public NoteBean(MessageSource msgSrc, User user, Note message) {
		super(msgSrc, user);
		this.message = message;

		setTitle(getMsgUtil().getMessage("note.title"));
		noteTitle = getMsgUtil().getMessage("note.noteTitle");
		noteContent = getMsgUtil().getMessage("note.noteContent");
		sender = getMsgUtil().getMessage("note.sender");
		receiver = getMsgUtil().getMessage("note.receiver");
		date = getMsgUtil().getMessage("note.date");
		btnList = getMsgUtil().getMessage("note.list");
		btnSend = getMsgUtil().getMessage("note.send");
		btnReply = getMsgUtil().getMessage("note.reply");
		btnDelete = getMsgUtil().getMessage("note.delete");
	}

	private Note message;
	private String receiverName;
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
