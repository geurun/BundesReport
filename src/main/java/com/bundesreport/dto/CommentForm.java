package com.bundesreport.dto;

import java.time.LocalDateTime;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentForm {

	private Long id;
	private String content;
	private LocalDateTime updatedDate;
	private User user;
	private Post post;
	private boolean deleted;

	public CommentForm(User user, Post post) {
		this.user = user;
		this.post = post;
	}

}
