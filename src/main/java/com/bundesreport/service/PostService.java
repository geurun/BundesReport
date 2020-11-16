package com.bundesreport.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bundesreport.domain.Post;
import com.bundesreport.dto.PostForm;
import com.bundesreport.repository.PostRepository;
import com.bundesreport.type.CategoryType;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	/** 글 조회 */
	public Post findPost(Long postId) {
		return postRepository.findOne(postId);
	}

	public List<Post> findPosts() {
		return postRepository.findAll();
	}

	public List<Post> findPostsByCategory(CategoryType categoryType) {
		return postRepository.findByCategory(categoryType);
	}

	@Transactional
	public Long createPost(PostForm postForm) {
		// postForm.setUser(user);
		Post post = postForm.toEntity();
		postRepository.save(post);
		return post.getId();
	}
	
	@Transactional
	public Long updatePost(PostForm postForm) {
		Post post = postRepository.findOne(postForm.getId());
		post.setTitle(postForm.getTitle());
		post.setContent(postForm.getContent());
		post.setCategory(postForm.getCategory());
		post.setUpdatedDate(LocalDateTime.now());
		return post.getId();
	}

}
