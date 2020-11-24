package com.bundesreport.dto;

import java.time.LocalDateTime;

import com.bundesreport.domain.Note;
import com.bundesreport.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NoteForm {
	private Long id;
	private boolean deleted;
	private String title;
	private String content;
	private LocalDateTime createdDate;
	private boolean readed;
	private User sender;
	private User receiver;

	public NoteForm(User receiver) {
		this.receiver = receiver;
	}

	public Note toEntity() {
		return Note.builder().id(id).deleted(deleted).title(title).content(content).createdDate(createdDate)
				.readed(readed).sender(sender).receiver(receiver).build();
	}
}
