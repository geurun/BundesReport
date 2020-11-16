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
	private String title;
	private String content;
	private User sender;
	private User receiver;
	private LocalDateTime createdDate;

	public NoteForm(User receiver) {
		this.receiver = receiver;
	}

	public Note toEntity() {
		return Note.builder().id(id).title(title).content(content).sender(sender).receiver(receiver).build();
	}
}
