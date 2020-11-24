package com.bundesreport.dto;

import java.time.LocalDateTime;

import com.bundesreport.domain.Comment;
import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentForm {
	private Long id;
	private boolean deleted;
	private String content;
	private LocalDateTime createdDate;
	private User user;
	private Post post;

	public CommentForm(User user, Post post) {
		this.user = user;
		this.post = post;
	}

	public Comment toEntity() {
		return Comment.builder().id(id).deleted(deleted).content(content).createdDate(createdDate).user(user).post(post)
				.build();
	}

	@Builder
	public CommentForm(Long id, boolean deleted, String content, LocalDateTime createdDate, User user, Post post) {
		this.id = id;
		this.deleted = deleted;
		this.content = content;
		this.createdDate = createdDate;
		this.user = user;
		this.post = post;
	}
}
