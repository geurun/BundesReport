package com.bundesreport.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
@Table(name = "NOTES")
public class Note {

	@Builder
	public Note(Long id, String title, String content, User sender, User receiver) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
	}

	@Id
	@GeneratedValue
	@Column(name = "note_id")
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String title;

	@Lob
	private String content;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column
	private boolean readed;

	@ManyToOne
	@JoinColumn(name = "SENDER_ID")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "RECEIVER_ID")
	private User receiver;
}
