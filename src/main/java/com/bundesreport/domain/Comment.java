package com.bundesreport.domain;

import java.time.LocalDateTime;

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

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue
	private Long id;

	private boolean deleted;

	@Lob
	private String content;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "like_id")
	private Like like;

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
	public Comment(Long id, boolean deleted, String content, User user, Post post) {
		this.id = id;
		this.deleted = deleted;
		this.content = content;
		this.user = user;
		this.post = post;
	}

	public CommentForm toCommentForm() {
		return CommentForm.builder().id(id).deleted(deleted).content(content).user(user).post(post).build();
	}

	public Comment getUpdateModel(CommentForm form) {
		setContent(form.getContent());
		return this;
	}
}