package com.bundesreport.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JoinColumnOrFormula;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "NOTES")
public class Note {

	@Id @GeneratedValue
	@Column(name = "note_id")
	private Long id;
	
	@Column(columnDefinition = "TEXT")
	private String title;
	
	@Lob
	private String content;
	
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User sender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private User receiver;

}
