package com.bundesreport.dto;

import java.time.LocalDateTime;

import com.bundesreport.domain.Comment;
import com.bundesreport.domain.CommentLike;
import com.bundesreport.domain.Post;
import com.bundesreport.domain.PostLike;
import com.bundesreport.domain.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeForm {
	private Long id;
	private boolean deleted;
	private User user;
	private Long userId;
	private Post post;
	private Long postId;
	private Comment comment;
	private Long CommentId;
	private LocalDateTime createdDate;

	public LikeForm(User user, Post post) {
		this.user = user;
		this.post = post;
	}

	public LikeForm(User user, Comment comment) {
		this.user = user;
		this.comment = comment;
	}

	public PostLike toPostLikeEntity() {
		return PostLike.builder().id(id).deleted(deleted).user(user).post(post).createdDate(createdDate).build();
	}

	public CommentLike toCommentLikeEntity() {
		return CommentLike.builder().id(id).deleted(deleted).user(user).comment(comment).createdDate(createdDate)
				.build();
	}

	@Builder
	public LikeForm(Long id, boolean deleted, User user, Post post, LocalDateTime createdDate) {
		this.id = id;
		this.deleted = deleted;
		this.user = user;
		this.post = post;
		this.createdDate = createdDate;
	}

	@Builder
	public LikeForm(Long id, boolean deleted, User user, Comment comment, LocalDateTime createdDate) {
		this.id = id;
		this.deleted = deleted;
		this.user = user;
		this.comment = comment;
		this.createdDate = createdDate;
	}
}
