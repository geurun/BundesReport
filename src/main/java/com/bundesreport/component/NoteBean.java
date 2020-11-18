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

		title = msgUtil.getMessage("note.title");
		noteTitle = msgUtil.getMessage("note.noteTitle");
		noteContent = msgUtil.getMessage("note.noteContent");
		sender = msgUtil.getMessage("note.sender");
		receiver = msgUtil.getMessage("note.receiver");
		date = msgUtil.getMessage("note.date");
		btnList = msgUtil.getMessage("note.list");
		btnSend = msgUtil.getMessage("note.send");
		btnReply = msgUtil.getMessage("note.reply");
		btnDelete = msgUtil.getMessage("note.delete");
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
