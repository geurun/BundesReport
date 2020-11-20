package com.bundesreport.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@Table(name = "likes")
public class Like {

	@Id
	@GeneratedValue
	private Long id;

	private boolean deleted;

	@OneToOne
	private User user;

	@OneToOne
	private Post post;

	@OneToOne
	private Comment comment;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@Builder
	public Like(Long id, User user, Post post, Comment comment) {
		this.id = id;
		this.user = user;
		this.post = post;
		this.comment = comment;
	}
}
