package com.bundesreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bundesreport.domain.Comment;
import com.bundesreport.domain.CommentLike;
import com.bundesreport.domain.User;
import com.bundesreport.dto.LikeForm;
import com.bundesreport.repository.CommentLikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentLikeService {

	@Autowired
	private final CommentLikeRepository likeRepository;

	public List<CommentLike> findByCommentAndUser(Comment comment, User user) {
		return likeRepository.findByCommentAndUser(comment, user);
	}

	public void save(LikeForm likeForm) {
		likeRepository.save(likeForm.toCommentLikeEntity());
	}

	public void delete(CommentLike commentLike) {
		likeRepository.delete(commentLike);
	}
}
