package com.bundesreport.service;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bundesreport.domain.Comment;
import com.bundesreport.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

	@Autowired
	private final CommentRepository commentRepository;

	public Long delete(Long commentId) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		Long postId = null;
		if (Objects.nonNull(comment)) {
			postId = comment.get().getPost().getId();
			commentRepository.delete(comment.get());
		}
		return postId;
	}
}
