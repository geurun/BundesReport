package com.bundesreport.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bundesreport.domain.Post;
import com.bundesreport.type.CategoryType;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepository {

	private final EntityManager em;
	
	public void save(Post post) {
		if (post.getId() == null) {
			em.persist(post);
		}
		else {
			em.merge(post);
		}
	}
	
	public Post findOne(Long id) {
		return em.find(Post.class, id);
	}
	
	public List<Post> findAll() {
		return em.createQuery("select p from Post p", Post.class).getResultList();
	}
	
	public List<Post> findByCategory(CategoryType categoryType) {
		return em.createQuery("select p from Post p where p.category = :category", Post.class).setParameter("category", categoryType).getResultList();
	}
}

