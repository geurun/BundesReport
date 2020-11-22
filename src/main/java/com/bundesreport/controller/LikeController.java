package com.bundesreport.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bundesreport.config.AjaxResponse;
import com.bundesreport.domain.Comment;
import com.bundesreport.domain.CommentLike;
import com.bundesreport.domain.Post;
import com.bundesreport.domain.PostLike;
import com.bundesreport.domain.User;
import com.bundesreport.dto.LikeForm;
import com.bundesreport.service.CommentLikeService;
import com.bundesreport.service.CommentService;
import com.bundesreport.service.PostLikeService;
import com.bundesreport.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LikeController extends PageController {

	@Autowired
	private final PostService postService;

	@Autowired
	private final PostLikeService postLikeService;

	@Autowired
	private final CommentService commentService;

	@Autowired
	private final CommentLikeService commentLikeService;

	@PostMapping(value = "/like/post")
	public ResponseEntity<?> likePost(@RequestBody LikeForm likeForm, Authentication auth) {
		AjaxResponse result = new AjaxResponse();
		if (Objects.nonNull(likeForm.getPostId())) {
			Optional<Post> postWrapper = postService.findById(likeForm.getPostId());
			User user = (User) auth.getPrincipal();
			for (PostLike postLike : postLikeService.findByPostAndUser(postWrapper.get(), user)) {
				postLikeService.delete(postLike);
				result.setLike(false);
				return ResponseEntity.ok(result);
			}
			likeForm.setPost(postWrapper.get());
			likeForm.setUser(user);
			postLikeService.save(likeForm);
			result.setLike(true);
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping(value = "/like/comment")
	public ResponseEntity<?> likeComment(@RequestBody LikeForm likeForm, Authentication auth) {
		AjaxResponse result = new AjaxResponse();
		if (Objects.nonNull(likeForm.getCommentId())) {
			Optional<Comment> commentWrapper = commentService.findById(likeForm.getCommentId());
			User user = (User) auth.getPrincipal();
			for (CommentLike commentLike : commentLikeService.findByCommentAndUser(commentWrapper.get(), user)) {
				commentLikeService.delete(commentLike);
				result.setLike(false);
				return ResponseEntity.ok(result);
			}
			likeForm.setComment(commentWrapper.get());
			likeForm.setUser(user);
			commentLikeService.save(likeForm);
			result.setLike(true);
		}
		return ResponseEntity.ok(result);
	}
}
