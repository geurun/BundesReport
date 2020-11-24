package com.bundesreport.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@OneToMany(mappedBy = "comment", cascade = { CascadeType.ALL })
	private List<CommentLike> likes = new ArrayList<>();

	@Builder
	public Comment(Long id, boolean deleted, String content, LocalDateTime createdDate, User user, Post post) {
		this.id = id;
		this.deleted = deleted;
		this.content = content;
		this.createdDate = createdDate;
		this.user = user;
		this.post = post;
	}

	public CommentForm toCommentForm() {
		return CommentForm.builder().id(id).deleted(deleted).content(content).createdDate(createdDate).user(user)
				.post(post).build();
	}

	public Comment getUpdateModel(CommentForm form) {
		setContent(form.getContent());
		return this;
	}

	public boolean hasMineLike(User user) {
		for (CommentLike commentLike : getLikes()) {
			if (commentLike.getUser().getUsername().equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}

	public int getLikesCount() {
		return getLikes().size();
	}
}