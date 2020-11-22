package com.bundesreport.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bundesreport.domain.Comment;
import com.bundesreport.domain.User;
import com.bundesreport.dto.CommentForm;
import com.bundesreport.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

	@Autowired
	private final CommentRepository commentRepository;

	public Optional<Comment> findById(Long id) {
		return commentRepository.findById(id);
	}

	public List<Comment> findByUser(User user) {
		return commentRepository.findByUser(user);
	}

	public void save(CommentForm commentForm) {
		commentRepository.save(commentForm.toEntity());
	}

	public void update(Comment comment) {
		commentRepository.save(comment);
	}

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
