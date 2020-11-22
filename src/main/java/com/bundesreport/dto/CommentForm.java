package com.bundesreport.dto;

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
	private String content;
	private User user;
	private Post post;

	public CommentForm(User user, Post post) {
		this.user = user;
		this.post = post;
	}

	public Comment toEntity() {
		return Comment.builder().id(id).content(content).user(user).post(post).build();
	}

	@Builder
	public CommentForm(Long id, String content, User user, Post post) {
		this.id = id;
		this.content = content;
		this.user = user;
		this.post = post;
	}
}
