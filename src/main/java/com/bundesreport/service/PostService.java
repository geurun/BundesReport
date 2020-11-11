package com.bundesreport.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bundesreport.domain.Post;
import com.bundesreport.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	/** 글 저장 */
	@Transactional
	public Long savePost(Post post) {
		postRepository.save(post);
		return post.getId();
	}

	/** 글 조회 */
	public Post findPost(Long postId) {
		return postRepository.findOne(postId);
	}

	public List<Post> findPosts() {
		return postRepository.findAll();
	}

	public List<Post> findPostsByCategory(int categoryId) {
		return postRepository.findByCategory(categoryId);
	}
}
