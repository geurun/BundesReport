package com.bundesreport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bundesreport.domain.Comment;
import com.bundesreport.domain.CommentLike;
import com.bundesreport.domain.User;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {

	public List<CommentLike> findByCommentAndUser(Comment comment, User user);
}
