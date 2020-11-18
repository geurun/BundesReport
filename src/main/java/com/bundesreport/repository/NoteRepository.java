package com.bundesreport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bundesreport.domain.Note;
import com.bundesreport.domain.User;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	public List<Note> findByReceiver(User user);
}
