package com.bundesreport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bundesreport.domain.Post;
import com.bundesreport.domain.PostLike;
import com.bundesreport.domain.User;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

	public List<PostLike> findByPostAndUser(Post post, User user);

	public int countByPostAndUser(Post post, User user);
}
