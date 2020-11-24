package com.bundesreport.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
@Where(clause = "deleted = false")
@Table(name = "notes")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private boolean deleted;

	private String title;

	@Lob
	private String content;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column
	private boolean readed;

	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private User receiver;

	@Builder
	public Note(Long id, boolean deleted, String title, String content, LocalDateTime createdDate, boolean readed,
			User sender, User receiver) {
		this.id = id;
		this.deleted = deleted;
		this.title = title;
		this.content = content;
		this.createdDate = createdDate;
		this.readed = readed;
		this.sender = sender;
		this.receiver = receiver;
	}

}
