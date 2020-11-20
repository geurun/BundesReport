package com.bundesreport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bundesreport.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
