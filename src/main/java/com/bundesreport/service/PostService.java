package com.bundesreport.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.User;
import com.bundesreport.dto.PostForm;
import com.bundesreport.repository.PostLikeRepository;
import com.bundesreport.repository.PostRepository;
import com.bundesreport.type.CategoryType;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

	@Autowired
	private final PostRepository postRepository;

	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
	}

	public List<Post> findPosts() {
		return postRepository.findAll();
	}

	public List<Post> findByCategory(String categoryName) {
		return postRepository.findByCategory(CategoryType.valueOf(categoryName.toUpperCase()));
	}

	public List<Post> findByCategory(CategoryType category) {
		return postRepository.findByCategory(category);
	}

	public List<Post> findByTitleOrContent(String searchKey) {
		return postRepository.findByTitleContainsOrContentContainsOrderById(searchKey, searchKey);
	}

	public List<Post> findByUser(User user) {
		return postRepository.findByUser(user);
	}

	public void save(PostForm postForm) {
		postRepository.save(postForm.toEntity());
	}

	public void update(Post post) {
		postRepository.save(post);
	}

	public String delete(Long postId) {
		Optional<Post> post = postRepository.findById(postId);
		if (Objects.nonNull(post)) {
			postRepository.delete(post.get());
			return post.get().getCategory().getLowerString();
		}
		return "";
	}
}
