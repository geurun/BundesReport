package com.bundesreport.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bundesreport.dto.CommentForm;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "COMMENTS")
public class Comment {

	@Id
	@GeneratedValue
	@Column(name = "comment_id")
	private Long id;

	@Lob
	private String content;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;

	private int good;

	private boolean deleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	// ==relational method==//
	public void setPost(Post post) {
		this.post = post;
		post.getComments().add(this);
	}

	public void setUser(User user) {
		this.user = user;
		user.getComments().add(this);
	}

	@Builder
	public Comment(Long id, String content, User user, Post post, boolean deleted) {
		this.id = id;
		this.content = content;
		this.user = user;
		this.post = post;
		this.deleted = deleted;
	}

	public CommentForm toCommentForm() {
		return CommentForm.builder().id(id).content(content).user(user).post(post).deleted(deleted).build();
	}
}
