package com.bundesreport.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.PostLike;
import com.bundesreport.domain.User;
import com.bundesreport.dto.LikeForm;
import com.bundesreport.repository.PostLikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeService {

	@Autowired
	private final PostLikeRepository likeRepository;

	public List<PostLike> findByPostAndUser(Post post, User user) {
		return likeRepository.findByPostAndUser(post, user);
	}

	public int countByPostAndUser(Post post, User user) {
		return likeRepository.countByPostAndUser(post, user);
	}

	public void save(LikeForm likeForm) {
		likeRepository.save(likeForm.toPostLikeEntity());
	}

	public void delete(PostLike postLike) {
		likeRepository.delete(postLike);
	}
}
